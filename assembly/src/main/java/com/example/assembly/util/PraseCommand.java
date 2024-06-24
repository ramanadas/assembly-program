package com.example.assembly.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PraseCommand {
    public List<String> parse(String commands) {
        return Arrays.asList(commands.split("\\r?\\n"));
    }
}
