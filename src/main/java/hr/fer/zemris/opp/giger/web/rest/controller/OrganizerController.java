package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.service.OrganizerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/organizers")
public class OrganizerController {

    private OrganizerService organizerService;

    @GetMapping("/create/{managerName}")
    public void create(@PathVariable String managerName) {
        organizerService.createOrganizer(managerName);
    }

    @GetMapping("/edit/{managerName}")
    public void edit(@PathVariable String managerName) {
        organizerService.editOrganizer(managerName);
    }
}
