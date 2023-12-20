package com.example.rabbitmqtutorial.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Common {

    private static final Logger log = LoggerFactory.getLogger(Common.class);

    public static String normalizedName(String input) {
        // Loại bỏ các dấu thanh (dấu diacritical)
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        normalized = pattern.matcher(normalized).replaceAll("");

        // Xóa khoảng trắng và chuyển thành chữ thường
        normalized = normalized.replaceAll("\\s+", "").toLowerCase();

        return normalized;
    }
}
