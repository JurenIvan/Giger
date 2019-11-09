package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Instrument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicianInvitationsDto {

    private long id;
    private String name;
    private String pictureUrl;
    private String bio;
    private List<Instrument> instrumentList;
}
