package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.CommentPreviewDto;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
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

    public CommentPreviewDto toDto() {
        return new CommentPreviewDto(id, content, postedOn, author.toDto());
    }
}
