package com.xworkz.crisis.controller;

import com.xworkz.crisis.dto.CrisisDto;
import com.xworkz.crisis.dto.CrisisEditProfileDto;
import com.xworkz.crisis.dto.CrisisProfileDto;
import com.xworkz.crisis.model.service.CrisisEditProfileService;
import com.xworkz.crisis.model.service.CrisisProfileService;
import com.xworkz.crisis.model.service.CrisisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller

public class CrisisProfileController {

    private Logger log=  LogManager.getLogger(CrisisProfileController.class);
    @Autowired
    private CrisisProfileService crisisProfileService;

    @Autowired
    private CrisisEditProfileService crisisEditProfileService;

    @Autowired
    private CrisisService crisisService;


    @PostMapping("/editprofile")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model, CrisisProfileDto crisisProfileDto, CrisisEditProfileDto crisisEditProfileDto, HttpSession session) {
        if (file.isEmpty()) {
            model.addAttribute("msg", "Please select a profile picture.");
            return "crisisProfileEdit";
        }
        CrisisDto crisisDto = crisisService.findByEmail(crisisEditProfileDto.getEmail());

        log.info("Image Upload");
        try {
            String filename = file.getOriginalFilename();
            String edit = crisisDto.getEmail() + "_" + filename;
            Long size = file.getSize();
            String fileType = file.getContentType();
            System.out.println("File Name: " + filename + ", Size: " + size + ", Content Type: " + fileType);

            byte[] bytes = file.getBytes();
            Path path = Paths.get("D:\\imageupload\\" + edit);
            System.out.println(path);
            Files.write(path, bytes);


            if (crisisDto == null) {
                System.out.println("User not found.");
                model.addAttribute("msg", "User not found.");
                return "crisisProfileEdit";
            }

            List<CrisisProfileDto> existingProfiles = crisisEditProfileService.findByUserId(crisisDto.getId());
            if (!existingProfiles.isEmpty()) {
                for (CrisisProfileDto dto : existingProfiles) {
                    crisisEditProfileService.updateProfileStatus(dto.getUserId());
                }
            }

            crisisProfileDto.setName(edit);
            crisisProfileDto.setSize(size);
            crisisProfileDto.setFileType(fileType);
            crisisProfileDto.setStatus("active");
            crisisProfileDto.setUserId(crisisDto.getId());
            crisisProfileDto.setCreatedBy(crisisDto.getFirstName());
            boolean isSaved = crisisProfileService.saveAndValidate(crisisProfileDto);

            crisisDto.setFirstName(crisisEditProfileDto.getFirstName());
            crisisDto.setLastName(crisisEditProfileDto.getLastName());
            crisisDto.setAddress(crisisEditProfileDto.getAddress());
            crisisDto.setContactNumber(crisisEditProfileDto.getContactNumber());

            crisisEditProfileService.updateUserDetails(crisisDto);

            if (isSaved) {
                model.addAttribute("msg", "Profile updated successfully.");
            } else {
                model.addAttribute("msg", "Profile update failed.");
            }


        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload " + file.getOriginalFilename());
            model.addAttribute("status", "Failed");
        }

        return "CrisisProfile";
    }
}
