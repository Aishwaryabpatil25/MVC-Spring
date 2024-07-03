package com.xworkz.crisis.model.service;

import com.xworkz.crisis.dto.CrisisDto;

import java.time.LocalDateTime;

public interface CrisisService {

    boolean saveAndValidate(CrisisDto crisisDto);

    void setAuditValues(CrisisDto crisisDto, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive);

    CrisisDto findByEmailAndPassword(String email,String password);

    CrisisDto findByEmail(String email);

    boolean updateFailedLoginAttempts(int attempts, String email);

    boolean updateAccountLock(boolean lock, String email);

    CrisisDto findByContactNumber(Long contactNumber);

    boolean resetPassword(String email, String newPassword);

    public boolean forgotPassword(CrisisDto crisisDto);



}

