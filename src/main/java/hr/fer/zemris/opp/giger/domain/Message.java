package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.BandDto;
import hr.fer.zemris.opp.giger.web.rest.dto.MessagePreview;
import hr.fer.zemris.opp.giger.web.rest.dto.PersonPreviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

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

	@ManyToOne
	private Person sender;

	@ManyToOne
	private Band senderBand;

	public MessagePreview toDto() {
		PersonPreviewDto personPreviewDto = sender != null ? sender.toDto() : null;
		BandDto bandDto = senderBand != null ? senderBand.toDto() : null;

		return new MessagePreview(id, content, sentTime, personPreviewDto, bandDto);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Message)) return false;
		Message message = (Message) o;
		return Objects.equals(id, message.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
