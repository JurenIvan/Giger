package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConversationPreviewDto {

    private List<PersonPreviewDto> participantsId;
    private BandPreviewDto bandId;
    private String pictureUrl;
    private List<MessagePreview> messages;

}
