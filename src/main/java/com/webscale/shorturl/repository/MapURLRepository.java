package com.webscale.shorturl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webscale.shorturl.model.MapURL;

public interface MapURLRepository extends MongoRepository<MapURL, Long> {

	
	
	
}
