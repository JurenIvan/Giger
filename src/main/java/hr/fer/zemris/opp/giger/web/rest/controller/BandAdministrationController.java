package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.service.BandService;
import hr.fer.zemris.opp.giger.web.rest.dto.BandCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.BandProfileDto;
import hr.fer.zemris.opp.giger.web.rest.dto.KickDto;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianBandDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/band-administration")
@AllArgsConstructor
@PreAuthorize("hasAuthority('MUSICIAN')")
public class BandAdministrationController {

    private BandService bandService;

    @PostMapping("/create")
    public void createBand(BandCreationDto band) {
        bandService.createBand(band);
    }

    @PostMapping("/invite")
    public void inviteUser(@RequestBody MusicianBandDto musicianBandDto) {
        bandService.inviteMusician(musicianBandDto);
    }

    @PostMapping("/invite-as-backup")
    public void inviteBackUpMusician(@RequestBody MusicianBandDto musicianBandDto) {
        bandService.inviteBackUpMusician(musicianBandDto);
    }

    @GetMapping("/join/{bandId}")
    public void joinBand(@PathVariable Long bandId) {
        bandService.joinBand(bandId);
    }

    @GetMapping("/leave/{bandId}")
    public void leaveBand(@PathVariable long bandId) {
        bandService.leaveBand(bandId);
    }

    @PostMapping("/kick")
    public void kickMusician(KickDto kickDto) {
        bandService.kickMusician(kickDto);
    }

    @PostMapping("/edit")
    public void editProfile(BandProfileDto bandProfileDto) {
        bandService.editProfile(bandProfileDto);
    }

    @PostMapping("/invites/{bandId}")
    public List<Musician> listInvitations(@PathVariable long bandId) {
        return bandService.listInvitations(bandId);
    }

    @PostMapping("/change-leader")
    public void changeLeader(@RequestBody MusicianBandDto musicianBandDto) {
        bandService.changeLeader(musicianBandDto);
    }
}
