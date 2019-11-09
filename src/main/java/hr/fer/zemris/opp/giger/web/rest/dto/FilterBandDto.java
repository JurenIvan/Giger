package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;

@NoArgsConstructor
@Data
public class FilterBandDto {

    private LocalDate specificDateFirst;
    private LocalDate specificDateSecond;

}
