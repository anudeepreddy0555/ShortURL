package com.webscale.shorturl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webscale.shorturl.model.MapURL;
import com.webscale.shorturl.repository.MapURLDAO;
import com.webscale.shorturl.repository.MapURLRepository;
import com.webscale.shorturl.service.ShortURLService;
import com.webscale.shorturl.utility.Utils;

@Transactional
@Service(value = "shortURLService")
public class ShortURLServiceImpl implements ShortURLService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MapURLRepository mapURLRepository;
	
	@Autowired
	MapURLDAO mapURLDAO;
	
	@Override
	public String createTinyURL(String originalURL) {
		logger.info("originalURL "+originalURL);
		MapURL mapURL =  new MapURL();
		String shortURL = null;
		String hashKey = Utils.getHashvalue(originalURL);
		
		try {
			long count = mapURLDAO.findCountByHashKey(hashKey);
			if (count > 0) {
				shortURL = mapURLDAO.findShortURLIfExistForOriginalURL(hashKey, originalURL);
				if (shortURL != null) {
					return shortURL;
				}
			}
			String counter = Utils.getBase62Val(count);
			shortURL = hashKey + counter;
			mapURL.setHashKey(hashKey);
			mapURL.setOriginalURL(originalURL);
			mapURL.setShortURL(shortURL);
			mapURL = mapURLRepository.insert(mapURL);
		} catch (DuplicateKeyException ex) {
			logger.error(ex.getMessage());
			logger.info("shortURL " + shortURL + " already exist ");
			return shortURL; 
		}
		
		
		logger.info("shortURL length "+mapURL.getShortURL().length()+" for longurl length "+mapURL.getOriginalURL().length());
		return mapURL.getShortURL();
	}

	@Override
	public String getOriginalURL(String shortURL) {
		return mapURLDAO.getOriginalURL(shortURL);
	}
	
	

}
