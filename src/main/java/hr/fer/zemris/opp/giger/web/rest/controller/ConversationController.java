package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Conversation;
import hr.fer.zemris.opp.giger.domain.Message;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConversationController {

    public Conversation createConversation() {
        return null;
    }

    public Conversation loadConversation(Conversation conversation) {
        return null;
    }

    public void postMessage(Message message, Conversation conversation) {

    }

    public List<ConversationDto> loadAllConversations() {
        return null;
    }

    public void leaveConversation(Conversation conversation){

    }


}
