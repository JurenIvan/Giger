package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class ConversationDto {

    private List<String> participants;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private String pictureUrl;

}
