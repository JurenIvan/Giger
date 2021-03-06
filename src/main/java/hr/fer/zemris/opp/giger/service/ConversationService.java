package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Conversation;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.Person;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.repository.BandRepository;
import hr.fer.zemris.opp.giger.repository.ConversationRepository;
import hr.fer.zemris.opp.giger.repository.PersonRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.AddToConversationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ConversationPreviewDto;
import hr.fer.zemris.opp.giger.web.rest.dto.NewMessageDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.*;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ConversationService {

	private ConversationRepository conversationRepository;
	private UserDetailsServiceImpl userDetailsService;
	private PersonRepository personRepository;
	private BandRepository bandRepository;

	public long createConversation(ConversationCreationDto conversationCreationDto) {
		Person person = userDetailsService.getLoggedPerson();
		return conversationRepository.save(Conversation.createConversation(conversationCreationDto, person)).getId();
	}

	public ConversationPreviewDto loadConversation(Long conversationId) {
		Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new GigerException(NO_SUCH_CONVERSATION));

		Person loggedInPerson = userDetailsService.getLoggedPerson();

		if (conversation.getParticipants().contains(loggedInPerson))
			return conversation.toDto();

		Musician loggedInMusician = userDetailsService.getLoggedMusician();
		if (conversation.getBand() != null && conversation.getBand().getMembers().contains(loggedInMusician))
			return conversation.toDto();

		throw new GigerException(NOT_IN_A_CONVERSATION);
	}

	public void postMessageAsPerson(NewMessageDto newMessageDto) {
		Person person = userDetailsService.getLoggedPerson();
		Conversation conversation = conversationRepository.findById(newMessageDto.getConversationId()).orElseThrow(() -> new GigerException(NO_SUCH_CONVERSATION));

		if (!conversation.getParticipants().contains(person)) {
			throw new GigerException(NOT_IN_A_CONVERSATION);
		}
		conversation.addMessage(newMessageDto, person, null);
		conversationRepository.save(conversation);
	}

	//todo test
	public void postMessageAsBand(NewMessageDto newMessageDto) {
		Musician musician = userDetailsService.getLoggedMusician();
		Conversation conversation = conversationRepository.findById(newMessageDto.getConversationId()).orElseThrow(() -> new GigerException(NO_SUCH_CONVERSATION));

		if (conversation.getBand() != null && conversation.getBand().getMembers().contains(musician)) {
			conversation.addMessage(newMessageDto, null, conversation.getBand());
			conversationRepository.save(conversation);
			return;
		}
		throw new GigerException(NOT_IN_A_CONVERSATION);
	}

	public List<ConversationPreviewDto> loadAllPersonalConversations() {
		Person person = userDetailsService.getLoggedPerson();
		return conversationRepository.findAllByParticipantsContaining(person).stream().map(Conversation::toDto).collect(toList());
	}

	public List<ConversationPreviewDto> loadAllBandsConversations() {
		Musician musician = userDetailsService.getLoggedMusician();
		List<Band> bands = bandRepository.findAllByMembersContaining(musician);
		return conversationRepository.findAllByBandIn(bands).stream().map(Conversation::toDto).collect(toList());
	}

	public List<ConversationPreviewDto> loadAllBandConversations(long bandId) {
		Musician musician = userDetailsService.getLoggedMusician();
		Band band = bandRepository.findById(bandId).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		if (!band.getMembers().contains(musician))
			throw new GigerException(NOT_A_MEMBER_OF_BAND);
		return conversationRepository.findAllByBand(band).stream().map(Conversation::toDto).collect(toList());
	}

	public void leaveConversation(Long conversationId) {
		Person person = userDetailsService.getLoggedPerson();
		Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new GigerException(NO_SUCH_CONVERSATION));
		if (!conversation.getParticipants().contains(person))
			throw new GigerException(NOT_A_MEMBER_OF_BAND);

		conversation.removeParticipants(person);
		conversationRepository.save(conversation);
	}

	public ConversationPreviewDto addToConversation(Long conversationId, AddToConversationDto addToConversationDto) {
		Person person = userDetailsService.getLoggedPerson();
		Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new GigerException(NO_SUCH_CONVERSATION));
		if (!conversation.getParticipants().contains(person))
			throw new GigerException(NOT_A_MEMBER_OF_BAND);


		Band band = null;
		List<Person> people = null;
		if (addToConversationDto.getBandId() != null)
			band = bandRepository.findById(addToConversationDto.getBandId()).orElse(null);
		if (addToConversationDto.getUserIds() != null) {
			people = personRepository.findAllByIdIn(addToConversationDto.getUserIds());
		}
		conversation.addMembers(people, band);

		return conversationRepository.save(conversation).toDto();
	}
}
