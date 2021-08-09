package org.training360.finalexam.teams;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayerRepository;

import javax.transaction.Transactional;
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
                .orElseThrow(() -> new IllegalStateException("The player id isn't exist"));

        Team team = teamRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("The team id isn't exist."));

        team.addPlayer(player);
        return modelMapper.map(team,TeamDTO.class);
    }
}
