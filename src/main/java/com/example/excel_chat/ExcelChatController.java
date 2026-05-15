package com.example.excel_chat;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;

import org.springframework.ai.chat.client.ChatClient;

import java.io.IOException;

@RestController
public class ExcelChatController {    
    
    private final ChatClient chatClient;

    public ExcelChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {
        return chatClient.prompt(message).call().content();
    }

    @GetMapping("/hello")
    public String excelChat() {
        return "Hello, World!";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        StringBuilder out = new StringBuilder();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        DataFormatter formatter = new DataFormatter();

        for (Sheet sheet : workbook) {
            for (Row row : sheet) {
                for (Cell cell : row) {
                    out.append(formatter.formatCellValue(cell)).append("\t");
                }
                out.append("\n");
            }
        }
        workbook.close();
        return out.toString();
    }
}