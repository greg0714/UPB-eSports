package com.upbesports.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upbesports.dao.TeamSchedulesDAO;
import com.upbesports.model.db.TeamSchedules;
import com.upbesports.model.form.ScheduleForm;

@RestController
public class SchedulingController 
{
	@Autowired TeamSchedulesDAO teamSchedulesDAO;
	
	@RequestMapping(value = "/scheduling/getSchedules", method = RequestMethod.GET)
	public List<TeamSchedules> getGames(HttpServletRequest request, HttpServletResponse response)
	{
    	return teamSchedulesDAO.findAllWithTeamName();
	}
	
	@Transactional
	@RequestMapping(value = "/scheduling/addOrUpdateSchedule", method = RequestMethod.POST)
	public void addOrUpdateSchedule(HttpServletRequest request, HttpServletResponse response, ScheduleForm form) throws IOException
	{
		if(form.getScheduleId() == null) 
		{
			TeamSchedules schedule = new TeamSchedules();
			setScheduleValuesFromForm(schedule, form);
			teamSchedulesDAO.create(schedule);
		}
		else
		{
			TeamSchedules schedule = teamSchedulesDAO.findOne(form.getScheduleId());
			setScheduleValuesFromForm(schedule, form);
			teamSchedulesDAO.update(schedule);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@RequestMapping(value = "/scheduling/removeSchedule", method = RequestMethod.POST)
	public void removeSchedule(HttpServletRequest request, HttpServletResponse response, ScheduleForm form) throws IOException
	{
		teamSchedulesDAO.deleteById(form.getScheduleId());
	}

	private void setScheduleValuesFromForm(TeamSchedules schedule, ScheduleForm form)
	{
		Calendar eventTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm", Locale.getDefault());
		try 
		{
			eventTime.setTime(sdf.parse(form.getDate()));
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		schedule.setComments(form.getComments());
		schedule.setEventTime(eventTime);
		schedule.setEventType(form.getEventType());
		schedule.setLocation(form.getLocation());
		schedule.setTeamId(form.getTeamId());
		schedule.setUpdated(Calendar.getInstance());
		schedule.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
