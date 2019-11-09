package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    private String content;
    @NotNull
    private LocalDateTime sentTime;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "fk_sender")
    @NotNull
    private Person sender;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "fk_sender_band")
    @NotNull
    private Band senderBand;


    public MessageDto toMessageDto() {
        if (sender != null) {
            return new MessageDto(id, content, sentTime, sender.getUsername(), sender.getPictureUrl());
        } else {
            return new MessageDto(id, content, senderBand.getName(), sentTime, sender.getPictureUrl());
        }
    }
}
