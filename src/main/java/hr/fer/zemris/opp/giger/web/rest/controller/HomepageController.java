package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.service.GigService;
import hr.fer.zemris.opp.giger.web.rest.dto.RequestGigsDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class HomepageController {

    @Autowired
    private GigService gigService;

    //probably useless
    @PostMapping("/gigs-by-type")
    public List<Gig> listGigsByType(RequestGigsDto requestGigsDto) {
        return gigService.listGigsByType(requestGigsDto.getGigType());
    }

    //probably useless
    @PostMapping("/gigs-by-band")
    public List<Gig> listGigsByBand(RequestGigsDto requestGigsDto) {
        return gigService.listGigsByBand(requestGigsDto.getBand());
    }
}
