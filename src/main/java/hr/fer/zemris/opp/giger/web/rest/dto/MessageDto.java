package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessageDto {

    private Long id;
    private String content;
    private LocalDateTime sentTime;
    private String sender;
    private String senderBand;
    private String senderPictureUrl;

    public MessageDto(Long id, String content, LocalDateTime sentTime, String sender, String senderPictureUrl) {
        this.id = id;
        this.content = content;
        this.sentTime = sentTime;
        this.sender = sender;
        this.senderPictureUrl = senderPictureUrl;
    }

    public MessageDto(Long id, String content, String senderBand, LocalDateTime sentTime, String senderPictureUrl) {
        this.id = id;
        this.content = content;
        this.sentTime = sentTime;
        this.senderBand = senderBand;
        this.senderPictureUrl = senderPictureUrl;
    }

}
