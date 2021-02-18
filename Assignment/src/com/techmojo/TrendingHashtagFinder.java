package com.techmojo;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TrendingHashtagFinder {
	
	private Map<String, Integer> hashtagCount; // stores hashtag strings and their counts
	private PriorityQueue<String> trendingHeap; // keeps track of most trending strings
	private int requiredCount; // number of trending strings required
	private static final int DEFAULT_COUNT = 10;

	public TrendingHashtagFinder() {
		this(DEFAULT_COUNT);
	}

	public TrendingHashtagFinder(int requiredCount) {
		this.requiredCount = requiredCount;
		this.hashtagCount = new HashMap<String, Integer>();
		
		/* Comparator to compare strings in heap as per their count stored in map */
		Comparator<String> hashtagComparator = (s1, s2) -> hashtagCount.get(s1).equals(hashtagCount.get(s2))
				? s2.compareTo(s1)
				: hashtagCount.get(s1) - hashtagCount.get(s2);
		trendingHeap = new PriorityQueue<String>(hashtagComparator);
	}
	
	private boolean isPresentInHeap(String s) {
		/*
		Alternatively can create an HashSet<String> to keep track of strings already
		present in the heap for efficiency, but we can skip for small heap size.
		*/
		return trendingHeap.contains(s);
	}
	
	public void addHashtag(String s, int count) {
		hashtagCount.put(s, hashtagCount.getOrDefault(s, 0) + count);
		
		if(isPresentInHeap(s)) {
			trendingHeap.remove(s);
			trendingHeap.offer(s);
		} else if(trendingHeap.size() < requiredCount) {
			trendingHeap.offer(s);	
		} else if(hashtagCount.get(s) > hashtagCount.get(trendingHeap.peek())) {
			trendingHeap.offer(s);
			trendingHeap.poll();	
		}
	}
	
	public void addHashtag(String s) {
		addHashtag(s,1);
	}
	/* prints all hashtag strings present in map with count */
	public void printAll() {
		if(hashtagCount.isEmpty()) {
			System.out.println("There are no hashtags currently!!");
		} else {
			for(Map.Entry<String, Integer> element: hashtagCount.entrySet()) {
			System.out.println("#"+element.getKey()+" count: "+element.getValue());
			}
		}
	}
	/* returns list of trending hashtag strings */
	public List<String> getTrendingHashtags() {
		List<String> trendingList = new LinkedList<>();
        while (!trendingHeap.isEmpty()) {
        	trendingList.add(trendingHeap.poll());
        }
        Collections.reverse(trendingList);
        return trendingList;		
	}
	
}
