package com.epam.training.lawAndSocial.model.education;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class University {

    long id;
    long userId;
    String name;
    String country;
    String city;
    int yearsFrom;
    int yearsTo;

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
