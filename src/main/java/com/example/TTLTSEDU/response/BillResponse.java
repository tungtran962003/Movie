package com.example.TTLTSEDU.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillResponse {

    private String movieName;

    private String cinemaName;

    private String roomName;

    private String scheduleName;

    private String foodName;

    private String promotionName;
}
