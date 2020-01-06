package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.BaseIntegrationTest;
import hr.fer.zemris.opp.giger.domain.exception.ApiError;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class RegistrationControllerIntegrationTest extends BaseIntegrationTest {

	private static final String API_LINK_REGISTER = "/register";
	private static final String JSON_GROUP = "registration";
	private static final String JSON_PATH_REGISTER = "registration_request_dto.txt";
	private static final String NICKNAME_CHECK = "/nickname-available";
	private static final String EMAIL_CHECK = "/email-available";

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	@DirtiesContext
	public void registering_allOk_success() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(
				post(API_LINK_REGISTER)
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_REGISTER)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		assertEquals(response.getContentAsString(), "Registration ok!");
	}

	@Test
	@DirtiesContext
	public void registering_usedEmail_throwException() throws Exception {
		MockHttpServletResponse response;
		response = mockMvc.perform(
				post(API_LINK_REGISTER)
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_REGISTER)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		assertEquals(response.getContentAsString(), "Registration ok!");

		response = mockMvc.perform(
				post(API_LINK_REGISTER)
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_REGISTER)))
				.andExpect(status().isBadRequest())
				.andReturn()
				.getResponse();

		ApiError apiError = objectMapper.readValue(response.getContentAsString(), ApiError.class);

		assertEquals(apiError.getViolationErrors().get(0).getCode(), 40023);
	}

	@Test
	@DirtiesContext
	public void isNicknameAvailable_itIsThenNot_response() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(
				get(API_LINK_REGISTER + NICKNAME_CHECK)
						.param("nickname", "jurenivan")
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_REGISTER)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		assertEquals(response.getContentAsString(), "true");

		response = mockMvc.perform(
				post(API_LINK_REGISTER)
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_REGISTER)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		assertEquals(response.getContentAsString(), "Registration ok!");

		response = mockMvc.perform(
				get(API_LINK_REGISTER + NICKNAME_CHECK)
						.param("nickname", "jurenivan")
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_REGISTER)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		assertEquals(response.getContentAsString(), "false");
	}

	@Test
	@DirtiesContext
	public void isEmailAvailable_itIsThenNot_response() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(
				get(API_LINK_REGISTER + EMAIL_CHECK)
						.param("email", "juren.ivan1@gmail.com")
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_REGISTER)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		assertEquals(response.getContentAsString(), "true");

		response = mockMvc.perform(
				post(API_LINK_REGISTER)
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_REGISTER)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		assertEquals(response.getContentAsString(), "Registration ok!");

		response = mockMvc.perform(
				get(API_LINK_REGISTER + EMAIL_CHECK)
						.param("email", "juren.ivan1@gmail.com")
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_REGISTER)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		assertEquals(response.getContentAsString(), "false");
	}
}
