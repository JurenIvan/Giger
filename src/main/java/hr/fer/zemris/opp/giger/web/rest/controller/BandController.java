package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Location;
import hr.fer.zemris.opp.giger.web.rest.dto.FilterBandDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bands")
public class BandController {

    @PostMapping("/available")
    public List<Band> listAvailableBands(LocalDate localDate, Location location) {
        return null;
    }

    @PostMapping("/filter")
    public List<Band> filterBandsByFilters(FilterBandDto filterBandDto) {
        return null;
    }
}
