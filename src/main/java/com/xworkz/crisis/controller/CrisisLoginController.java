package com.xworkz.crisis.controller;

import com.xworkz.crisis.dto.CrisisComplaintRiseDto;
import com.xworkz.crisis.dto.CrisisDto;
import com.xworkz.crisis.dto.CrisisProfileDto;
import com.xworkz.crisis.model.service.CrisisComplaintRiseService;
import com.xworkz.crisis.model.service.CrisisEditProfileService;
import com.xworkz.crisis.model.service.CrisisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")

public class CrisisLoginController {
    @Autowired
    private CrisisService crisisService;
    @Autowired
    private CrisisEditProfileService crisisEditProfileService;

    @Autowired
    private CrisisComplaintRiseService crisisComplaintRiseService;

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        CrisisDto crisisDto = crisisService.findByEmailAndPassword(email, password);
        if (crisisDto != null && crisisDto.getAccountLocked() == false) {
            session.setAttribute("email",email);
            session.setAttribute("firstName",crisisDto.getFirstName());
            session.setAttribute("lastName",crisisDto.getLastName());
            session.setAttribute("contactNumber",crisisDto.getContactNumber());
            session.setAttribute("alternativeContactNumber",crisisDto.getAlternativeContactNumber());
            session.setAttribute("address",crisisDto.getAddress());
           List<CrisisComplaintRiseDto> complaint= crisisComplaintRiseService.getAllCrisisComplaints(crisisDto.getId());

            session.setAttribute("complaint",complaint);
            session.setAttribute("dto",crisisDto);
            System.out.println("Login success");
            model.addAttribute("message", crisisDto.getFirstName() + " login successfully");
            model.addAttribute("dto", crisisDto);
            List<CrisisProfileDto> crisisProfileDto1 = crisisEditProfileService.findByUserId(crisisDto.getId());
            if (!crisisProfileDto1.isEmpty()) {

                for (CrisisProfileDto dto : crisisProfileDto1) {
                    if (dto.getStatus().equals("active") || dto.getStatus() == ("active")) {
                        session.setAttribute("profile", "/image/" + dto.getName());
                    }
                }
                return "CrisisProfile";
            }
        } else {
            System.out.println("Login failed");
            model.addAttribute("message", "Login failed");
            CrisisDto crisisDto1 = crisisService.findByEmail(email);
            if (crisisDto1 != null) {
                int atmps = crisisDto1.getFailedLoginAttempts();
                if (atmps >= 3) {
                    crisisService.updateAccountLock(true, email);
                    model.addAttribute("msg", "Your account has been locked due to multiple failed login attempts. Please click forgot password.");
                    model.addAttribute("action", "edit");
                } else {
                    atmps = crisisDto1.getFailedLoginAttempts() + 1;
                    crisisService.updateFailedLoginAttempts(atmps, email);
                    crisisService.updateAccountLock(false, email);
                    model.addAttribute("msg", "Invalid login attempts left: " + (3 - atmps));
                }
            } else {
                model.addAttribute("msg", "Invalid email address");
            }
            return "CrisisLogin";
        }
        return "CrisisProfile";
    }
}
