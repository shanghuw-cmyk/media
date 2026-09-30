package com.social.media.controllers;

import com.social.media.models.SocialUser;
import com.social.media.services.SocialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialController {

    private SocialService socialService;

    // 建構子：Spring 啟動時會自動找 SocialService 的 Bean 傳進來
    public SocialController(SocialService socialService) {
        this.socialService = socialService;
    }

    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>> getUsers() {
        return new ResponseEntity<>(socialService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/social/users")
    public ResponseEntity<SocialUser> saveUser(@RequestBody SocialUser socialUser) {
        SocialUser createdUser = socialService.saveUser(socialUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/social/users/{userId}")
    public ResponseEntity<String> deleteUsers(@PathVariable Long userId) {
        socialService.deleteUser(userId);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);

    }

}

