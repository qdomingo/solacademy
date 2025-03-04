package com.example.solacademy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.solacademy.model.Schedule;

@Mapper
public interface ScheduleMapper {
	
	List<Schedule> getAllSchedules();
	List<Schedule> getScheduleById(Long id);
	List<Schedule> getScheduleByStudentId(Long id);
	int createSchedule(Schedule schedule);
	int updateSchedule(Schedule schedule);
	int deleteSchedule(Long id);
	int deleteStudentSchedule(Long id);
	int deleteScheduleSerie(Long id, String serie_id);
	Long getMaxScheduleID();

}
