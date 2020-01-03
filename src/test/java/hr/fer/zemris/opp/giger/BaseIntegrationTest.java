package hr.fer.zemris.opp.giger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles(value = "local")
@AutoConfigureMockMvc
@DirtiesContext
public class BaseIntegrationTest {

	protected static final String JSON_BASEPATH = "src/main/resources/test_json_files";
	@Autowired
	protected ObjectMapper objectMapper;

	protected byte[] readJsonFile(String groupPath, String filename) {
		try {
			return Files.readString(Paths.get(JSON_BASEPATH, groupPath, filename)).getBytes();
		} catch (IOException e) {
			throw new IllegalArgumentException("No such file found.", e);
		}
	}

	@Test
	public void contextLoad() {
	}
}
