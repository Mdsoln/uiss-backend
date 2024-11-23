package com.uiss.home.controller;

import com.uiss.home.HomePageService;
import com.uiss.home.models.HomeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/admin")
@RequiredArgsConstructor
public class AdminPageController {

    private final HomePageService homePageService;

    @PostMapping("/insert/home-page-details")
    public ResponseEntity<String> createHomePageDetails(
            @RequestBody @Valid HomeRequest homeRequest
    ) {
        String response = homePageService.createHomePageDetails(homeRequest);
        return new ResponseEntity<>("Home details saved successfully with ID: "+response, HttpStatus.CREATED);
    }

    // TODO: 11/23/24 updating the home details 
}
