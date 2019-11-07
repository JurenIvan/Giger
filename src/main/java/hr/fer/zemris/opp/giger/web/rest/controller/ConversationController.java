package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Conversation;
import hr.fer.zemris.opp.giger.domain.Message;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    @PostMapping("/create")
    public Conversation createConversation() {
        return null;
    }

    @GetMapping("{conversationId}")
    public Conversation loadConversation(Conversation conversation) {
        return null;
    }

    @PostMapping("/send/conversationId")
    public void postMessage(Message message, Conversation conversation) {
    }

    @GetMapping("/get")
    public List<ConversationDto> loadAllConversations() {
        return null;
    }

    @GetMapping("/leave/{conversationId}")
    public void leaveConversation(Conversation conversation) {
    }
}
