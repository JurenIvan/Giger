package hr.fer.zemris.opp.giger.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Post {

    private String content;
    private LocalDateTime publishedOn;

    private List<Comment> commentList;
}
