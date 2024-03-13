package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Schedule;
import com.example.TTLTSEDU.request.ScheduleRequest;

import java.text.ParseException;
import java.util.List;

public interface ScheduleService {

    List<Schedule> getAll();

    Boolean add(ScheduleRequest scheduleRequest) throws ParseException;

    Boolean update(ScheduleRequest scheduleRequest, Integer id) throws ParseException;

    Boolean delete(Integer id);
}
