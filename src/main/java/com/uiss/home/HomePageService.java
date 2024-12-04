package com.uiss.home;
import com.uiss.home.entity.Home;
import com.uiss.home.entity.Programmes;
import com.uiss.home.entity.StartWith;
import com.uiss.home.exception.HomeDetailsNotFoundException;
import com.uiss.home.mapper.HomePageMapper;
import com.uiss.home.models.HomeRequest;
import com.uiss.home.models.ProgramRequest;
import com.uiss.home.repository.HomeRepository;
import com.uiss.home.repository.ProgrammesRepository;
import com.uiss.home.repository.StartWithYouRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomePageService {

    private final HomeRepository homeRepository;
    private final HomePageMapper mapper;
    private final StartWithYouRepository startWithYouRepository;
    private final ProgrammesRepository programmesRepository;

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
}
