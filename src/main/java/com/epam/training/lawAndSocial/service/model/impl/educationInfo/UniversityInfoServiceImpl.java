package com.epam.training.lawAndSocial.service.model.impl.educationInfo;

import com.epam.training.lawAndSocial.dao.EducationInfoDao;
import com.epam.training.lawAndSocial.model.education.EducationInfo;
import com.epam.training.lawAndSocial.service.model.EducationInfoService;
import com.epam.training.lawAndSocial.service.model.impl.educationInfo.annotations.UniverInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class UniversityInfoServiceImpl extends AbstractEducationInfoServiceImpl<EducationInfo> implements EducationInfoService<EducationInfo> {

    private final static Logger LOGGER = LoggerFactory.getLogger(UniversityInfoServiceImpl.class);

    @Inject
    public UniversityInfoServiceImpl(@UniverInfo EducationInfoDao<EducationInfo> educationInfoDao) {
        super(educationInfoDao);
    }
}
