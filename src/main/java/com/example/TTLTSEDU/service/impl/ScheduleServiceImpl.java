package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Cinema;
import com.example.TTLTSEDU.entity.Schedule;
import com.example.TTLTSEDU.repository.ScheduleRepository;
import com.example.TTLTSEDU.request.ScheduleRequest;
import com.example.TTLTSEDU.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public String generateCode() {
        Schedule scheduleFinalPresent = scheduleRepository.findTopByOrderByIdDesc();
        if (scheduleFinalPresent == null) {
            return "S00001";
        }
        Integer idFinalPresent = scheduleFinalPresent.getId() + 1;
        String code = String.format("%05d", idFinalPresent);
        return "S" + code;
    }

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public void add(ScheduleRequest scheduleRequest) throws ParseException {
        Schedule schedule = scheduleRequest.add(new Schedule());
        schedule.setCode(generateCode());
        scheduleRepository.save(schedule);
    }

    @Override
    public Boolean update(ScheduleRequest scheduleRequest, Integer id) throws ParseException {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        if (schedule != null) {
            schedule = scheduleRequest.update(scheduleRequest, id, schedule.getCode());
            scheduleRepository.save(schedule);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        if (schedule != null) {
            schedule.setIsActive(false);
            scheduleRepository.save(schedule);
            return true;
        } else {
            return false;
        }
    }
}
