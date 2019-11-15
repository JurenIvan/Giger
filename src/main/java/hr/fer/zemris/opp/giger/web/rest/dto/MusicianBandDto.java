package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MusicianBandDto {

    private long bandId;
    private long musicianId;
}
