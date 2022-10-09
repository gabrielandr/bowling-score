package com.jobsity.bowlingscore.controller;

import com.jobsity.bowlingscore.exception.BowlingBusinessException;
import com.jobsity.bowlingscore.service.BowlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BowlingRestController {

    @Autowired
    private BowlingService roleService;

    @PostMapping(value = "/processScore", produces = {
            MediaType.TEXT_PLAIN_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> processScore(@RequestParam("fileName") String fileName) {
        List<String> finalScoreBoard = null;
        try {
            finalScoreBoard = roleService.processScore(fileName);
        } catch (BowlingBusinessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        if (finalScoreBoard.isEmpty()) {
            return ResponseEntity.ok("File is empty or it was not found!");
        }
        return ResponseEntity.ok(finalScoreBoard.toString());
    }

}
