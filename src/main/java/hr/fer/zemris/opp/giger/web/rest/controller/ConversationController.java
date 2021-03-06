package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.exception.GigerValidationException;
import hr.fer.zemris.opp.giger.service.ConversationService;
import hr.fer.zemris.opp.giger.web.rest.dto.AddToConversationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationPreviewDto;
import hr.fer.zemris.opp.giger.web.rest.dto.NewMessageDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/conversations")
@AllArgsConstructor
public class ConversationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConversationController.class);
	private ConversationService conversationService;

	@PostMapping("/create")
	public long createConversation(@Valid @RequestBody ConversationCreationDto conversationCreationDto, BindingResult bindingResult) {
		LOGGER.info("CreateConversation: " + conversationCreationDto);
		handleValidation(bindingResult);
		return conversationService.createConversation(conversationCreationDto);
	}

	@GetMapping("{conversationId}")
	public ConversationPreviewDto loadConversation(@PathVariable @Min(1) Long conversationId) {
		LOGGER.info("LoadConversation: " + conversationId);
		return conversationService.loadConversation(conversationId);
	}

	@PostMapping("/send/person")
	public void postMessageAsPerson(@Valid @RequestBody NewMessageDto newMessageDto, BindingResult bindingResult) {
		LOGGER.info("PostMessageAsPerson: " + newMessageDto);
		handleValidation(bindingResult);
		conversationService.postMessageAsPerson(newMessageDto);
	}

	@PostMapping("/send/band")
	public void postMessageAsBand(@Valid @RequestBody NewMessageDto newMessageDto, BindingResult bindingResult) {
		LOGGER.info("PostMessageAsBand: " + newMessageDto);
		handleValidation(bindingResult);
		conversationService.postMessageAsBand(newMessageDto);
	}

	@GetMapping("/get/personal")
	public List<ConversationPreviewDto> loadAllConversations() {
		LOGGER.info("LoadAllConversations: ");
		return conversationService.loadAllPersonalConversations();
	}

	@GetMapping("/get/band/{bandId}")
	public List<ConversationPreviewDto> loadAllBandConversations(@PathVariable @Min(1) Long bandId) {
		LOGGER.info("LoadAllBandConversations: " + bandId);
		return conversationService.loadAllBandConversations(bandId);
	}

	@GetMapping("/get/bands")
	public List<ConversationPreviewDto> loadAllBandsConversations() {
		LOGGER.info("LoadAllBandsConversations");
		return conversationService.loadAllBandsConversations();
	}

	@GetMapping("/leave/{conversationId}")
	public void leaveConversation(@PathVariable @Min(1) Long conversationId) {
		LOGGER.info("LeaveConversation: " + conversationId);
		conversationService.leaveConversation(conversationId);
	}

	@PostMapping("/add/{conversationId}")
	public ConversationPreviewDto addToConversation(@PathVariable @Min(1) Long conversationId, @Valid @RequestBody AddToConversationDto addToConversationDto) {
		LOGGER.info("AddToConversation: " + addToConversationDto + "   conversationId: " + conversationId);
		return conversationService.addToConversation(conversationId, addToConversationDto);
	}

	private void handleValidation(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.warn("Validation Exception: " + bindingResult.getAllErrors());
			throw new GigerValidationException(bindingResult);
		}
	}
}
