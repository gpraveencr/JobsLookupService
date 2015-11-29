package com.jobs.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Job {

	@Id
	private String id;
	private String companyName;
	private String title;
	private String city;
	private String zipCode;
	private List<String> description;
	private List<String> responsibilities;
	private List<String> techStack;
	private List<String> perks;
	private String responseMessage;
	
	public Job() { }
	
	public Job(String id, String companyName, String title, String city, String zipCode) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.title = title;
		this.city = city;
		this.zipCode = zipCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	public List<String> getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(List<String> responsibilities) {
		this.responsibilities = responsibilities;
	}

	public List<String> getTechStack() {
		return techStack;
	}

	public void setTechStack(List<String> techStack) {
		this.techStack = techStack;
	}

	public List<String> getPerks() {
		return perks;
	}

	public void setPerks(List<String> perks) {
		this.perks = perks;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", companyName=" + companyName + ", title="
				+ title + ", city=" + city + ", zipCode=" + zipCode + "]";
	} 
	
	
}
