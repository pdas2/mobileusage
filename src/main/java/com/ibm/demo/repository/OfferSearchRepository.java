package com.ibm.demo.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ibm.demo.model.offerService;


@Repository
public class OfferSearchRepository {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Collection<offerService> searchOffers(String text) {
		return mongoTemplate.find(Query.query(new Criteria()
						.orOperator(Criteria.where("accountId").regex(text, "i"), 
									Criteria.where("mobNo").regex(text, "i"), 
									Criteria.where("IMSI").regex(text, "i"))
						
						), offerService.class);
	}

}
