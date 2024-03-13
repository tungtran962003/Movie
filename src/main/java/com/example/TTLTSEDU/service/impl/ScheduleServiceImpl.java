package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Cinema;
import com.example.TTLTSEDU.entity.Schedule;
import com.example.TTLTSEDU.repository.ScheduleRepository;
import com.example.TTLTSEDU.request.ScheduleRequest;
import com.example.TTLTSEDU.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
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

    public Boolean checkTime(Date startAt, Date endAt, Integer movieId, Integer roomId) {
        List<Schedule> listSchedule = scheduleRepository.checkTimeSchedule(startAt, endAt, movieId, roomId);
        if (listSchedule == null) {
            return true;
        } else {
            return false;
        }
    }

//    public Date generateEndAt(Date startAt, Integer movieDuration) {
//        Integer seconds = movieDuration * 60;
//        LocalTime localTime = LocalTime.ofSecondOfDay(seconds);
//    }

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Boolean add(ScheduleRequest scheduleRequest) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startAt = sdf.parse(scheduleRequest.getStartAt());
        Date endAt = sdf.parse(scheduleRequest.getEndAt());
        if (checkTime(startAt, endAt, scheduleRequest.getMovieId(), scheduleRequest.getRoomId())) {
            Schedule schedule = scheduleRequest.add(new Schedule());
            schedule.setCode(generateCode());
            scheduleRepository.save(schedule);
            return true;
        } else {
            return false;
        }
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
