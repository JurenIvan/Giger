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

	List<Band> findAllByNameContaining(String name);

	Optional<Band> findByName(String name);

	List<Band> findAll();

	List<Band> findAllByMembersContaining(Musician musician);

	Optional<Band> findByPostsContaining(Post post);
// todo
//	@Query(value = "SELECT " +
//			"FROM band " +
//			"JOIN band_occasions bo ON band.id = bo.band_id " +
//			"JOIN occasion o ON bo.occasions_id = o.id " +
//			"where YEAR(o.local_date_time) != YEAR(:localDate) \n" +
//			"    AND MONTH(o.local_date_time) != MONTH(:localDate)\n" +
//			"    AND DAY(o.local_date_time) != DAY(:localDate)"
//			, nativeQuery = true)
//	List<Band> findAllAvailableBands(LocalDate localDate);

	List<Band> findAllByInvitedContaining(Musician musician);

	List<Band> findAllByInvitedBackUpMembersContaining(Musician musician);
}
