package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {

	Optional<Musician> findByPostsContaining(Post post);
}
