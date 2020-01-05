package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.exception.ErrorCode;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.web.rest.dto.BandDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationPreviewDto;
import hr.fer.zemris.opp.giger.web.rest.dto.NewMessageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String title;
	private String pictureUrl;

	@ManyToMany
	private List<Person> participants;

	@ManyToOne(fetch = LAZY)
	private Band band;

	@OneToMany(cascade = MERGE)
	private List<Message> messages;

	public static Conversation createConversation(ConversationCreationDto other, Person creator) {
		return new Conversation(null, other.getTitle(), other.getPictureUrl(), List.of(creator), null, null);
	}

	public void removeParticipants(Person person) {
		participants.remove(person);
	}

	public void addMessage(NewMessageDto newMessageDto, Person sender, Band senderBand) {
		this.messages.add(new Message(null, newMessageDto.getContent(), LocalDateTime.now(), sender, senderBand));
	}

	public ConversationPreviewDto toDto() {
		BandDto bandDto = band != null ? band.toDto() : null;
		return new ConversationPreviewDto(participants.stream().map(Person::toDto).collect(toList()), bandDto, pictureUrl, messages.stream().map(Message::toDto).collect(toList()));
	}


	public void addMembers(List<Person> people, Band band) {
		if (people != null)
			this.participants.addAll(people);

		if (this.band == null)
			this.band = band;
		else
			throw new GigerException(ErrorCode.CONVERSATION_ALREADY_HAS_BAND);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Conversation)) return false;
		Conversation conversation = (Conversation) o;
		return Objects.equals(id, conversation.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
