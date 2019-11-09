package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Conversation;
import hr.fer.zemris.opp.giger.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    List<Conversation> findAllByParticipantsContaining(Person person);
}
