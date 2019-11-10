package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Conversation;
import hr.fer.zemris.opp.giger.domain.Message;
import hr.fer.zemris.opp.giger.service.ConversationService;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversations")
@AllArgsConstructor
public class ConversationController {

    private ConversationService conversationService;

    @PostMapping("/create")
    public long createConversation(@RequestBody ConversationCreationDto conversationCreationDto) {
        return conversationService.createConversation(conversationCreationDto);
    }

    @PostMapping("{conversationId}")
    public ConversationPreviewDto loadConversation(@PathVariable Long conversationId) {
        return conversationService.loadConversation(conversationId);
    }

    @PostMapping("/send/conversationId")
    public void postMessage(Message message, Conversation conversation) {
    }

    @GetMapping("/get")
    public List<ConversationPreviewDto> loadAllConversations() {
        return null;
    }

    @GetMapping("/leave/{conversationId}")
    public void leaveConversation(Conversation conversation) {
    }
}
