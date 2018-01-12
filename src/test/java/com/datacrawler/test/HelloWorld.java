package com.datacrawler.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    Connection connection = Jsoup.connect("http://www.bluetata.com").requestBody("");
	}

}
