package com.jobs.dao;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.jobs.model.Job;
import com.mongodb.DBObject;

@Repository
public class JobRepository {

	public static final String COLLECTION_NAME = "Job";
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void addJob(Job job) {
		if(!mongoTemplate.collectionExists(Job.class)) {
			mongoTemplate.createCollection(Job.class);
		}
		mongoTemplate.insert(job, COLLECTION_NAME);
	}
	
	public List<Job> getJobsByZipCode(String zipCode) {
		return mongoTemplate.find(Query.query(Criteria.where("zipCode").in(zipCode)), Job.class, COLLECTION_NAME);
	} 

	public List<String> getJobsTitles() {  
		
		@SuppressWarnings("unchecked")
		List<String> titles = mongoTemplate.getCollection(COLLECTION_NAME).distinct("title");
		return titles; 
	}
	
	public List<Job> getJobsByCompany(String company) {
		return mongoTemplate.find(Query.query(Criteria.where("companyName").regex(Pattern.compile(company, Pattern.CASE_INSENSITIVE))), Job.class, COLLECTION_NAME);
	}
	
	public List<Job> getJobsByTitle(String title) {
		return mongoTemplate.find(Query.query(Criteria.where("title").regex(Pattern.compile(title, Pattern.CASE_INSENSITIVE))), Job.class, COLLECTION_NAME);
	}
	
	public List<Job> getAllJobs() {
		return mongoTemplate.findAll(Job.class, COLLECTION_NAME);
	}
	
	public Job deleteById(String id) {
		Job job = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Job.class);
		mongoTemplate.remove(job);
		job.setResponseMessage("Successfully deleted");
		
		return job;
	}
}
