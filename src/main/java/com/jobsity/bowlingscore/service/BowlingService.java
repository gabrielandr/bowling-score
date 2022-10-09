package com.jobsity.bowlingscore.service;

import com.jobsity.bowlingscore.exception.BowlingBusinessException;

import java.io.IOException;
import java.util.List;

public interface BowlingService {
    List<String> processScore(String fileName) throws BowlingBusinessException, IOException;
}