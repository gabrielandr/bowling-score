package com.jobsity.bowlingscore.util;

import com.jobsity.bowlingscore.dto.RollsDTO;

import java.util.List;
import java.util.Optional;

public class PlayerUtils {

    public static RollsDTO findPlayerRollsByName(List<RollsDTO> playersThrows, String playerName) {
        Optional<RollsDTO> rollsDTOOptional = playersThrows.stream().filter(rollsDTO -> playerName.equalsIgnoreCase(rollsDTO.getPlayerName())).findFirst();
        if (rollsDTOOptional.isPresent()) {
            return rollsDTOOptional.get();
        }
        return null;
    }
}
