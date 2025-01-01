package com.uiss.home.controller;

import com.uiss.home.HomePageService;
import com.uiss.home.models.PageResponse;
import com.uiss.home.models.responses.HomeResponse;
import com.uiss.home.models.responses.ProgrammesResponse;
import com.uiss.home.models.responses.SectionResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Operation(summary = "Get home page details for section 1(default)")
    @GetMapping("/home-page-details")
    public ResponseEntity<HomeResponse> getHomePageDetails() {
        return ResponseEntity.ok(homePageService.getHomePageDetails());
    }

    @CrossOrigin()
    @Operation(summary = "Get specific home page details for section 1 by ID")
    @GetMapping("/home-page-details/{home-id}")
    public ResponseEntity<HomeResponse> getHomePageDetailsById(@PathVariable("home-id") String homeId) {
        return ResponseEntity.ok(homePageService.getHomePageDetails(homeId));
    }

    @CrossOrigin()
    @Operation(summary = "Get home page details for section 2(Start with You details)")
    @GetMapping("/section-two/start-with-details")
    public ResponseEntity<SectionResponse> getSectionTwoStartWithDetails() {
        return ResponseEntity.ok(homePageService.getSectionTwoStartWithDetails());
    }

    @CrossOrigin()
    @Operation(summary = "Get specific home page details for section 2(Start with You details) by ID")
    @GetMapping("/section-two/start-with-details/{section-id}")
    public ResponseEntity<SectionResponse> getSectionTwoStartWithDetailsById(@PathVariable("section-id") Integer sectionId) {
        return ResponseEntity.ok(homePageService.getSectionTwoStartWithDetails(sectionId));
    }

    @CrossOrigin()
    @Operation(summary = "Get all programmes")
    @GetMapping("/programmes/explore-our-programmes")
    public ResponseEntity<List<ProgrammesResponse>> getProgrammesExploreOurProgrammes() {
        return ResponseEntity.ok(homePageService.findAllProgrammes());
    }

    @CrossOrigin()
    @Operation(summary = "Get all upcoming events with pageable response")
    @GetMapping("/upcoming-event/get-all-events")
    public ResponseEntity<PageResponse<Object[]>> getAllUpcomingEvents(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "6") int pageSize
    ) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Error::Invalid page number");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("Error::Invalid page size");
        }

        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by("date","time","dayOfWeek"));
        Page<Object[]> customers = homePageService.findAllUpcomingEvents(pageable);

        PageResponse<Object[]> response = new PageResponse<>(
                customers.getContent(),
                customers.getNumber(),
                customers.getTotalPages(),
                customers.getTotalElements()
        );

        return ResponseEntity.ok(response);
    }

    @CrossOrigin()
    @Operation(summary = "Get all testimonials with pageable response")
    @GetMapping("/testimonials/get-all-testimonials")
    public ResponseEntity<PageResponse<Object[]>> getAllTestimonials(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "6") int pageSize
    ) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Error::Invalid page number");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("Error::Invalid page size");
        }

        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by("createdAt"));
        Page<Object[]> customers = homePageService.findAllTestimonials(pageable);

        PageResponse<Object[]> response = new PageResponse<>(
                customers.getContent(),
                customers.getNumber(),
                customers.getTotalPages(),
                customers.getTotalElements()
        );

        return ResponseEntity.ok(response);
    }

    @CrossOrigin()
    @Operation(summary = "Get all UISS Quotes with pageable response")
    @GetMapping("/uiss-quotes/get-all-quotes")
    public ResponseEntity<PageResponse<Object[]>> getAllQuotes(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "6") int pageSize
    ) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Error::Invalid page number");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("Error::Invalid page size");
        }

        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by("author"));
        Page<Object[]> customers = homePageService.findAllQuotes(pageable);

        PageResponse<Object[]> response = new PageResponse<>(
                customers.getContent(),
                customers.getNumber(),
                customers.getTotalPages(),
                customers.getTotalElements()
        );

        return ResponseEntity.ok(response);
    }
}
