package com.jobsity.bowlingscore.util;

import com.jobsity.bowlingscore.dto.ScoreBoardDTO;

import java.util.List;
import java.util.Optional;

public class PlayerUtils {

    public static ScoreBoardDTO findPlayerRollsByName(List<ScoreBoardDTO> playersThrows, String playerName) {
        Optional<ScoreBoardDTO> playerRollsOptional = playersThrows.stream().filter(scoreBoardDTO -> playerName.equalsIgnoreCase(scoreBoardDTO.getPlayerName())).findFirst();
        if (playerRollsOptional.isPresent()) {
            return playerRollsOptional.get();
        }
        return null;
    }
}
