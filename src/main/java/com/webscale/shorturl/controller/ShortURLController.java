package com.webscale.shorturl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.webscale.shorturl.service.ShortURLService;

@RestController
@RequestMapping(value = "/")
public class ShortURLController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ShortURLService shortURLservice;
	
	@Value("${application.url}")
	private String applicationURl;
	
	@Value("${server.port}")
	private String port;
	
	@RequestMapping(value="/createShortURL",method=RequestMethod.POST) 
	public String createShortURL(@RequestBody String originalURL) {
		//validate original url 
		logger.info("originalURL "+originalURL);
		return applicationURl+":"+port+"/"+shortURLservice.createTinyURL(originalURL);
	}
	
	
	@RequestMapping(value="/{shortURL}",method=RequestMethod.GET) 
	public ModelAndView  redirectOriginalURL(@PathVariable String shortURL) {
		logger.info("tinyURL "+shortURL);
		String originalURL = shortURLservice.getOriginalURL(shortURL);
		if(originalURL==null) {
			originalURL = "http://localhost:8102/statusNotFound";
			return new ModelAndView("redirect:" + originalURL);
		}
		return new ModelAndView("redirect:" + originalURL);
	}
	
	
	@RequestMapping(value="/statusNotFound",method=RequestMethod.GET) 
	public String statusNotFound() {
		return "404 Status Not found";
	}
	
	
	 
	

}
