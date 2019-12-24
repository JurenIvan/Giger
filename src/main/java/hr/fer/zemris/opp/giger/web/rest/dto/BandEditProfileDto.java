package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BandEditProfileDto {

    private Long bandId;
    private String bio;
    private String pictureUrl;
    private Location location;
    private List<Long> removePostIds;
    private Long maxDistance;
}
