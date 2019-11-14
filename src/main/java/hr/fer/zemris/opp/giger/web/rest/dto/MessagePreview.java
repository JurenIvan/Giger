package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePreview {

    private Long id;
    private String content;
    private LocalDateTime sentTime;
    private PersonPreviewDto sender;
    private BandPreviewDto senderBand;

}
