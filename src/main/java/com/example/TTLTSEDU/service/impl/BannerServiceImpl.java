package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Banner;
import com.example.TTLTSEDU.repository.BannerRepository;
import com.example.TTLTSEDU.request.BannerRequest;
import com.example.TTLTSEDU.service.BannerService;
import com.example.TTLTSEDU.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public List<Banner> getAll() {
        return bannerRepository.findAll();
    }

    @Override
    public void add(BannerRequest bannerRequest) {
        String uploadDir = "./src/main/resources/static/assets/banner/";
        String fileName = bannerRequest.getImageUrl().getOriginalFilename();
        String newFileName = "banner_" + new Date().getTime();
        String bannerPath = FileUtil.copyFile(bannerRequest.getImageUrl(), fileName, uploadDir);
        String imageUrl = FileUtil.rename(bannerPath, newFileName);
        Banner banner = bannerRequest.add(new Banner());
        banner.setImageUrl(imageUrl);
        bannerRepository.save(banner);
    }

    @Override
    public Boolean update(BannerRequest bannerRequest, Integer id) {
        Banner banner = bannerRepository.findById(id).orElse(null);
        if (banner != null) {
            String uploadDir = "./src/main/resources/static/assets/banner/";
            String fileName = bannerRequest.getImageUrl().getOriginalFilename();
            String newFileName = "banner_" + new Date().getTime();
            String bannerPath = FileUtil.copyFile(bannerRequest.getImageUrl(), fileName, uploadDir);
            String imageUrl = FileUtil.rename(bannerPath, newFileName);
            banner = bannerRequest.update(banner, id);
            banner.setImageUrl(imageUrl);
            bannerRepository.save(banner);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Banner banner = bannerRepository.findById(id).orElse(null);
        if (banner != null) {
            bannerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
