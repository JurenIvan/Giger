package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.MessagePreview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.MERGE;
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
    private Person sender;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "fk_sender_band")
    private Band senderBand;


    public MessagePreview toDto() {
        return new MessagePreview(id, content, sentTime, sender.toDto(), senderBand.toDto());
    }
}
