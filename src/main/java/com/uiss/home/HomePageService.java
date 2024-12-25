package com.uiss.home;
import com.uiss.home.entity.Event;
import com.uiss.home.entity.Home;
import com.uiss.home.entity.Programmes;
import com.uiss.home.entity.StartWith;
import com.uiss.home.exception.HomeDetailsNotFoundException;
import com.uiss.home.mapper.HomePageMapper;
import com.uiss.home.models.HomeRequest;
import com.uiss.home.models.ProgramRequest;
import com.uiss.home.models.TestimonialRequest;
import com.uiss.home.models.UpcomingEvent;
import com.uiss.home.models.responses.HomeResponse;
import com.uiss.home.models.responses.ProgrammesResponse;
import com.uiss.home.models.responses.SectionResponse;
import com.uiss.home.repository.*;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomePageService {

    private final HomeRepository homeRepository;
    private final HomePageMapper mapper;
    private final StartWithYouRepository startWithYouRepository;
    private final ProgrammesRepository programmesRepository;
    private final EventRepository eventRepository;
    private final TestimonialsRepository testimonialsRepository;

    public String createHomePageDetails(HomeRequest homeRequest) {
        var response = mapper.toHome(homeRequest);
        return homeRepository.save(response).getHomeId();
    }

    public void updateHomePageDetails(String homeId, String homeTitle) {
        var home = homeRepository.findByHomeId(homeId);
        if (home == null) {
            throw new HomeDetailsNotFoundException(String.format("Can not update home title:: Home details with id %s not found", homeId));
        }

        home.setHomeTitle(homeTitle);
        homeRepository.save(home);
    }

    public void updateHomePageDetails(String homeId, HomeRequest homeRequest){
        var home = homeRepository.findByHomeId(homeId);
        if (home == null) {
            throw new HomeDetailsNotFoundException(String.format("Can not update home details:: Home details with id %s not found", homeId));
        }

        mergeCustomer(home, homeRequest);
        homeRepository.save(home);

    }

    private void mergeCustomer(Home home, HomeRequest homeRequest) {
        if (StringUtils.isNotBlank(homeRequest.homeTitle())){
            home.setHomeTitle(homeRequest.homeTitle());
        }
        if (StringUtils.isNotBlank(homeRequest.homeDescription())){
            home.setHomeDescription(homeRequest.homeDescription());
        }
        if (StringUtils.isNotBlank(homeRequest.homeImageUrl())){
            home.setBackgroundImageUrl(homeRequest.homeImageUrl());
        }

    }

    public String createStartWithYouDetails(String sectionTitle, String description, String imagePath) {
        var response = mapper.toStartWithYou(sectionTitle, description, imagePath);
        return startWithYouRepository.save(response).getId().toString();
    }

    public void editImagePath(Integer sectionId, String imagePath) {
        var response = startWithYouRepository.findById(sectionId)
                .orElseThrow(()-> new EntityNotFoundException("Can not update image path:: Start with you section with ID %s not found"+sectionId));

        StartWith.builder().imagePath(imagePath).build();
        startWithYouRepository.save(response);
    }

    public String exploreOurProgrammes(ProgramRequest programRequest) {
        Programmes programmes = Programmes.builder()
                .about(programRequest.about())
                .mission(programRequest.mission())
                .vision(programRequest.vision())
                .youTubeUrl(programRequest.youTubeUrl())
                .build();
        programmesRepository.save(programmes);
        return "Programmes saved successfully";
    }

    public String createUpComingEvent(UpcomingEvent request) {
        LocalDate eventDate = LocalDate.parse(request.date(), DateTimeFormatter.ISO_DATE);
        String dayOfWeek = eventDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        Event event = Event.builder()
                .title(request.title())
                .description(request.description())
                .imageUrl(request.imageUrl())
                .date(request.date())
                .time(request.time())
                .dayOfWeek(dayOfWeek)
                .build();
        eventRepository.save(event);
        return "Event saved successfully";
    }

    public void updateUpcomingEvent(Integer eventId, UpcomingEvent upcomingEventRequest) {
        var updateEvent = eventRepository.findById(eventId)
                .orElseThrow(()-> new EntityNotFoundException("Can not update event:: event ID %s not found"+eventId));

        updateEvent.setTitle(upcomingEventRequest.title());
        updateEvent.setDescription(upcomingEventRequest.description());
        updateEvent.setImageUrl(upcomingEventRequest.imageUrl());
        updateEvent.setTime(upcomingEventRequest.time());
        updateEvent.setDate(upcomingEventRequest.date());
        eventRepository.save(updateEvent);
    }

    public String createTestimonial(TestimonialRequest testimonialRequest) {
        var testimonial = mapper.toTestimonial(testimonialRequest);
        return testimonialsRepository.save(testimonial).getId().toString();
    }

    public void updateTestimonial(Integer testimonialId, TestimonialRequest testimonialRequest) {
        var testimonial = testimonialsRepository.findById(testimonialId)
                .orElseThrow(()-> new EntityNotFoundException("Can not update testimonial:: testimonial ID %s not found"+testimonialId));

        testimonial.setDescription(testimonialRequest.description());
        testimonial.setImageUrl(testimonialRequest.imageUrl());
        testimonial.setPosition(testimonialRequest.position());
        testimonial.setFullname(testimonialRequest.fullname());
        testimonialsRepository.save(testimonial);
    }

    public HomeResponse getHomePageDetails() {
        Home home = homeRepository.findAll().stream()
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException("Can not get home page:: Home page not found"));
        return HomeResponse.builder()
                .homeTitle(home.getHomeTitle())
                .homeDescription(home.getHomeDescription())
                .homeImage(home.getBackgroundImageUrl())
                .build();
    }

    public HomeResponse getHomePageDetails(String homeId) {
        Home home = homeRepository.findByHomeId(homeId);
        if (home == null) {
            throw new EntityNotFoundException("Cannot find home page with ID " + homeId);
        }
        return HomeResponse.builder()
                .homeTitle(home.getHomeTitle())
                .homeDescription(home.getHomeDescription())
                .homeImage(home.getBackgroundImageUrl())
                .build();
    }

    public void editStartWithDetails(Integer sectionId, String imagePath, String sectionTitle, String description) {
        var section = startWithYouRepository.findById(sectionId)
                .orElseThrow(()-> new EntityNotFoundException("Can not update section:: section ID %s not found"+sectionId));
        section.setTitle(sectionTitle);
        section.setImagePath(imagePath);
        section.setDescription(description);
        startWithYouRepository.save(section);
    }

    public SectionResponse getSectionTwoStartWithDetails() {
        StartWith startWith = startWithYouRepository.findAll().stream()
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException("Can not get section:: section not found"));
        return SectionResponse.builder()
                .title(startWith.getTitle())
                .description(startWith.getDescription())
                .imageUrl(startWith.getImagePath())
                .build();
    }

    public SectionResponse getSectionTwoStartWithDetails(Integer sectionId) {
        StartWith startWith = startWithYouRepository.findById(sectionId)
                .orElseThrow(()-> new EntityNotFoundException("Can not get section:: section not found with ID " + sectionId));
        return SectionResponse.builder()
                .title(startWith.getTitle())
                .description(startWith.getDescription())
                .imageUrl(startWith.getImagePath())
                .build();
    }

    public List<ProgrammesResponse> findAllProgrammes() {
        List<Programmes> programmes = programmesRepository.findAll();
        return programmes.stream().map(mapper::toProgrammesResponse).collect(Collectors.toList());
    }
}
