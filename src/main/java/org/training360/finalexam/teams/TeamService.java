package org.training360.finalexam.teams;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.training360.finalexam.players.CreatePlayerCommand;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayerRepository;
import org.training360.finalexam.players.PositionType;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;
    private ModelMapper modelMapper;

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    @Transactional
    public TeamDTO createTeam(CreateTeamCommand command) {
        Team team = new Team(command.getName());
        teamRepository.save(team);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO updateWithExistingPlayer(Long id, UpdateWithExistingPlayerCommand command) {
        Player player = playerRepository
                .findById(command.getId())
                .orElseThrow(() -> new IllegalStateException("The player id doesn't exist"));

        Team team = teamRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("The team id doesn't exist."));

        addPlayerIfEnable(team, player);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO updateTeamWithNewPlayer(Long id, @Valid CreatePlayerCommand command) {
        Player player = new Player(
                command.getName(),
                command.getBirthDate(),
                command.getPosition());
        Team team = teamRepository
                .findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));

        addPlayerIfEnable(team, player);
        return modelMapper.map(team, TeamDTO.class);
    }

    private void addPlayerIfEnable(Team team, Player player) {
        if (player.getTeam() == null && countOfPlayerByType(team, player.getPosition()) < 2) {
            team.addPlayer(player);
        }
    }

    private int countOfPlayerByType(Team team, PositionType positionType) {
        int count = 0;
        for (Player pl : team.getPlayers()) {
            if (pl.getPosition().equals(positionType)) {
                count++;
            }
        }
        return count;
    }
}
