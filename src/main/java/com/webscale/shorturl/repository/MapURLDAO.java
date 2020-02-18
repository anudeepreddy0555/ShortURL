package com.webscale.shorturl.repository;

public interface MapURLDAO {
	
	String getOriginalURL(String shortURL);
	
	long findCountByHashKey(String hashKey);
	
	String findShortURLIfExistForOriginalURL(String hashKey,String originalURL);
}
