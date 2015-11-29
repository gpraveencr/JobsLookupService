package com.jobs.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jobs.dao.JobRepository;
import com.jobs.model.Job;

import coom.jobs.response.RestResponse;

@Controller
public class JobsController {

	private static Logger logger = LoggerFactory.getLogger(JobsController.class);
	
	@Autowired
	private JobRepository jobRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Default Jobs REST Service Home Page.");
		
		Date date = new Date();
		DateFormat dateFormate = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formatedDate = dateFormate.format(date);
		model.addAttribute("serverTime", formatedDate);
		
		return "status";
	}
	
	@RequestMapping(value="/jobs", method=RequestMethod.GET)
	@ResponseBody
	public List<Job> getAllJobs() {
		return jobRepository.getAllJobs();
	}

	@RequestMapping(value="/jobs/{zipCode}", method=RequestMethod.GET)
	@ResponseBody
	public List<Job> getJobsByZipCode(@PathVariable("zipCode") String zipCode) {
		return jobRepository.getJobsByZipCode(zipCode);
	}

	@RequestMapping(value="/jobs/{company}", method=RequestMethod.GET)
	@ResponseBody
	public List<Job> getJobsByCompany(@PathVariable("company") String company) {
		return jobRepository.getJobsByCompany(company);
	}

	
	@RequestMapping(value="/job", method=RequestMethod.POST)
	@ResponseBody
	public RestResponse addJob(@ModelAttribute("job") Job job) {
		
		RestResponse response = new RestResponse(true, "Success");
		try {
			jobRepository.addJob(job);
		} catch(Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
}
