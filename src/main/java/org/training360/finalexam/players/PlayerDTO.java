package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.teams.Team;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private PositionType position;
    private Team team;


    public String getName() {
        return name;
    }
}