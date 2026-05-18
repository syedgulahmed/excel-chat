package com.example.excel_chat.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.excel_chat.dto.AnalysisResponse;

@Service
public class AnalysisService {
    private final ClaudeClient claudeClient;
    private final ExcelParser excelParser;
    

    public AnalysisService(ClaudeClient claudeClient, ExcelParser excelParser) {
        this.claudeClient = claudeClient;
        this.excelParser = excelParser;
    }

    public AnalysisResponse analyze(String prompt, MultipartFile file) throws IOException {
        
        boolean hasPrompt = prompt != null && !prompt.isBlank();
        boolean hasFile = file != null && !file.isEmpty();

        if(!hasPrompt && !hasFile){
            throw new IllegalArgumentException("Provide a prompt, a file, or both");
        }

        String finalPrompt;

        if (!hasFile) {
            finalPrompt = prompt;
        } else if (!hasPrompt) {
            String excelText = excelParser.excelToString(file);
            finalPrompt = "Analyze the following spreadsheet and provide a summary.\n\n"
                        + "Spreadsheet data:\n" + excelText;
        } else {
            String excelText = excelParser.excelToString(file);
            finalPrompt = prompt + "\n\nSpreadsheet data:\n" + excelText;
        }

        String response = claudeClient.send(finalPrompt);
        return new AnalysisResponse(response);
        
    }
    
}