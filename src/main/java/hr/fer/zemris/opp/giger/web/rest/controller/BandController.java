package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.service.BandService;
import hr.fer.zemris.opp.giger.web.rest.dto.BandDto;
import hr.fer.zemris.opp.giger.web.rest.dto.FilterBandDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/bands")
public class BandController {

    private BandService bandService;

    @GetMapping("/like/{name}")
    public List<BandDto> getBands(@PathVariable String name) {
        return bandService.listBands(name);
    }

    @PostMapping("/filter") //todo improve
    public List<BandDto> filterBandsByFilters(FilterBandDto filterBandDto) {
        return bandService.listAvailableBands(filterBandDto);
    }
}
