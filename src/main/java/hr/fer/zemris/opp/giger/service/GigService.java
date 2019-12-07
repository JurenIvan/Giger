package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.errorHandling.GigerException;
import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.domain.enums.GigType;
import hr.fer.zemris.opp.giger.repository.BandRepository;
import hr.fer.zemris.opp.giger.repository.GigRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.GigCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.GigPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static hr.fer.zemris.opp.giger.config.errorHandling.ErrorCode.NO_SUCH_GIG;

@Service
@AllArgsConstructor
public class GigService {

    private GigRepository gigRepository;
    private BandRepository bandRepository;
    private UserDetailsServiceImpl userDetailsService;

    public List<Gig> listGigsByType(GigType gigType) {
        return gigRepository.findAllByGigTypeAndPrivateGig(gigType, false);
    }

    public List<Gig> listGigsByBand(Band band) {
        return null;
    }

    public Gig createGig(GigCreationDto gigCreationDto) {
        Gig gig = new Gig();
        gig.setDateTime(gigCreationDto.getDateTime());
        gig.setDescription(gigCreationDto.getDescription());
        gig.setDateTime(gigCreationDto.getDateTime());

        return gigRepository.save(gig);
    }

    public GigPreviewDto viewGig(Long gigId) {
        Gig gig = gigRepository.findById(gigId).orElseThrow(() -> new GigerException(NO_SUCH_GIG));

        if (!gig.isPrivateGig() && gig.isFinalDealAchieved())
            return gig.toDto();

        if (userDetailsService.isLoggedUserOrganizer() && gig.getOrganizer().getId().equals(userDetailsService.getLoggedOrganizer().getId()))
            return gig.toDto();

        if (userDetailsService.isLoggedUserMusician() && bandRepository.findAllByMembersContaining(userDetailsService.getLoggedMusician()).stream().anyMatch(e -> e.getGigs().contains(gig)))
            return gig.toDto();

        if (!gig.isFinalDealAchieved() || gig.isPrivateGig())
            throw new GigerException(NO_SUCH_GIG);

        return gig.toDto();
    }
}
