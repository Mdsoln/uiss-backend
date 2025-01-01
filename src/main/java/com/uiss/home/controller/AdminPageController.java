package com.uiss.home.controller;


import com.uiss.home.HomePageService;
import com.uiss.home.exception.NullValueException;
import com.uiss.home.models.*;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create home page details for section 1")
    @PostMapping("/insert/home-page-details")
    public ResponseEntity<String> createHomePageDetails(
            @RequestBody @Valid HomeRequest homeRequest
    ) {
        String response = homePageService.createHomePageDetails(homeRequest);
        return new ResponseEntity<>("Home details saved successfully with ID: "+response, HttpStatus.CREATED);
    }

    @CrossOrigin()
    @Operation(summary = "Update specific home page title with home page ID")
    @PostMapping("/update/home-page-title/{home-id}")
    public ResponseEntity<String> updateHomePageTitle(@PathVariable("home-id") String homeId, @RequestParam(name = "homeTitle") String homeTitle) {
         if (StringUtils.isEmpty(homeId) || StringUtils.isEmpty(homeTitle) || StringUtils.isBlank(homeTitle) || StringUtils.isBlank(homeId)) {
             return new ResponseEntity<>("Home details ID is required",HttpStatus.BAD_REQUEST);
         }
         homePageService.updateHomePageDetails(homeId, homeTitle);
         return new ResponseEntity<>("Home details updated successfully with ID: "+homeId, HttpStatus.OK);
    }

    @CrossOrigin()
    @Operation(summary = "Update home page details with home page ID")
    @PostMapping("/update/home-page-details/{home-id}")
    public ResponseEntity<String> updateHomeDetails(@PathVariable("home-id") String homeId, @RequestBody @Valid HomeRequest homeRequest) {
        if (StringUtils.isEmpty(homeId)) {
            return new ResponseEntity<>("Home ID is required",HttpStatus.BAD_REQUEST);
        }

        homePageService.updateHomePageDetails(homeId, homeRequest);
        return new ResponseEntity<>("Home details updated successfully with ID: "+homeId, HttpStatus.OK);
    }

    @CrossOrigin()
    @Operation(summary = "Create section 2 details(Start with You details)")
    @PostMapping("/section-two/insert-start-with-details")
    public ResponseEntity<String> insertStartWithDetails(
            @RequestParam(name = "title", defaultValue = "It Starts With You") String sectionTitle,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "imagePath", required = false) String imagePath
    ) {
        if (StringUtils.isEmpty(sectionTitle) || StringUtils.isEmpty(description) || StringUtils.isEmpty(imagePath)) {
            throw new NullValueException("All fields are required!");
        }
        String response = homePageService.createStartWithYouDetails(sectionTitle, description, imagePath);

        return ResponseEntity.ok("Start with details section created successfully with ID section: " + response);
    }

    @CrossOrigin()
    @Operation(summary = "Update section 2 details(Start with You details) by startwith section ID")
    @PostMapping("/section-two/edit-start-with-details/{section-id}")
    public ResponseEntity<String> editStartWithDetails(@PathVariable("section-id") Integer sectionId,
              @RequestParam(name = "title", required = false) String sectionTitle,
              @RequestParam(name = "description", required = false) String description,
              @RequestParam(name = "imagePath") String imagePath) {
        if (sectionId == null || sectionId <= 0) {
            throw new NullValueException("Section ID is invalid!");
        }
        homePageService.editStartWithDetails(sectionId, imagePath, sectionTitle, description);
        return ResponseEntity.ok("Section details updated successfully with ID section: " + sectionId);
    }

    @CrossOrigin()
    @Operation(summary = "Create section 3 programmes(Explore our programmes)")
    @PostMapping("/insert/programmes-details")
    public ResponseEntity<String> exploreOurProgrammes(@RequestBody @Valid ProgramRequest programRequest) {
        return new ResponseEntity<>(homePageService.exploreOurProgrammes(programRequest), HttpStatus.CREATED);
    }


    @CrossOrigin()
    @Operation(summary = "Create section 4 upcoming events")
    @PostMapping("/upcoming-events/create-event")
    public ResponseEntity<String> createEvent(@RequestBody @Valid UpcomingEvent eventRequest) {
         return new ResponseEntity<>(homePageService.createUpComingEvent(eventRequest), HttpStatus.CREATED);
    }

    @CrossOrigin()
    @Operation(summary = "Update upcoming event details with specific event ID")
    @PostMapping("/update/upcoming-events/edit-event/{event-id}")
    public ResponseEntity<String> updateUpcomingEvent(@PathVariable("event-id") Integer eventId, @RequestBody @Valid UpcomingEvent upcomingEventRequest) {
        if (eventId == null){
            throw new NullValueException("Event ID is invalid!");
        }

        homePageService.updateUpcomingEvent(eventId, upcomingEventRequest);

        return new ResponseEntity<>("Upcoming event updated successfully with ID: "+eventId, HttpStatus.OK);
    }

    @CrossOrigin()
    @Operation(summary = "Create section 5 testimonials")
    @PostMapping("/testimonials/create-testimonial")
    public ResponseEntity<String> createTestimonial(@RequestBody @Valid TestimonialRequest testimonialRequest) {
        return new ResponseEntity<>(homePageService.createTestimonial(testimonialRequest), HttpStatus.CREATED);
    }

    @CrossOrigin()
    @Operation(summary = "Update section 5 testimonials with testimonial ID")
    @PostMapping("/update/testimonials/edit-testimonial/{testimonial-id}")
    public ResponseEntity<String> updateTestimonial(@PathVariable("testimonial-id") Integer testimonialId, @RequestBody @Valid TestimonialRequest testimonialRequest) {
        if (testimonialId == null){
            throw new NullValueException("Testimonial ID is invalid!");
        }

        homePageService.updateTestimonial(testimonialId, testimonialRequest);
        return new ResponseEntity<>("Testimonial updated successfully with ID: "+testimonialId, HttpStatus.OK);

    }

    @CrossOrigin()
    @Operation(summary = "Create a new Quote")
    @PostMapping("/uiss-quotes/create-quotes")
    public ResponseEntity<String> createQuotes(@RequestBody @Valid QuoteRequest request){
        return new ResponseEntity<>(homePageService.createQuote(request), HttpStatus.CREATED);
    }

    @CrossOrigin()
    @Operation(summary = "Update Quote details")
    @PostMapping("/uiss-quotes/update-quote/{quote-id}")
    public ResponseEntity<String> updateQuote(@PathVariable("quote-id") Integer quoteId, @RequestBody @Valid QuoteRequest request){
        if (quoteId == null){
            throw new NullValueException("Quote ID is invalid!");
        }
        return new ResponseEntity<>(homePageService.updateQuote(quoteId, request), HttpStatus.CREATED);
    }

}
