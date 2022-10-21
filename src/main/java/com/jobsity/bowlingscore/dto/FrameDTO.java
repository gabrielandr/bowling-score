package com.jobsity.bowlingscore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrameDTO {

    private String score;
    private List<String> pinfalls;

}
