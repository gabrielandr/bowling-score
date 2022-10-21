package com.jobsity.bowlingscore.service;

import com.jobsity.bowlingscore.dto.RollsDTO;
import com.jobsity.bowlingscore.dto.ScoreBoardDTO;
import com.jobsity.bowlingscore.exception.BowlingBusinessException;
import com.jobsity.bowlingscore.util.PlayerUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PlayerRollServiceImpl implements PlayerRollService {

    @Override
    public List<RollsDTO> separateRollsByPlayer(List<String> rawRollsFromFile) {
        String currentPlayer = "";
        List<RollsDTO> playersThrows = new ArrayList<>();

        int lineNumber = 1;
        for (String line : rawRollsFromFile) {
            //Odd line = player names, even lines = player scores, if playername changes, a new player is added to the map if it doesn`t exist
            if (lineNumber % 2 != 0) {
                currentPlayer = line;
                if (PlayerUtils.findPlayerRollsByName(playersThrows, currentPlayer) == null) {
                    playersThrows.add(new RollsDTO(line, new ArrayList<>()));
                }
            } else {
                PlayerUtils.findPlayerRollsByName(playersThrows, currentPlayer).getPinfalls().add(line);
            }
            lineNumber++;
        }
        return playersThrows;
    }

    @Override
    public void buildPlayersScoreBoard(List<RollsDTO> rollsByPlayer) {
        List<ScoreBoardDTO> scoreBoards;
        if (!rollsByPlayer.isEmpty()) {
            for (RollsDTO rollsDTO : rollsByPlayer) {
                ScoreBoardDTO scoreBoardDTO = new ScoreBoardDTO(rollsDTO.getPlayerName(), new ArrayList<>());
                for (String pinfall : rollsDTO.getPinfalls()) {
                    if (isStrike(pinfall)) {

                    }
                }

            }
        }
        return finalScoreBoard;
    }

    protected List<Integer> convertPlayerPinFalls(List<String> pinFalls) {
        List<Integer> pinFallScores = new ArrayList<>();
        for (String pinfall : pinFalls) {
            if (isNumeric(pinfall)) {
                Integer playerScore = Integer.parseInt(pinfall);
                pinFallScores.add(playerScore);
            } else if ("f".equalsIgnoreCase(pinfall)) {
                pinFallScores.add(0);
            }
        }
        return pinFallScores;
    }

    /**
     * Checks if a string is numeric, also fails on negative numbers
     *
     * @param string
     * @return
     */
    protected boolean isNumeric(String string) {
        String regex = "[0-9]+[\\.]?[0-9]*";
        return Pattern.matches(regex, string);
    }
    protected boolean isStrike(List<Integer> playerRolls, int rollIndex) {
        return playerRolls.get(rollIndex) == 10;
    }

}
