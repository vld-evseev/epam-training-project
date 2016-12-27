package com.epam.training.lawAndSocial.utils;

import com.epam.training.lawAndSocial.web.servlet.model.FieldValidation;
import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.epam.training.lawAndSocial.utils.ServletParams.BIRTH_DATE_PARAM;

public class DateValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateValidator.class);

    public static String parseDate(String date, FormValidation validation) {
        if (validation == null) {
            validation = new FormValidation();
        }
        final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String defaultDate = LocalDate.now().format(pattern);

        if (date == null || date.isEmpty()) {
            LOGGER.debug("Attempting to set null or empty date");
            validation.getFields().put(
                    BIRTH_DATE_PARAM,
                    FieldValidation.builder().isIncorrect(true).build()
            );
            return defaultDate;
        }

        try {
            final LocalDate localDate = LocalDate.parse(date, pattern);
            if (!dateRangeIsValid(localDate)) {
                LOGGER.debug("Attempting to set an out-of-range date: {}", date);
                validation.getFields().put(
                        BIRTH_DATE_PARAM,
                        FieldValidation.builder().isIncorrect(true).build()
                );
                return defaultDate;
            }
            return localDate.format(pattern);
        } catch (DateTimeException e) {
            LOGGER.error("Error while parsing date: {}\n{}", date, e.getMessage());
            validation.getFields().put(
                    BIRTH_DATE_PARAM,
                    FieldValidation.builder().isIncorrect(true).build()
            );
            return defaultDate;
        }
    }

    private static boolean dateRangeIsValid(LocalDate date) {
        return date.isAfter(LocalDate.of(1900, 1, 1))
                && date.isBefore(LocalDate.now().plusDays(1));
    }

}
