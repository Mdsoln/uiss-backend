package com.uiss.home.controller;

import com.uiss.home.HomePageService;
import com.uiss.home.models.responses.HomeResponse;
import com.uiss.home.models.responses.ProgrammesResponse;
import com.uiss.home.models.responses.SectionResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @CrossOrigin()
    @GetMapping("/section-two/start-with-details")
    public ResponseEntity<SectionResponse> getSectionTwoStartWithDetails() {
        return ResponseEntity.ok(homePageService.getSectionTwoStartWithDetails());
    }

    @CrossOrigin()
    @GetMapping("/section-two/start-with-details/{section-id}")
    public ResponseEntity<SectionResponse> getSectionTwoStartWithDetailsById(@PathVariable("section-id") Integer sectionId) {
        return ResponseEntity.ok(homePageService.getSectionTwoStartWithDetails(sectionId));
    }

    @CrossOrigin()
    @GetMapping("/programmes/explore-our-programmes")
    public ResponseEntity<List<ProgrammesResponse>> getProgrammesExploreOurProgrammes() {
        return ResponseEntity.ok(homePageService.findAllProgrammes());
    }
}
