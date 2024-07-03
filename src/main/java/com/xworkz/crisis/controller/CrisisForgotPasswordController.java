package com.xworkz.crisis.controller;

import com.xworkz.crisis.dto.CrisisDto;
import com.xworkz.crisis.model.service.CrisisService;
import com.xworkz.crisis.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;


@Controller
public class CrisisForgotPasswordController {

    @Autowired
    private CrisisService crisisService;
    @Autowired
    private EmailServiceController emailServiceController;


    @PostMapping("/forgotPassword")
    public String forgotPassword(@RequestParam("email") String email, Model model) {
        CrisisDto crisisDto = crisisService.findByEmail(email);
        if (crisisDto != null) {
           String pass=PasswordUtil. generatePassword(12);
           crisisDto.setPassword(pass);
            boolean updated = crisisService.forgotPassword(crisisDto);
            if (updated) {
                emailServiceController.sendPassword(crisisDto.getEmail(), crisisDto.getPassword());
                model.addAttribute("message", "A new password has been sent to your email");
                model.addAttribute("dto",crisisDto);
                return "CrisisPasswordReset";
            }
        }
        model.addAttribute("error", "Email not found");
        return "CrisisForgotPassword";
    }
}
