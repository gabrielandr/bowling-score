package com.jobsity.bowlingscore.controller;

import lombok.Data;

import java.util.List;

@Data
public class PlayerScoreDTO {
    private String playerName;
    private List<String> pinfallsByFrame;
    private String scoreByFrame;

}
