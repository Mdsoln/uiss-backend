package com.uiss.home.mapper;

import com.uiss.home.HomeIdGenerator;
import com.uiss.home.entity.Home;
import com.uiss.home.entity.StartWith;
import com.uiss.home.entity.Testimonials;
import com.uiss.home.models.HomeRequest;
import com.uiss.home.models.TestimonialRequest;
import org.springframework.stereotype.Service;

@Service
public class HomePageMapper {

    public Home toHome(HomeRequest homeRequest) {
        return Home.builder()
                .homeId(HomeIdGenerator.generateHomeDetailsId())
                .homeTitle(homeRequest.homeTitle())
                .homeDescription(homeRequest.homeDescription())
                .backgroundImageUrl(homeRequest.homeImageUrl())
                .build();
    }

    public StartWith toStartWithYou(String sectionTitle, String description, String imagePath) {
        return StartWith.builder()
                .title(sectionTitle)
                .description(description)
                .imagePath(imagePath)
                .build();
    }

    public Testimonials toTestimonial(TestimonialRequest testimonialRequest) {
        return Testimonials.builder()
                .description(testimonialRequest.description())
                .imageUrl(testimonialRequest.imageUrl())
                .position(testimonialRequest.position())
                .fullname(testimonialRequest.fullname())
                .build();
    }
}
