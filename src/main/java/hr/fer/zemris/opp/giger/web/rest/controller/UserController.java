package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.User;
import hr.fer.zemris.opp.giger.service.UserService;
import hr.fer.zemris.opp.giger.web.rest.dto.FindUsersDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @PostMapping("/findUsers")
    private List<User> findUsers(FindUsersDto findUsersDto) {
        return userService.findUsers(findUsersDto);
    }
}
