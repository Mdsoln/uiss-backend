package com.uiss.home.controller;

import com.uiss.home.HomePageService;
import com.uiss.home.models.HomeRequest;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping(path = "/api/v1/admin")
@RequiredArgsConstructor
public class AdminPageController {

    private final HomePageService homePageService;

    @CrossOrigin()
    @PostMapping("/insert/home-page-details")
    public ResponseEntity<String> createHomePageDetails(
            @RequestBody @Valid HomeRequest homeRequest
    ) {
        String response = homePageService.createHomePageDetails(homeRequest);
        return new ResponseEntity<>("Home details saved successfully with ID: "+response, HttpStatus.CREATED);
    }

    @CrossOrigin()
    @PostMapping("/update/home-page-title/{home-id}")
    public ResponseEntity<String> updateHomePageTitle(@PathVariable("home-id") String homeId, @RequestParam(name = "homeTitle") String homeTitle) {
         if (StringUtils.isEmpty(homeId) || StringUtils.isEmpty(homeTitle) || StringUtils.isBlank(homeTitle) || StringUtils.isBlank(homeId)) {
             return new ResponseEntity<>("Home details ID is required",HttpStatus.BAD_REQUEST);
         }
         homePageService.updateHomePageDetails(homeId, homeTitle);
         return new ResponseEntity<>("Home details updated successfully with ID: "+homeId, HttpStatus.OK);
    }

    @CrossOrigin()
    @PostMapping("/update/home-page-details/{home-id}")
    public ResponseEntity<String> updateHomeDetails(@PathVariable("home-id") String homeId, @RequestBody @Valid HomeRequest homeRequest) {
        if (StringUtils.isEmpty(homeId)) {
            return new ResponseEntity<>("Home ID is required",HttpStatus.BAD_REQUEST);
        }

        homePageService.updateHomePageDetails(homeId, homeRequest);
        return new ResponseEntity<>("Home details updated successfully with ID: "+homeId, HttpStatus.OK);
    }
    // TODO: 11/23/24 updating the home details 
}
