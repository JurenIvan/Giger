package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {

    List<Band> findAllByNameLike(String name);

    Optional<Band> findByName(String name);
}
