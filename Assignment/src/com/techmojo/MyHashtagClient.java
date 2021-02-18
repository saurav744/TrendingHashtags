package com.techmojo;

import java.util.List;
import java.util.Scanner;
/* User interface Class for i/o */
public class MyHashtagClient {
	
	private TrendingHashtagFinder trendingHashtagFinder;
	private Scanner scanner;
	
	public MyHashtagClient () {
		this.trendingHashtagFinder = new TrendingHashtagFinder();
		this.scanner = new Scanner(System.in);
	}
	
	public void hashtagConsole() {
		int option;
		do {
			options();
			option = scanInt();
			switch(option) {
			case 1:
				System.out.println(" Enter your string with hashtags:");
				String tweet = scanline();
				if(tweet != null && !tweet.isEmpty()) {
					String words[] = tweet.split(" ");
					for(String word: words) {
						if(word.startsWith("#")) {
							if(word.length() > 1) // save only non-empty hashtags
							trendingHashtagFinder.addHashtag(word.substring(1));
						}
					}
				}
				break;	
			case 2:
				System.out.println(" Here is a list of all hashtags:");
				trendingHashtagFinder.printAll();
				break;
			case 3:
				printTrendingAndExit();
                break;
			default:
				System.out.println("Enter correct menu options");
			}	
		} while(option != 3);
		
	}
	
	private void printTrendingAndExit() {
		List<String> list = trendingHashtagFinder.getTrendingHashtags();
		System.out.println(list);
    	System.out.printf("%n%nExiting..");
		
	}
	public void options() {
		System.out.printf("%n%n ************ Welcome to Trending Hashtag App  *********** %n%n");
        System.out.println("Enter options from menu:");
        System.out.println("1. Add a tweet ");
        System.out.println("2. View all hashtags ");
        System.out.println("3. View top 10 trending hashtags and exit ");
        System.out.println("");
		
	}
    private String scanline() {
        String input = "";
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        return input;
    }
    private int scanInt() {
    	int val = scanner.nextInt();
		scanner.nextLine();
		return val;
    }

}
