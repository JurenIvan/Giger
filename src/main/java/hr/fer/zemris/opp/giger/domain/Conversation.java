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

	@ManyToMany(fetch = LAZY)
	@JoinTable(name = "conversation_user",
			joinColumns = {@JoinColumn(name = "fk_conversation")},
			inverseJoinColumns = {@JoinColumn(name = "fk_user")})
	private List<Person> participants;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "fk_band")
	private Band band; //todo add list of bands?


	@OneToMany(fetch = LAZY, cascade = MERGE)
	@JoinColumn(name = "fk_conversation")
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
}
