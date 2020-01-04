package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {

	List<Band> findAllByNameLike(String name);

	Optional<Band> findByName(String name);

	List<Band> findAll();

	List<Band> findAllByMembersContaining(Musician musician);

	Optional<Band> findByPostsContaining(Post post);
}
