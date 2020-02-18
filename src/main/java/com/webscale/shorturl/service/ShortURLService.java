package com.webscale.shorturl.service;

import org.springframework.web.bind.annotation.RequestBody;

public interface ShortURLService {
	
	String createTinyURL(String originalURL);
	
	String getOriginalURL(String tinyURL);
	

}
