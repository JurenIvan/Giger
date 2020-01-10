package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.service.ConversationService;
import hr.fer.zemris.opp.giger.web.rest.dto.AddToConversationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationPreviewDto;
import hr.fer.zemris.opp.giger.web.rest.dto.NewMessageDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/conversations")
@AllArgsConstructor
public class ConversationController {

	private ConversationService conversationService;

	@PostMapping("/create")
	public long createConversation(@Valid @RequestBody ConversationCreationDto conversationCreationDto) {
		return conversationService.createConversation(conversationCreationDto);
	}

	@GetMapping("{conversationId}")
	public ConversationPreviewDto loadConversation(@PathVariable @Min(1) Long conversationId) {
		return conversationService.loadConversation(conversationId);
	}

	@PostMapping("/send/person")
	public void postMessageAsPerson(@Valid @RequestBody NewMessageDto newMessageDto) {
		conversationService.postMessageAsPerson(newMessageDto);
	}

	@PostMapping("/send/band")
	public void postMessageAsBand(@Valid @RequestBody NewMessageDto newMessageDto) {
		conversationService.postMessageAsBand(newMessageDto);
	}

	@GetMapping("/get/personal")
	public List<ConversationPreviewDto> loadAllConversations() {
		return conversationService.loadAllPersonalConversations();
	}

	@GetMapping("/get/band/{bandId}")
	public List<ConversationPreviewDto> loadAllBandConversations(@PathVariable @Min(1) Long bandId) {
		return conversationService.loadAllBandConversations(bandId);
	}

	@GetMapping("/get/bands")
	public List<ConversationPreviewDto> loadAllBandsConversations() {
		return conversationService.loadAllBandsConversations();
	}

	@GetMapping("/leave/{conversationId}")
	public void leaveConversation(@PathVariable @Min(1) Long conversationId) {
		conversationService.leaveConversation(conversationId);
	}

	@PostMapping("/add/{conversationId}")
	public ConversationPreviewDto addToConversation(@PathVariable @Min(1) Long conversationId, @Valid @RequestBody AddToConversationDto addToConversationDto) {
		return conversationService.addToConversation(conversationId, addToConversationDto);
	}

}
