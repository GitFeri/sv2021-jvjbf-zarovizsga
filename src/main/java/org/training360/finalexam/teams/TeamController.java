package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/teams")
@AllArgsConstructor
public class TeamController {

    private TeamService teamService;

    @GetMapping
    public List<Team> getTeams() {
        return teamService.getTeams();
    }

    @PostMapping
    public TeamDTO createTeam(@RequestBody CreateTeamCommand command) {
        return teamService.createTeam(command);
    }

    @PutMapping("/{id}/players")
    public TeamDTO updateWithExistingPlayer(@PathVariable Long id, @RequestBody UpdateWithExistingPlayerCommand command) {
        return teamService.updateWithExistingPlayer(id,command);
    }

}
