package com.epam.training.lawAndSocial.utils;

import com.epam.training.lawAndSocial.web.servlet.model.FieldValidation;
import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import static com.epam.training.lawAndSocial.utils.ServletParams.BIRTH_DATE_PARAM;

public final class DateValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateValidator.class);

    private DateValidator() {
    }

    public enum Pattern {
        DD_MM_YYYY {
            @Override
            public String toString() {
                return "dd.MM.yyyy";
            }
        },

        YYYY {
            @Override
            public String toString() {
                return "yyyy";
            }
        }
    }

    public static String parseDate(String date, Pattern pattern, FormValidation validation) {
        if (validation == null) {
            validation = new FormValidation();
        }

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.toString());

        if (pattern == Pattern.YYYY) {
            return parseYear(date, formatter);
        }

        return parseDate(date, formatter, validation);
    }

    private static String parseDate(String date, DateTimeFormatter formatter, FormValidation validation) {
        String defaultDate = LocalDate.now().format(formatter);

        if (date == null || date.isEmpty()) {
            LOGGER.debug("Attempting to set null or empty date");
            validation.getFields().put(
                    BIRTH_DATE_PARAM,
                    FieldValidation.builder().isIncorrect(true).build()
            );
            return defaultDate;
        }

        try {
            final LocalDate localDate = LocalDate.parse(date, formatter);
            if (!dateRangeIsValid(localDate)) {
                LOGGER.debug("Attempting to set an out-of-range date: {}", date);
                validation.getFields().put(
                        BIRTH_DATE_PARAM,
                        FieldValidation.builder().isIncorrect(true).build()
                );
                return defaultDate;
            }
            return localDate.format(formatter);
        } catch (DateTimeException e) {
            LOGGER.error("Error while parsing date: {}\n{}", date, e.getMessage());
            validation.getFields().put(
                    BIRTH_DATE_PARAM,
                    FieldValidation.builder().isIncorrect(true).build()
            );
            return defaultDate;
        }
    }

    private static String parseYear(String date, DateTimeFormatter formatter) {
        try {
            Year year = Year.of(Integer.valueOf(date));
            if (yearRangeIsValid(year)) {
                return year.format(formatter);
            }

            return Year.of(0).format(formatter);
        } catch (NumberFormatException e) {
            LOGGER.error("Error while parsing year\n{}", e.getMessage());
            return Year.of(0).format(formatter);
        }
    }

    private static boolean yearRangeIsValid(Year year) {
        return year.isAfter(Year.of(1900)) &&
                year.isBefore(Year.now());
    }

    private static boolean dateRangeIsValid(LocalDate date) {
        return date.isAfter(LocalDate.of(1900, 1, 1))
                && date.isBefore(LocalDate.now().plusDays(1));
    }

}
