package com.epam.training.lawAndSocial.web.servlet.user.edit;

import com.epam.training.lawAndSocial.model.education.School;
import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;
import static org.mockito.Mockito.mock;

public class EducationInfoServletTest {

    @Test
    public void getSchoolListValidTest() throws Exception {
        final Map<String, String[]> params = new HashMap<>();
        params.put(SCHOOL_ID_PARAM, new String[]{"1", "2"});
        params.put(SCHOOL_USER_ID_PARAM, new String[]{"1", "2"});
        params.put(SCHOOL_NAME_PARAM, new String[]{"testSchool1", "testSchool2"});
        params.put(SCHOOL_COUNTRY_PARAM, new String[]{"Russia", "USA"});
        params.put(SCHOOL_CITY_PARAM, new String[]{"Moscow", "New York"});
        params.put(SCHOOL_YEAR_FROM_PARAM, new String[]{"1990", "1985"});
        params.put(SCHOOL_YEAR_TO_PARAM, new String[]{"2000", "1995"});

        final FormValidation formValidation = mock(FormValidation.class);

        final List<School> schoolList = EducationInfoServlet.getSchoolList(params, formValidation);
        Assert.assertFalse(schoolList.isEmpty());

        final School school1 = schoolList.get(0);
        Assert.assertEquals(school1.getId(), 1);
        Assert.assertEquals(school1.getUserId(), 1);
        Assert.assertEquals(school1.getName(), "testSchool1");
        Assert.assertEquals(school1.getCountry(), "Russia");
        Assert.assertEquals(school1.getCity(), "Moscow");
        Assert.assertEquals(school1.getYearsFrom(), 1990);
        Assert.assertEquals(school1.getYearsTo(), 2000);

        final School school2 = schoolList.get(1);
        Assert.assertEquals(school2.getId(), 2);
        Assert.assertEquals(school2.getUserId(), 2);
        Assert.assertEquals(school2.getName(), "testSchool2");
        Assert.assertEquals(school2.getCountry(), "USA");
        Assert.assertEquals(school2.getCity(), "New York");
        Assert.assertEquals(school2.getYearsFrom(), 1985);
        Assert.assertEquals(school2.getYearsTo(), 1995);

    }

    @Test
    public void getSchoolListInvalidTest() throws Exception {
        final Map<String, String[]> params = new HashMap<>();
        params.put(SCHOOL_ID_PARAM, new String[]{null});
        params.put(SCHOOL_USER_ID_PARAM, new String[]{""});
        params.put(SCHOOL_NAME_PARAM, new String[]{"school"});
        params.put(SCHOOL_COUNTRY_PARAM, new String[]{"Russia"});
        params.put(SCHOOL_CITY_PARAM, new String[]{"Moscow"});
        params.put(SCHOOL_YEAR_FROM_PARAM, new String[]{});
        params.put(SCHOOL_YEAR_TO_PARAM, new String[]{});

        final FormValidation formValidation = mock(FormValidation.class);

        final List<School> schoolList = EducationInfoServlet.getSchoolList(params, formValidation);
        Assert.assertFalse(schoolList.isEmpty());

        final School school1 = schoolList.get(0);
        Assert.assertEquals(school1.getId(), 0);
        Assert.assertEquals(school1.getUserId(), 0);
        Assert.assertEquals(school1.getName(), "school");
        Assert.assertEquals(school1.getCountry(), "");
        Assert.assertEquals(school1.getCity(), "");
        Assert.assertEquals(school1.getYearsFrom(), 1);
        Assert.assertEquals(school1.getYearsTo(), 1);

    }

}