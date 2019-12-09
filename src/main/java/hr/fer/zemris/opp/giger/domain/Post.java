package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.PostPreviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;
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

    public PostPreviewDto toDto() {
        return new PostPreviewDto(id, content, publishedOn, comments.stream().map(e -> e.toDto()).collect(toList()));
    }
}
