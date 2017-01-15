package com.epam.training.lawAndSocial.model.education.impl;


import com.epam.training.lawAndSocial.model.education.EducationInfo;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class School implements EducationInfo {

    long id;
    long userId;
    String name;
    String country;
    String city;
    int yearFrom;
    int yearTo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;

        if (userId != school.userId) return false;
        return name.equals(school.name);

    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }
}
