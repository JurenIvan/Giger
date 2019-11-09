package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.domain.Organizer;
import hr.fer.zemris.opp.giger.domain.enums.GigType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GigRepository extends JpaRepository<Gig, Long> {


    List<Gig> findAllByGigTypeAndPrivateGig(GigType gigType, Boolean isPrivate);

    List<Gig> findAllByFinalBandAndPrivateGig(Band band, boolean isPrivate);

    List<Gig> findAllByOrganizer(Organizer organizer);
}
