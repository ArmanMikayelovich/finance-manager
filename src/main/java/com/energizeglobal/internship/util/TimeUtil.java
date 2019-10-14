package com.energizeglobal.internship.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.time.Instant;
import java.time.ZoneId;

@Component
public class TimeUtil {
    public LocalDate sqlDateToLocalDateTime(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
