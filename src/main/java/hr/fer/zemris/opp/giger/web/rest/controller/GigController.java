package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.service.GigService;
import hr.fer.zemris.opp.giger.web.rest.dto.GigCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.GigPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping("/gigs")
public class GigController {

    private GigService gigService;

    @PostMapping("/create-gig")
    @PreAuthorize("hasPermission('ORGANIZER')")
    public Gig createGig(@Valid @RequestBody GigCreationDto gigCreationDto) {
        return gigService.createGig(gigCreationDto);
    }

    @GetMapping("/{gigId}")
    public GigPreviewDto viewGig(@PathVariable Long gigId) {
        return gigService.viewGig(gigId);
    }
}
