package com.jobsity.bowlingscore.service;

import com.jobsity.bowlingscore.exception.BowlingBusinessException;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<String> readBowlingFile(String fileName) throws BowlingBusinessException, IOException;
}