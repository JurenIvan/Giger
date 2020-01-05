package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.BandDto;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianProfileDto;
import hr.fer.zemris.opp.giger.web.rest.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	@NotNull
	private String content;
	@NotNull
	private LocalDateTime publishedOn;

	@OneToMany(fetch = EAGER, cascade = MERGE)
	@JoinColumn(name = "fk_post")
	private List<Comment> comments;

	public PostDto toDto(MusicianProfileDto author, BandDto bandAuthor) {
		return new PostDto(id, content, publishedOn, comments.stream().map(Comment::toDto).collect(toList()), author, bandAuthor);
	}

	public static Post createPost(String content) {
		return new Post(null, content, LocalDateTime.now(), null);
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(!(o instanceof Post)) return false;
		Post post = (Post) o;
		return id.equals(post.getId());
	}

	@Override
	public int hashCode(){ return Objects.hash(id); }
}
