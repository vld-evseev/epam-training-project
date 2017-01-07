package com.epam.training.lawAndSocial.utils;

import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class DateValidatorTest {
    @Test
    public void parseDate_dd_MM_yyyy() throws Exception {
        final FormValidation formValidation = mock(FormValidation.class);

        final String validDate = "01.12.1945";
        final String result1 = DateValidator.parseDate(validDate, DateValidator.Pattern.DD_MM_YYYY, formValidation);
        assertEquals(result1, validDate);

        final String nonValidDate = "007";
        final String result2 = DateValidator.parseDate(nonValidDate, DateValidator.Pattern.DD_MM_YYYY, formValidation);
        assertEquals(result2, LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    @Test
    public void parseDate_yyyy() throws Exception {
        final FormValidation formValidation = mock(FormValidation.class);

        final String validDate = "1945";
        final DateValidator.Pattern yyyy = DateValidator.Pattern.YYYY;

        final String result1 = DateValidator.parseDate(validDate, yyyy, formValidation);
        assertEquals(result1, validDate);

        final String nonValidDate = "007";
        final String result2 = DateValidator.parseDate(nonValidDate, yyyy, formValidation);
        assertEquals(result2, Year.now().format(DateTimeFormatter.ofPattern(yyyy.toString())));
    }

}