package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicianProfileDto {

	private String name;
	private List<Long> instrumentList;
	private String pictureUrl;
	private String contactNumber;
	private List<Post> postList;
}
