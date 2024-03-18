package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.Banner;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BannerRequest {

    @NotBlank
    private MultipartFile  imageUrl;

    @NotBlank
    private String title;

    public Banner add(Banner banner) {
        banner.setTitle(this.title);
        banner.setImageUrl(String.valueOf(this.imageUrl));
        return banner;
    }

    public Banner update(BannerRequest bannerRequest, Integer id) {
        Banner banner = new Banner();
        banner.setId(id);
        banner.setTitle(bannerRequest.getTitle());
        return banner;
    }
}
