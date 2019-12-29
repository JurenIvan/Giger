package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Location;
import hr.fer.zemris.opp.giger.domain.enums.GigType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GigCreationDto {

    private LocalDateTime dateTime;
    private Location location;
    private String description;
    private String expectedDuration;
    private Integer proposedPrice;
    private GigType gigType;
    private Boolean privateGig;
    private String gigName;
}
