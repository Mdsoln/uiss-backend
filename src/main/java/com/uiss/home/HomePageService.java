package com.uiss.home;
import com.uiss.home.entity.Home;
import com.uiss.home.exception.HomeDetailsNotFoundException;
import com.uiss.home.mapper.HomePageMapper;
import com.uiss.home.models.HomeRequest;
import com.uiss.home.repository.HomeRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomePageService {

    private final HomeRepository homeRepository;
    private final HomePageMapper mapper;

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
}
