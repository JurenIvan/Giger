package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.enums.GigType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BandPreviewDto {

    private long id;
    private String name;
    private String pictureURl;
    private List<GigType> gigTypes;

}
