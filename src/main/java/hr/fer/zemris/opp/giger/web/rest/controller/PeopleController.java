package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Person;
import hr.fer.zemris.opp.giger.service.PeopleService;
import hr.fer.zemris.opp.giger.web.rest.dto.FindUsersDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class PeopleController {

    private PeopleService peopleService;

    @PostMapping("/findUsers")
    private List<Person> findUsers(@RequestBody FindUsersDto findUsersDto) {
        return peopleService.findPeople(findUsersDto);
    }

    @GetMapping("/reviews/{personId}")
    public List<ReviewPreviewDto> listReviewsForPerson(@PathVariable Long personId) {
        return peopleService.getReviews(personId);
    }
}
