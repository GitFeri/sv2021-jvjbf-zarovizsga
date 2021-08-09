package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@AllArgsConstructor
public class PlayerController {

private PlayerService playerService;

    @GetMapping
    public List<Player> getPlayers() {
        return playerService.getPlayers();
    }

    @PostMapping
    public PlayerDTO createPlayer(@Valid @RequestBody CreatePlayerCommand command) {
        return playerService.createPlayer(command);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayerById(id);
    }
}
