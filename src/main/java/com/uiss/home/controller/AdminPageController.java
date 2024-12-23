package com.uiss.home.controller;


import com.uiss.home.HomePageService;
import com.uiss.home.exception.NullValueException;
import com.uiss.home.models.HomeRequest;
import com.uiss.home.models.ProgramRequest;
import com.uiss.home.models.TestimonialRequest;
import com.uiss.home.models.UpcomingEvent;
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

    @CrossOrigin()
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
    @PostMapping("/section-two/edit-image-path/{section-id}")
    public ResponseEntity<String> editImagePath(@PathVariable("section-id") Integer sectionId, @RequestParam(name = "imagePath") String imagePath) {
        if (sectionId == null || sectionId <= 0) {
            throw new NullValueException("Section ID is invalid!");
        }
        homePageService.editImagePath(sectionId, imagePath);
        return ResponseEntity.ok("Image path updated successfully with ID section: " + sectionId);
    }

    @CrossOrigin()
    @PostMapping("/programmes/explore-our-programmes")
    public ResponseEntity<String> exploreOurProgrammes(@RequestBody @Valid ProgramRequest programRequest) {
        return new ResponseEntity<>(homePageService.exploreOurProgrammes(programRequest), HttpStatus.CREATED);
    }


    @CrossOrigin()
    @PostMapping("/upcoming-events/create-event")
    public ResponseEntity<String> createEvent(@RequestBody @Valid UpcomingEvent eventRequest) {
         return new ResponseEntity<>(homePageService.createUpComingEvent(eventRequest), HttpStatus.CREATED);
    }

    @CrossOrigin()
    @PostMapping("/update/upcoming-events/edit-event/{event-id}")
    public ResponseEntity<String> updateUpcomingEvent(@PathVariable("event-id") Integer eventId, @RequestBody @Valid UpcomingEvent upcomingEventRequest) {
        if (eventId == null){
            throw new NullValueException("Event ID is invalid!");
        }

        homePageService.updateUpcomingEvent(eventId, upcomingEventRequest);

        return new ResponseEntity<>("Upcoming event updated successfully with ID: "+eventId, HttpStatus.OK);
    }

    @CrossOrigin()
    @PostMapping("/testimonials/create-testimonial")
    public ResponseEntity<String> createTestimonial(@RequestBody @Valid TestimonialRequest testimonialRequest) {
        return new ResponseEntity<>(homePageService.createTestimonial(testimonialRequest), HttpStatus.CREATED);
    }

    @CrossOrigin()
    @PostMapping("/update/testimonials/edit-testimonial/{testimonial-id}")
    public ResponseEntity<String> updateTestimonial(@PathVariable("testimonial-id") Integer testimonialId, @RequestBody @Valid TestimonialRequest testimonialRequest) {
        if (testimonialId == null){
            throw new NullValueException("Testimonial ID is invalid!");
        }

        homePageService.updateTestimonial(testimonialId, testimonialRequest);
        return new ResponseEntity<>("Testimonial updated successfully with ID: "+testimonialId, HttpStatus.OK);

    }

}
