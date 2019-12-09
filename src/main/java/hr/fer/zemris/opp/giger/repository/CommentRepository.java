package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
