package com.uiss.home.controller;

import com.uiss.home.HomePageService;
import com.uiss.home.models.responses.HomeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping(path = "/api/v1/home-page")
@RequiredArgsConstructor
public class HomePageController {
    private final HomePageService homePageService;

    @CrossOrigin()
    @GetMapping("/home-page-details")
    public ResponseEntity<HomeResponse> getHomePageDetails() {
        return ResponseEntity.ok(homePageService.getHomePageDetails());
    }

    @CrossOrigin()
    @GetMapping("/home-page-details/{home-id}")
    public ResponseEntity<HomeResponse> getHomePageDetailsById(@PathVariable("home-id") String homeId) {
        return ResponseEntity.ok(homePageService.getHomePageDetails(homeId));
    }
}
