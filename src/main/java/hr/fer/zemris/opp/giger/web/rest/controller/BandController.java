package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Location;
import hr.fer.zemris.opp.giger.web.rest.dto.FilterBandDto;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController("/band")
public class BandController {

    public List<Band> listAvailableBands(LocalDate localDate, Location location) {
        return null;
    }

    public List<Band> filterBandsByFilters(FilterBandDto filterBandDto){
        return null;
    }
}
