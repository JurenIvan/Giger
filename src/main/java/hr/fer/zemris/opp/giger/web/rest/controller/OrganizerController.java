package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.service.OrganizerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/organizers")
public class OrganizerController {

    private OrganizerService organizerService;

    @PostMapping("/create/{managerName}")
    public void create(@PathVariable String managerName) {
        organizerService.createOrganizer(managerName);
    }

    @PostMapping("/edit/{managerName}")
    public void edit(@PathVariable String managerName) {
        organizerService.editOrganizer(managerName);
    }
}
