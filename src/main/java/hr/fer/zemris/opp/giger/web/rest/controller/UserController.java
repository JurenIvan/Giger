package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Person;
import hr.fer.zemris.opp.giger.service.PeopleService;
import hr.fer.zemris.opp.giger.web.rest.dto.FindUsersDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private PeopleService peopleService;

    @PostMapping("/findUsers")
    private List<Person> findUsers(@RequestBody FindUsersDto findUsersDto) {
        return peopleService.findUsers(findUsersDto);
    }
}
