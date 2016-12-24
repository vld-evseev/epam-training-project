package com.epam.training.lawAndSocial.model.education;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Education {

    List<School> schoolList;
    List<University> universityList;

}
