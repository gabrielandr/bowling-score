package com.jobsity.bowlingscore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreBoardDTO {

    private String playerName;
    private List<Frame> frames;

}
