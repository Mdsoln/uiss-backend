package com.uiss.home;
import com.uiss.home.mapper.HomePageMapper;
import com.uiss.home.models.HomeRequest;
import com.uiss.home.repository.HomeRepository;
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
}
