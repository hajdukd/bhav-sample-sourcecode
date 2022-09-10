package com.hajdukda.bhavsamplesourcecode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class Key {

    public static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final String name;
    private final String date;

    public static String formatDate(final LocalDate date) {
        return date.format(YYYY_MM_DD);
    }
}
