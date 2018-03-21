package com.wakeup;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;


public class WakeUpTask {
	
	final static Logger LOGGER = Logger.getLogger(WakeUpTask.class);
	
	@Value("#{'${sites}'.split(',')}") 
	private List<String> sites;
	
	
	public void launch(){
		LOGGER.info("TASK STARTED");
		for (String site : sites) {
			try {
				URL url = new URL(site);
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				connection.connect();
				int httpStatusCode = connection.getResponseCode();
				LOGGER.info("SITE: "+site+" HTTP STATUS: "+httpStatusCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
