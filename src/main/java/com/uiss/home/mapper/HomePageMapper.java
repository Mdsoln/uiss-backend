package com.uiss.home.mapper;

import com.uiss.home.HomeIdGenerator;
import com.uiss.home.entity.Home;
import com.uiss.home.models.HomeRequest;
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
}
