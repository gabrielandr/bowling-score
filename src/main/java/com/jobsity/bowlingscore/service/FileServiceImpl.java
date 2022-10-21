package com.jobsity.bowlingscore.service;

import com.jobsity.bowlingscore.exception.BowlingBusinessException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<String> readBowlingFile(String fileName) throws BowlingBusinessException, IOException {
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        byte[] data = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
        String content = new String(data, StandardCharsets.UTF_8);
        if ("".equals(content)) {
            throw new BowlingBusinessException("File is empty!");
        }
        //Split string with tab separator
        return List.of(content.split("\\s+"));

    }
}
