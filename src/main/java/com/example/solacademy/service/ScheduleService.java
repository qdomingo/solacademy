package com.example.solacademy.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.solacademy.mapper.ScheduleMapper;
import com.example.solacademy.model.Schedule;

@Service
@Transactional
public class ScheduleService {
	
	@Autowired
    private ScheduleMapper scheduleMapper;

    public List<Schedule> getAllSchedules() {
        return scheduleMapper.getAllSchedules();
    }
    
    public List<Schedule> getScheduleById(Long id) {
        return scheduleMapper.getScheduleById(id);
    }
    
    public List<Schedule> getScheduleByStudentId(Long id) {
        return scheduleMapper.getScheduleByStudentId(id);
    }
    
    public int createSchedule(Schedule schedule) {
    	
    	int resultado = 0;
    	Long maxId = scheduleMapper.getMaxScheduleID();

    	if(schedule.getWeekly() == 1) {

    		// Creamos serie_id
    		String serie_id = schedule.getStudent_id() + "_" + schedule.getInicio().substring(10, 16);
    		schedule.setSerie_id(serie_id);
    		
    		// primera persistencia
    		schedule.setId(maxId);
    		resultado = scheduleMapper.createSchedule(schedule);
    		
    		// Convertir la cadena a ZonedDateTime
            ZonedDateTime dateTimeInicio = ZonedDateTime.parse(schedule.getInicio());
            ZonedDateTime dateTimeFin = ZonedDateTime.parse(schedule.getFin());
    		
    		// añadir logica para repetir semanalmente durante 1 año
        	for(int i=1;i<53;i++) {
        		
                // aumentamos maxId
                maxId++;
        		                
                // Sumar 7 días
                ZonedDateTime newDateTimeInicio = dateTimeInicio.plus(i*7, ChronoUnit.DAYS);
                ZonedDateTime newDateTimeFin = dateTimeFin.plus(i*7, ChronoUnit.DAYS);
                
                // Ajustar la hora para el horario de verano si es necesario
                if (ZoneId.of("Europe/Madrid").getRules().isDaylightSavings(newDateTimeInicio.toInstant())) {
                	newDateTimeInicio = newDateTimeInicio.minusHours(1);
                	newDateTimeFin = newDateTimeFin.minusHours(1);
                }

                // Convertir la nueva fecha y hora a cadena con formato ISO
                String newIsoDateTimeInicioString = newDateTimeInicio.format(DateTimeFormatter.ISO_INSTANT);
                String newIsoDateTimeFinString = newDateTimeFin.format(DateTimeFormatter.ISO_INSTANT);
                
                // seteamos los campos
                schedule.setInicio(newIsoDateTimeInicioString);
                schedule.setFin(newIsoDateTimeFinString);
                schedule.setId(maxId);
                
                //persitimos
                resultado = scheduleMapper.createSchedule(schedule);

        	}
    		schedule.setId(maxId);
    	} else {
    		schedule.setId(maxId);
    		resultado = scheduleMapper.createSchedule(schedule);
    	}
    	
        return resultado;
    }
    
    public int updateSchedule(Schedule schedule) {
        return scheduleMapper.updateSchedule(schedule);
    }
    
    public int deleteSchedule(Long id) {
        return scheduleMapper.deleteSchedule(id);
    }
    
    public int deleteScheduleSerie(Schedule schedule) {
    	Long id = schedule.getStudent_id();
    	String serie_id = schedule.getSerie_id();
        return scheduleMapper.deleteScheduleSerie(id, serie_id);
    }

}
