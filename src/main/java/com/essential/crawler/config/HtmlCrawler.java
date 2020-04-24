package com.essential.crawler.config;

import java.io.File;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

@Service
public class HtmlCrawler extends WebCrawler{
	
	private final static Pattern EXCLUSIONS
    = Pattern.compile(".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf))$");
	
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String urlString = url.getURL().toLowerCase();
	    return !EXCLUSIONS.matcher(urlString).matches() 
	      && urlString.startsWith("http://www.onefivenine.com/india/Listing/Town/medicalshops/Shahdol/Shahdol");
	}
	
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		 
	    if (page.getParseData() instanceof HtmlParseData) {
	        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
	        String title = htmlParseData.getTitle();
	        String text = htmlParseData.getText();
	        String html = htmlParseData.getHtml();
	        Set<WebURL> links = htmlParseData.getOutgoingUrls();
	        
	        
	        System.err.println(text);
	 
	        // do something with the collected data
	    }
	}
	
	
	public void startCrawling() {
	File crawlStorage = new File("src/test/resources/crawler4j");
	CrawlConfig config = new CrawlConfig();
	config.setCrawlStorageFolder(crawlStorage.getAbsolutePath());
	 
	int numCrawlers = 12;
	 
	PageFetcher pageFetcher = new PageFetcher(config);
	RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
	RobotstxtServer robotstxtServer= new RobotstxtServer(robotstxtConfig, pageFetcher);
	CrawlController controller;
	try {
		controller = new CrawlController(config, pageFetcher, robotstxtServer);
		controller.addSeed("http://www.onefivenine.com/india/Listing/Town/medicalshops/Shahdol/Shahdol");
		 
		CrawlController.WebCrawlerFactory<HtmlCrawler> factory = HtmlCrawler::new;
		 
		controller.start(factory, numCrawlers);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
}
}
