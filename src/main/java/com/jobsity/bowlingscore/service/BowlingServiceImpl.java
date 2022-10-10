package com.jobsity.bowlingscore.service;

import com.jobsity.bowlingscore.dto.ScoreBoardDTO;
import com.jobsity.bowlingscore.exception.BowlingBusinessException;
import com.jobsity.bowlingscore.util.PlayerUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class BowlingServiceImpl implements BowlingService {

    @Override
    public List<String> processScore(String fileName) throws BowlingBusinessException, IOException {
        List<ScoreBoardDTO> playersThrows = readBowlingFile(fileName);
        List<String> finalScoreBoard = new ArrayList<>();
        finalScoreBoard.add("Frame \t\t 1 \t\t 2 \t\t 3 \t\t 4 \t\t 5 \t\t 6 \t\t 7 \t\t 8 \t\t 9 \t\t 10\n");

        List<ScoreBoardDTO> scoreBoard;
        if (!playersThrows.isEmpty()) {
            List<String> errors = new ArrayList<>();
            for (ScoreBoardDTO rollsByPlayer : playersThrows) {
                scoreBoard = new ArrayList<>();
                errors.addAll(calculatePlayerScore(rollsByPlayer.getPinfalls(), scoreBoard));
                if (!errors.isEmpty()) {
                    throw new BowlingBusinessException("Error has acurred! " + errors);
                } else {
                    finalScoreBoard.addAll(displayScoreBoard(rollsByPlayer, scoreBoard));
                }

            }
        }
        return finalScoreBoard;
    }

    public List<ScoreBoardDTO> readBowlingFile(String fileName) throws IOException, BowlingBusinessException {
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        byte[] data = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
        String content = new String(data, StandardCharsets.UTF_8);
        if ("".equals(content)) {
            throw new BowlingBusinessException("File is empty!");
        }
        //Split string with tab separator
        String[] rawRollsFromFile = content.split("\\s+");
        return readPlayersRolls(rawRollsFromFile);
    }

    /**
     * Calculate player score
     * @param rollsByPlayer
     * @param scoreBoard
     * @return
     */
    protected List<String> calculatePlayerScore(List<String> rollsByPlayer, List<ScoreBoardDTO> scoreBoard) {
        int playerScore = 0;
        int playerNextThrowIndex = 0;
        int frameLastIndex = 0;

        List<String> errors = validatePlayerPinfalls(rollsByPlayer);
        List<Integer> playerRolls = convertPlayerPinFalls(rollsByPlayer);

        //loop through 10 frames of a game
        if (errors.isEmpty()) {
            for (int frameIndex = 0; frameIndex < 10; frameIndex++) {
                frameLastIndex = playerNextThrowIndex;

                if (isStrike(playerRolls, playerNextThrowIndex)) {
                    //Apply strike bonus
                    playerScore += playerRolls.get(playerNextThrowIndex) + playerRolls.get(playerNextThrowIndex + 1) + playerRolls.get(playerNextThrowIndex + 2);
                    if (frameIndex == 9) {
                        scoreBoard.add(new ScoreBoardDTO("", String.valueOf(playerScore), Arrays.asList(
                                isStrike(playerRolls, playerNextThrowIndex) ? "X" : String.valueOf(playerRolls.get(playerNextThrowIndex)),
                                isStrike(playerRolls, playerNextThrowIndex + 1) ? "X" : String.valueOf(playerRolls.get(playerNextThrowIndex + 1)),
                                isStrike(playerRolls, playerNextThrowIndex + 2) ? "X" : String.valueOf(playerRolls.get(playerNextThrowIndex + 2)))));
                    } else {
                        scoreBoard.add(new ScoreBoardDTO("", String.valueOf(playerScore), Arrays.asList("", "X")));
                    }
                    playerNextThrowIndex++;
                    frameLastIndex += 2;
                } else if (isSpare(playerRolls, playerNextThrowIndex)) {
                    //Apply spare bonus
                    playerScore += playerRolls.get(playerNextThrowIndex) + playerRolls.get(playerNextThrowIndex + 1) + playerRolls.get(playerNextThrowIndex + 2);
                    if (frameIndex == 9) {
                        frameLastIndex += 2;

                        scoreBoard.add(new ScoreBoardDTO("", String.valueOf(playerScore), Arrays.asList(String.valueOf(playerRolls.get(playerNextThrowIndex)), "/",
                                String.valueOf(playerRolls.get(playerNextThrowIndex + 2)))));
                    } else {
                        scoreBoard.add(new ScoreBoardDTO("", String.valueOf(playerScore), Arrays.asList(String.valueOf(playerRolls.get(playerNextThrowIndex)), "/")));
                    }
                    playerNextThrowIndex += 2;
                    frameLastIndex += 1;
                } else {
                    playerScore += playerRolls.get(playerNextThrowIndex) + playerRolls.get(playerNextThrowIndex + 1);
                    scoreBoard.add(new ScoreBoardDTO("", String.valueOf(playerScore), Arrays.asList(String.valueOf(playerRolls.get(playerNextThrowIndex)),
                            String.valueOf(playerRolls.get(playerNextThrowIndex + 1)))));
                    if (frameIndex == 9) {
                        playerNextThrowIndex += 1;
                    } else {
                        playerNextThrowIndex += 2;
                    }
                    frameLastIndex += 1;
                }
            }

            if (rollsByPlayer.size() > frameLastIndex + 1) {
                errors.add("Can`t have more than 10 frames! Please review score board!");
            } else if (rollsByPlayer.size() < frameLastIndex + 1) {
                errors.add("Can`t have more than 10 frames! Please review score board!");
            }

        }
        return errors;
    }

    /**
     *
     * @param rollsByPlayer
     * @param scoreBoard
     * @return
     */
    protected List<String> displayScoreBoard(ScoreBoardDTO rollsByPlayer, List<ScoreBoardDTO> scoreBoard) {
        List<String> finalScoreBoard = new ArrayList<>();
        finalScoreBoard.add(rollsByPlayer.getPlayerName() + "\n");

        String pinFallScore = "";
        String frameScore = "";
        int frameIndex = 1;
        for (ScoreBoardDTO scoreBoardDTO : scoreBoard) {
            if (frameIndex == 10) {
                //Handle the case of last frame not being a strike or spare
                if (scoreBoardDTO.getPinfalls().size() > 2) {
                    pinFallScore = pinFallScore + scoreBoardDTO.getPinfalls().get(0) + " " + scoreBoardDTO.getPinfalls().get(1)
                            + " " + scoreBoardDTO.getPinfalls().get(2);
                } else {
                    pinFallScore = pinFallScore + scoreBoardDTO.getPinfalls().get(0) + " " + scoreBoardDTO.getPinfalls().get(1);
                }
                frameScore = frameScore + scoreBoardDTO.getScore() + "\t\t";
            } else {
                pinFallScore = pinFallScore + scoreBoardDTO.getPinfalls().get(0) + " " + scoreBoardDTO.getPinfalls().get(1) + "\t\t";
                frameScore = frameScore + scoreBoardDTO.getScore() + "\t\t";
                frameIndex++;
            }
        }

        finalScoreBoard.add("Pinfalls\t" + pinFallScore + "\n");
        finalScoreBoard.add("Score\t\t" + frameScore + "\n");
        return finalScoreBoard;
    }

    /**
     * Validate if a player pinfall value is valid
     * @param playerPinfalls
     * @return
     */
    protected List<String> validatePlayerPinfalls(List<String> playerPinfalls) {
        List<String> errors = new ArrayList<>();
        playerPinfalls.stream().forEach(pinfall -> {
            if ((!isNumeric(pinfall) && !"f".equalsIgnoreCase(pinfall)) || (isNumeric(pinfall) && Integer.parseInt(pinfall) > 10)) {
                errors.add("Invalid Roll Score! '" + pinfall + "'");
            }
        });
        return errors;
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

    protected boolean isSpare(List<Integer> playerRolls, int rollIndex) {
        return playerRolls.get(rollIndex) + playerRolls.get(rollIndex + 1) == 10;
    }

    protected boolean isStrike(List<Integer> playerRolls, int rollIndex) {
        return playerRolls.get(rollIndex) == 10;
    }

    /**
     * Read text file and organize rolls by player
     *
     * @param rawRollsFromFile
     * @return
     */
    protected List<ScoreBoardDTO> readPlayersRolls(String[] rawRollsFromFile) {
        String currentPlayer = "";
        List<ScoreBoardDTO> playersThrows = new ArrayList<>();

        int lineNumber = 1;
        for (String line : rawRollsFromFile) {
            //Odd line = player names, even lines = player scores, if playername changes, a new player is added to the map if it doesn`t exist
            if (lineNumber % 2 != 0) {
                currentPlayer = line;
                if (PlayerUtils.findPlayerRollsByName(playersThrows, currentPlayer) == null) {
                    playersThrows.add(new ScoreBoardDTO(line, "", new ArrayList<>()));
                }
            } else {
                PlayerUtils.findPlayerRollsByName(playersThrows, currentPlayer).getPinfalls().add(line);
            }
            lineNumber++;
        }
        return playersThrows;

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
}
