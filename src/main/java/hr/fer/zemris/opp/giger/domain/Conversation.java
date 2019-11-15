package hr.fer.zemris.opp.giger.domain;

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
import static javax.persistence.CascadeType.ALL;
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
    private Band band;


    @OneToMany(fetch = LAZY, cascade = ALL)
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
        return new ConversationPreviewDto(participants.stream().map(e -> e.toDto()).collect(toList()), band.toDto(), pictureUrl, messages.stream().map(e -> e.toDto()).collect(toList()));
    }
}
