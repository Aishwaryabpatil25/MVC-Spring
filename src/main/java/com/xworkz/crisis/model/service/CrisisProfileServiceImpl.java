package com.xworkz.crisis.model.service;

import com.xworkz.crisis.dto.CrisisProfileDto;
import com.xworkz.crisis.model.repo.CrisisProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CrisisProfileServiceImpl implements CrisisProfileService {

    @Autowired
    private CrisisProfileRepo crisisProfileRepo;

    public CrisisProfileServiceImpl() {
        System.out.println("Created CrisisProfileServiceImpl");
    }

    @Override
    public boolean saveAndValidate(CrisisProfileDto crisisProfileDto) {
        System.out.println("Running save and validate method in CrisisProfileService");



crisisProfileDto.setCreatedOn(LocalDateTime.now());
crisisProfileDto.setUpdatedBy("NA");
crisisProfileDto.setUpdatedOn(null);
        boolean save = crisisProfileRepo.save(crisisProfileDto);
        if (save) {
            System.out.println("Profile upload success: " + crisisProfileDto);
        } else {
            System.out.println("Profile upload not successful: " + crisisProfileDto);
        }
        return save;
    }


}
