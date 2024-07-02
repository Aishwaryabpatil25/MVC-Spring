package com.xworkz.crisis.model.repo;

import com.xworkz.crisis.dto.CrisisDto;

public interface CrisisRepo {

    boolean save(CrisisDto crisisDto);

    CrisisDto findByEmailAndPassword(String email, String password);

    CrisisDto findByEmail(String email);

    boolean updateFailedLoginAttempts(CrisisDto crisisDto);

    boolean updateAccountLock(CrisisDto crisisDto);

    CrisisDto findByContactNumber(Long contactNumber);

    boolean updatePassword(String email, String newPassword);

    boolean forgotPassword(CrisisDto crisisDto);

}
