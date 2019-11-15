package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Conversation;
import hr.fer.zemris.opp.giger.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    List<Conversation> findAllByParticipantsContaining(Person person);

    List<Conversation> findAllByParticipantsContainingOrBandIn(Person person, List<Band> bands);

    List<Conversation> findAllByBandIn(List<Band> bands);

    Optional<Conversation> findConversationById(long id);

    List<Conversation> findAllByBand(Band band);
}
