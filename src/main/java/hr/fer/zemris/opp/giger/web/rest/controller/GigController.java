package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.service.GigService;
import hr.fer.zemris.opp.giger.web.rest.dto.GigCreationDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@AllArgsConstructor
@RestController("/gig")
public class GigController {

    private GigService gigService;

    @PostMapping("/create-gig")
//    @PreAuthorize("hasPermission(null, 'USER')")
    public Gig createGig(@Valid @RequestBody GigCreationDto gigCreationDto) {
        return gigService.createGig(gigCreationDto);
    }

    public Gig viewGig(Gig gig) {
        return null;
    }

}
