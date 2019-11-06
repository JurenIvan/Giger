package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
@Data
public class FilterBandDto {

    private HashMap<String, String> filters;

}
