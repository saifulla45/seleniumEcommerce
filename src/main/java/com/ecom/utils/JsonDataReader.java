package com.ecom.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class JsonDataReader {

    public static List<HashMap<String, String>> getDataFromJson(String filePath) {
        try {
            File file = new File(filePath);
            String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
             return objectMapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
