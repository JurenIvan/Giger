package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationPreviewDto {

	private Long conversationId;
	private List<PersonPreviewDto> participantsId;
	private BandDto bandId;
	private String pictureUrl;
	private List<MessagePreview> messages;
}
