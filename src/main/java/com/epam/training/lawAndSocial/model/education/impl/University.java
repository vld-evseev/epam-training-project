package com.epam.training.lawAndSocial.model.education.impl;

import com.epam.training.lawAndSocial.model.education.EducationInfo;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class University implements EducationInfo {

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

        University that = (University) o;

        if (userId != that.userId) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }
}
