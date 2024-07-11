package com.xworkz.crisis.controller;

import com.xworkz.crisis.dto.CrisisDto;
import com.xworkz.crisis.model.service.CrisisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CrisisResetPassword {

    @Autowired
    public CrisisService crisisService;
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword,
                                Model model) {
        CrisisDto crisisDto = crisisService.findByEmailAndPassword(email, password);

        if (crisisDto != null) {
            if (newPassword.equals(confirmPassword)) {
                boolean isUpdated = crisisService.resetPassword(email, newPassword);
                if (isUpdated) {
                    model.addAttribute("message", "Password successfully updated");
                    model.addAttribute("dto",crisisDto);
                    return "CrisisLogin";
                } else {
                    model.addAttribute("message", "Failed to update password");
                }
            } else {
                model.addAttribute("message", "New password and confirm password do not match");
            }
        } else {
            model.addAttribute("message", "Invalid current password");
        }
        return "CrisisPasswordReset";
    }
}

