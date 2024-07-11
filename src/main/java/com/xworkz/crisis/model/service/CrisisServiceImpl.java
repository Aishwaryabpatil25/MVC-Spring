package com.xworkz.crisis.model.service;

import com.xworkz.crisis.controller.EmailServiceController;
import com.xworkz.crisis.dto.CrisisDto;
import com.xworkz.crisis.model.repo.CrisisRepo;
import com.xworkz.crisis.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CrisisServiceImpl implements CrisisService {

    @Autowired
    private CrisisRepo crisisRepo;

    @Autowired
    private EmailServiceController emailServiceController;



    public CrisisServiceImpl() {
        System.out.println("Created crisis service impl");
    }

    @Override
    public boolean saveAndValidate(CrisisDto crisisDto) {
        System.out.println("Running save and validate method in crisis service");

       CrisisDto crisisDto1= crisisRepo.findByEmail(crisisDto.getEmail());
       if(crisisDto1!=null)
       {
           System.out.println("email already exists");
           return false;
       }
       else {
           System.err.println(crisisDto1);
           setAuditValues(crisisDto, crisisDto.getFirstName(), LocalDateTime.now(), "NA", null, true);

           boolean save = crisisRepo.save(crisisDto);
           if (save) {
               System.out.println("Crisis service success: " + crisisDto);
               return true;
           } else {
               System.out.println("Crisis service not success: " + crisisDto);
           }
       }
        return false;
    }

    @Override
    public void setAuditValues(CrisisDto crisisDto, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive) {
        crisisDto.setCreatedBy(createdBy);
        crisisDto.setCreatedOn(createdOn);
        crisisDto.setUpdatedBy(updatedBy);
//        if (updatedBy == null || !updatedBy.equals("NA")) {
//            crisisDto.setUpdatedOn(updatedOn);
//        }
        crisisDto.setActive(isActive);
    }

    @Override
    public CrisisDto findByEmailAndPassword(String email, String password) {
        CrisisDto crisisDto = crisisRepo.findByEmailAndPassword(email, password);

        if (crisisDto != null) {
            System.out.println("Crisis found: " + crisisDto);
            return crisisDto;
        } else {
            System.out.println("Crisis not found for email: " + email);
            return null;
        }

    }

    @Override
    public CrisisDto findByEmail(String email) {
        CrisisDto crisisDto = crisisRepo.findByEmail(email);
        if (crisisDto != null) {
            return crisisDto;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateFailedLoginAttempts(int attempts, String email) {
        CrisisDto crisisDto = crisisRepo.findByEmail(email);
        if (crisisDto != null) {
            crisisDto.setFailedLoginAttempts(attempts);
            return crisisRepo.updateFailedLoginAttempts(crisisDto);
        }
        return false;
    }

    @Override
    public boolean updateAccountLock(boolean lock, String email) {
        CrisisDto crisisDto = crisisRepo.findByEmail(email);
        if (crisisDto != null) {
            crisisDto.setAccountLocked(lock);
            return crisisRepo.updateAccountLock(crisisDto);
        }
        return false;
    }

    @Override
    public CrisisDto findByContactNumber(Long contactNumber) {
        CrisisDto crisisDto = crisisRepo.findByContactNumber(contactNumber);
        if (crisisDto != null) {
            System.out.println("Crisis found for phone: " + crisisDto.getContactNumber());
            return crisisDto;
        } else {
            System.out.println("Crisis not found for phone: " + contactNumber);
            return null;
        }
    }

    @Override
    public boolean resetPassword(String email, String newPassword) {

            return crisisRepo.updatePassword(email, newPassword);
        }

    @Override
    public boolean forgotPassword(CrisisDto crisisDto) {


        boolean isUpdated = crisisRepo.forgotPassword(crisisDto);
        if (isUpdated) {

            System.out.println("Crisis found Email: " + crisisDto.getEmail());
            return true;
        }
        return false;
    }


}







