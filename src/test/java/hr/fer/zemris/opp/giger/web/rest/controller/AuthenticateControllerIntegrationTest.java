package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.BaseIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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

public class AuthenticateControllerIntegrationTest extends BaseIntegrationTest {

    private static final String AUTHENTICATE = "/authenticate";
    private static final String JSON_GROUP = "login";
    private static final String JSON_PATH_LOGIN = "login_request_dto.txt";
    private static final String JSON_PATH_LOGIN_ADMIN = "admin_login_request_dto";
    private final static String ROLES_CHECK = "/current-roles";

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() { mockMvc = webAppContextSetup(context).apply(springSecurity()).build();  }

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

        //assertEquals(response, HttpStatus.OK);
        assertEquals(response.getContentAsString(), "OK");

    }

    @Test
    @DirtiesContext
    public void loginAsAdmin_getRoles_succes() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(
                post(AUTHENTICATE)
                        .contentType(APPLICATION_JSON_VALUE)
                        .with(csrf())
                        .content(readJsonFile(JSON_GROUP, JSON_PATH_LOGIN_ADMIN)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertEquals(response, HttpStatus.OK);

        response = mockMvc.perform(
                get(ROLES_CHECK))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

        assertEquals(response.getContentAsString(), "MUSICIAN, PERSON, ORGANIZER");
    }

    @Test
    @DirtiesContext
    public void loginNobody_getRoles_throwException() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(
                get(ROLES_CHECK))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        //assertEquals(response.getContentAsString(), "");
    }
}
