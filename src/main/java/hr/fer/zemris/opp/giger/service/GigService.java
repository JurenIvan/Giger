package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.domain.Organizer;
import hr.fer.zemris.opp.giger.domain.enums.GigType;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.repository.BandRepository;
import hr.fer.zemris.opp.giger.repository.GigRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.GigCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.GigPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.NO_SUCH_BAND;
import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.NO_SUCH_GIG;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class GigService {

    private GigRepository gigRepository;
    private BandRepository bandRepository;
    private UserDetailsServiceImpl userDetailsService;

    public List<Gig> listGigsByType(GigType gigType) {
        return gigRepository.findAllByGigTypeAndPrivateGig(gigType, false);
    }

    public List<GigPreviewDto> listGigsByBand(Long bandId) {
        return bandRepository.findById(bandId).orElseThrow(() -> new GigerException(NO_SUCH_BAND)).getGigs().stream().map(Gig::toDto).collect(toList());
    }

    public Gig createGig(GigCreationDto gigCreationDto) {
        Organizer organizer = userDetailsService.getLoggedOrganizer();

        Gig gig = new Gig();
        gig.setDateTime(gigCreationDto.getDateTime());
        gig.setLocation(gigCreationDto.getLocation());
        gig.setDescription(gigCreationDto.getDescription());
        gig.setExpectedDuration(gigCreationDto.getExpectedDuration());
        gig.setProposedPrice(gigCreationDto.getProposedPrice());
        gig.setGigType(gigCreationDto.getGigType());
        gig.setPrivateGig(gigCreationDto.getPrivateGig());
        gig.setName(gigCreationDto.getGigName());
        gig.setOrganizer(organizer);

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
