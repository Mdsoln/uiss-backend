package com.uiss.home.models.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HomeResponse {
    private String homeTitle;
    private String homeDescription;
    private String homeImage;
}
