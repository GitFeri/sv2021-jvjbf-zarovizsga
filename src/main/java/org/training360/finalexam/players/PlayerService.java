package org.training360.finalexam.players;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {
    private ModelMapper modelMapper;
    private PlayerRepository playerRepository;

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Transactional
    public PlayerDTO createPlayer(CreatePlayerCommand command) {
        Player player = new Player(
                command.getName(),
                command.getBirthDate(),
                command.getPosition());
        playerRepository.save(player);
        return modelMapper.map(player,PlayerDTO.class);
    }


    public void deletePlayerById(Long id) {
     playerRepository.deleteById(id);
    }
}
