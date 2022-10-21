package com.jobsity.bowlingscore.service;

import com.jobsity.bowlingscore.dto.RollsDTO;

import java.util.List;

public interface PlayerRollService {
    List<RollsDTO> separateRollsByPlayer(List<String> rawRollsFromFile);

    void buildPlayersScoreBoard(List<RollsDTO> rollsByPlayer);
}