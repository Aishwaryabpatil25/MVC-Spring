package com.xworkz.crisis.controller;

import com.xworkz.crisis.dto.CrisisDto;
import com.xworkz.crisis.model.service.CrisisService;
import com.xworkz.crisis.util.PasswordUtil;
import com.xworkz.crisis.util.VerifyRecaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping()
public class CrisisSignupController {

    @Autowired
    private CrisisService crisisService;

    @Autowired
    private EmailServiceController emailService;

    public CrisisSignupController() {
        System.out.println("Created CrisisController");
    }

    @PostMapping("submitSignup")
    public String submitCrisisRegistration(@Valid CrisisDto crisisDto, BindingResult bindingResult, Model model, @RequestParam("g-recaptcha-response") String gRecaptchaResponse) {
        System.out.println(crisisDto);
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("dto", crisisDto);
            return "CrisisSignUp";
        }

//        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
//        if (!verify) {
//            model.addAttribute("message", "reCAPTCHA verification failed. Please try again.");
//            model.addAttribute("dto", crisisDto);
//            return "CrisisSignUp";
//        }

        String generatedPassword = PasswordUtil.generatePassword(12);
        crisisDto.setPassword(generatedPassword);
        boolean save = crisisService.saveAndValidate(crisisDto);
        if (save) {
            model.addAttribute("message", crisisDto.getFirstName() + " Successfully Registered!  ");
            emailService.sendPassword(crisisDto.getEmail(), generatedPassword);
            return "CrisisSuccess";
        } else {
            model.addAttribute("message", crisisDto.getFirstName() + " Not Successfully Registered! ");
            return "CrisisSignUp";
        }
    }
}
