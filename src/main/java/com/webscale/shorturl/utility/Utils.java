package com.webscale.shorturl.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class Utils {
	
	public static String  getHashvalue(String text) {
		String result = "";
		long value  = 0;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte[] bytes = md.digest();
			long pow2 = 1;
			int count = 0;
			for(int i=0;i<=bytes.length-1;i++) {
				value = value + pow2*bytes[i];
				pow2 = pow2*256;
				count++;
				if(count==6) {
					break;
				}
			}
			result = getBase62Val(value);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static String getBase62Val(long value) {
		if(value==0) {
			return "a";
		}
		String base62mapping = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String result ="";
		while(value!=0) {
			result = result+base62mapping.charAt((int)Math.abs(value%62));
			value = value/62;
		}
		return result;
	}
	

}
