package hr.fer.zemris.opp.giger.web.rest.dto;


import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.enums.GigType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestGigsDto {

    private GigType gigType;
    private Band band;

}
