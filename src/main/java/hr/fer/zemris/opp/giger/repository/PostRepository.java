package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
