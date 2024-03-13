package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Banner;
import com.example.TTLTSEDU.request.BannerRequest;

import java.util.List;

public interface BannerService {

    List<Banner> getAll();

    void add(BannerRequest bannerRequest);

    Boolean update(BannerRequest bannerRequest, Integer id);

    Boolean delete(Integer id);
}
