package com.jobsity.bowlingscore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollsDTO {

    private String playerName;
    private List<String> pinfalls;

}
