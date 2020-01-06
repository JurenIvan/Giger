package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	@NotNull
	private String content;
	@NotNull
	private LocalDateTime postedOn;

	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private Person author;

	public CommentDto toDto() {
		return new CommentDto(id, content, postedOn, author.toDto());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Comment)) return false;
		Comment comment = (Comment) o;
		return Objects.equals(id, comment.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

