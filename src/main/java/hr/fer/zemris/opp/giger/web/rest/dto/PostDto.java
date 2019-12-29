package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private Long id;
	private String content;
	private LocalDateTime publishedOn;
	private List<CommentDto> comments;
	private MusicianProfileDto musicianAuthor;
	private BandDto bandAuthor;
}
