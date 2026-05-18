package com.example.excel_chat;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.excel_chat.service.AnalysisService;
import org.springframework.http.MediaType;

import java.io.IOException;

@RestController
public class AnalysisController {    
    
    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping(value = "/api/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String analyze(
        @RequestParam(required = false) String prompt,
        @RequestParam(required = false) MultipartFile file) throws IOException {
    
        return analysisService.analyze(prompt, file).getResult();
    }
}