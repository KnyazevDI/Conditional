package ru.netology.conditional.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.conditional.Profile.DevProfile;
import ru.netology.conditional.Profile.ProductionProfile;
import ru.netology.conditional.Profile.SystemProfile;

@RestController
@RequestMapping("/")
public class ProfileController {

    @Autowired
    private SystemProfile profile;


    public ProfileController(SystemProfile profile) {
        this.profile = profile;
    }

    @GetMapping("/")
    public String getProfile() {
        return profile.getProfile();
    }

}