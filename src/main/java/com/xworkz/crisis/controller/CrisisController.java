package com.xworkz.crisis.controller;

import com.xworkz.crisis.dto.CrisisDto;
import com.xworkz.crisis.model.service.CrisisService;
import com.xworkz.crisis.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@Controller
@RequestMapping()
public class CrisisController {

    @Autowired
    private CrisisService crisisService;

    @Autowired
    private EmailServiceController emailService;

    public CrisisController() {
        System.out.println("Created CrisisController");
    }

    @PostMapping("submitSignup")
    public String submitCrisisRegistration(@Valid CrisisDto crisisDto, BindingResult bindingResult, Model model) {
        System.out.println(crisisDto);
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("dto", crisisDto);
            return "CrisisSignUp";
        } else {
            String generatedPassword = PasswordUtil.generatePassword(12);
            crisisDto.setPassword(generatedPassword);
            boolean save = crisisService.saveAndValidate(crisisDto);
            if (save) {
                model.addAttribute("message",  crisisDto.getFirstName()+ " Successfully Registered!  ");
                emailService.sendPassword(crisisDto.getEmail(), generatedPassword);
                return "CrisisSuccess";
            } else {
                model.addAttribute("message",  crisisDto.getFirstName()+" Not Successfully Registered! " );
            }
            return "CrisisSuccess";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        CrisisDto crisisDto = crisisService.findByEmailAndPassword(email, password);
        if (crisisDto != null) {

            System.out.println("Login success");
            model.addAttribute("message", crisisDto.getFirstName() + " login successfully");
            model.addAttribute("dto", crisisDto);
            return "CrisisPasswordReset";
        } else {
            System.out.println("Login failed");
            model.addAttribute("message", "Login failed");
            CrisisDto crisisDto1 = crisisService.findByEmail(email);
            if (crisisDto1 != null) {
                int atmps = crisisDto1.getFailedLoginAttempts() + 1;
                if (atmps >= 3) {
                    crisisService.updateAccountLock(true, email);
                    model.addAttribute("msg", "Your account has been locked due to multiple failed login attempts.");
                    model.addAttribute("action", "edit");
                } else {
                    crisisService.updateFailedLoginAttempts(atmps, email);
                    model.addAttribute("msg", "Invalid login " + (3 - atmps) + " attempts left.");
                }
            } else {
                model.addAttribute("msg", "Invalid email address");
            }
            return "CrisisLogin";

        }
    }
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


