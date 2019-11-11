package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.domain.enums.GigType;
import hr.fer.zemris.opp.giger.repository.GigRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.GigCreationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GigService {

    private GigRepository gigRepository;

    public List<Gig> listGigsByType(GigType gigType) {
        return gigRepository.findAllByGigTypeAndPrivateGig(gigType, false);
    }

    public List<Gig> listGigsByBand(Band band) {
        return gigRepository.findAllByFinalBandAndPrivateGig(band, false);
    }

    public Gig createGig(GigCreationDto gigCreationDto) {
        Gig gig = new Gig();
        gig.setDateTime(gigCreationDto.getDateTime());
        gig.setDescription(gigCreationDto.getDescription());
        gig.setDateTime(gigCreationDto.getDateTime());

        return gigRepository.save(gig);
    }
}
