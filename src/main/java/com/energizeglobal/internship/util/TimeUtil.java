package com.energizeglobal.internship.util;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class TimeUtil {
    public LocalDateTime sqlDateToLocalDateTime(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
