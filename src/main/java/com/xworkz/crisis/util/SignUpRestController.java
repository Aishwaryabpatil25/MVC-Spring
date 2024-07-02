package com.xworkz.crisis.util;

import com.xworkz.crisis.dto.CrisisDto;
import com.xworkz.crisis.model.service.CrisisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SignUpRestController {

    @Autowired
    private CrisisService crisisService;

    @GetMapping("/validateEmail/{email}")
    public String validateEmail(@PathVariable String email) {
        CrisisDto crisis = crisisService.findByEmail(email);
        System.out.println(email);
        return crisis != null ? "Email already exists" : "";
    }

    @GetMapping("/validateContactNumber/{contactNumber}")
    public String validateContactNumber(@PathVariable Long contactNumber) {
        {
            CrisisDto existingContactNumber = crisisService.findByContactNumber(contactNumber);
            return existingContactNumber != null ? "Contact number already exists" : "";
        }
    }
}

