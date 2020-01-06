package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Person;
import hr.fer.zemris.opp.giger.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findAllByAuthor(Person person);
}
