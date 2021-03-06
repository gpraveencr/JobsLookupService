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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jobs.dao.JobRepository;
import com.jobs.model.Job;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import coom.jobs.response.RestResponse;

@Controller 
@Api(value = "/rest", description = "Jobs Lookup Service")
public class JobsController {

	private static Logger logger = LoggerFactory.getLogger(JobsController.class);
	
	@Autowired
	private JobRepository jobRepository;
	 
	@ApiOperation(value = "Get All Jobs",
            notes = "Anything Else?")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
	@RequestMapping(value="/jobs", method=RequestMethod.GET)
	@ResponseBody
	public List<Job> getAllJobs() {
		logger.debug("Request is in getAllJobs()");
		return jobRepository.getAllJobs();
	}

	@RequestMapping(value="/jobs/zip{zipCode}", method=RequestMethod.GET)
	@ResponseBody
	public List<Job> getJobsByZipCode(@RequestParam String zipCode) {
		logger.debug("Request is in getJobsByZipCode(): zipCode: " +  zipCode);
		return jobRepository.getJobsByZipCode(zipCode);
	} 

	@RequestMapping(value="/jobs/titles", method=RequestMethod.GET)
	@ResponseBody
	public List<String> getJobsTitles() {
		logger.debug("Request is in getJobsTitles()");
		return jobRepository.getJobsTitles();
	}
	
	@RequestMapping(value="/jobs/company{company}", method=RequestMethod.GET)
	@ResponseBody
	public List<Job> getJobsByCompany(@RequestParam String company) {
		logger.debug("Request is in getJobsByCompany(): company: " + company);
		return jobRepository.getJobsByCompany(company);
	} 

	@RequestMapping(value="/jobs/title{title}", method=RequestMethod.GET)
	@ResponseBody
	public List<Job> getJobsByTitle(@RequestParam String title) {
		logger.debug("Request is in getJobsByTitle(): title: " + title);
		return jobRepository.getJobsByTitle(title);
	} 
	
	@RequestMapping(value="/jobs", method=RequestMethod.POST)
	@ResponseBody
	public RestResponse addJob(@RequestBody Job job) {
		logger.debug("Request is in addJob(): job: " + job.toString());
		
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
