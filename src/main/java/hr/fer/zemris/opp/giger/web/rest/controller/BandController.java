package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.service.BandService;
import hr.fer.zemris.opp.giger.web.rest.dto.BandPreviewDto;
import hr.fer.zemris.opp.giger.web.rest.dto.FilterBandDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bands")
public class BandController {

    private BandService bandService;

    @GetMapping("/like/{name}")
    public List<BandPreviewDto> getBands(@PathVariable String name) {
        return bandService.listBands(name);
    }

    @PostMapping("/filter")
    public List<BandPreviewDto> filterBandsByFilters(FilterBandDto filterBandDto) {
        return bandService.listAvailableBands(filterBandDto);
    }
}
