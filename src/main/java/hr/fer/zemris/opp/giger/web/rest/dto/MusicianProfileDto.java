package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Instrument;
import hr.fer.zemris.opp.giger.domain.Occasion;
import hr.fer.zemris.opp.giger.domain.Post;
import hr.fer.zemris.opp.giger.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicianProfileDto {

    private String name;
    private List<Instrument> instrumentList;
    private String pictureUrl;
    private List<Occasion> occasions;
    private List<ReviewsDto> reviews;
    private List<Post> posts;

}
