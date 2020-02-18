package com.webscale.shorturl.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.webscale.shorturl.model.MapURL;
import com.webscale.shorturl.repository.MapURLDAO;

@Repository
public class MapURLDAOImpl implements MapURLDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public String getOriginalURL(String shortURL) {
		String result = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("shortURL").is(shortURL));
		MapURL mapURL = mongoTemplate.findOne(query, MapURL.class);
		if(mapURL!=null) {
			result = mapURL.getOriginalURL();
		}
		return result;
	}

	@Override
	public long findCountByHashKey(String hashKey) {
		Query query = new Query();
		query.addCriteria(Criteria.where("hashKey").is(hashKey));
		Long count = mongoTemplate.count(query, MapURL.class);
		return count;
	}
	
	@Override
	public String findShortURLIfExistForOriginalURL(String hashKey,String originalURL) {
		String result = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("hashKey").is(hashKey).andOperator(Criteria.where("originalURL").is(originalURL)));
		List<MapURL> rows = mongoTemplate.find(query, MapURL.class);
		if(rows.size()==1) {
			result = rows.get(0).getShortURL();
		}
		return result;
	}
	
	

}
