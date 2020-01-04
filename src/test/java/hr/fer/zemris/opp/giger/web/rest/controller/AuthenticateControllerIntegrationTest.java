package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.BaseIntegrationTest;
import hr.fer.zemris.opp.giger.config.security.JwtUtil;
import hr.fer.zemris.opp.giger.config.security.model.AuthenticationResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class AuthenticateControllerIntegrationTest extends BaseIntegrationTest {

	private static final String AUTHENTICATE = "/authenticate";
	private final static String ROLES_CHECK = "/current-roles";

	private static final String JSON_GROUP = "login";
	private static final String JSON_PATH_LOGIN = "login_request_dto.txt";
	private static final String JSON_PATH_LOGIN_ADMIN = "admin_login_request_dto.txt";
	private static final String JSON_PATH_LOGIN_NO_ROLES = "login_request_dto_no_roles.txt";

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private JwtUtil jwtUtil;

	@Before
	public void setUp() {
		mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	@DirtiesContext
	public void login_allOk_success() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(
				post(AUTHENTICATE)
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_LOGIN)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		AuthenticationResponseDto authenticationResponseDto = objectMapper.readValue(response.getContentAsString(), AuthenticationResponseDto.class);

		assertEquals("john.doe@giger.com", jwtUtil.extractEmail(authenticationResponseDto.getToken()));
		assertTrue(jwtUtil.extractExpiration(authenticationResponseDto.getToken()).after(new Date()));
	}

	@Test
	@DirtiesContext
	public void loginAsAdmin_getRoles_succes() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(
				post(AUTHENTICATE)
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_LOGIN_ADMIN)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		AuthenticationResponseDto authenticationResponseDto = objectMapper.readValue(response.getContentAsString(), AuthenticationResponseDto.class);

		assertEquals("admin@giger.com", jwtUtil.extractEmail(authenticationResponseDto.getToken()));
		assertTrue(jwtUtil.extractExpiration(authenticationResponseDto.getToken()).after(new Date()));

		response = mockMvc.perform(
				get(ROLES_CHECK)
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.header(AUTHENTICATION_HEADER, "Bearer " + authenticationResponseDto.getToken())
						.accept(APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		assertEquals(response.getContentAsString(), "[\"MUSICIAN\",\"PERSON\",\"ORGANIZER\"]");
	}

	@Test
	@DirtiesContext
	public void loginJustPerson_getRoles_throwException() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(
				post(AUTHENTICATE)
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.content(readJsonFile(JSON_GROUP, JSON_PATH_LOGIN_NO_ROLES)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		AuthenticationResponseDto authenticationResponseDto = objectMapper.readValue(response.getContentAsString(), AuthenticationResponseDto.class);

		assertEquals("david.doe@giger.com", jwtUtil.extractEmail(authenticationResponseDto.getToken()));
		assertTrue(jwtUtil.extractExpiration(authenticationResponseDto.getToken()).after(new Date()));

		response = mockMvc.perform(
				get(ROLES_CHECK)
						.contentType(APPLICATION_JSON_VALUE)
						.with(csrf())
						.header(AUTHENTICATION_HEADER, "Bearer " + authenticationResponseDto.getToken())
						.accept(APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

		assertEquals(response.getContentAsString(), "[\"PERSON\"]");
	}
}
