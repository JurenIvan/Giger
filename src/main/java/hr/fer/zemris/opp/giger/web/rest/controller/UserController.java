package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Person;
import hr.fer.zemris.opp.giger.service.PeopleService;
import hr.fer.zemris.opp.giger.web.rest.dto.FindUsersDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private PeopleService peopleService;

    @PostMapping("/findUsers")
    private List<Person> findUsers(FindUsersDto findUsersDto) {
        return peopleService.findUsers(findUsersDto);
    }
}
