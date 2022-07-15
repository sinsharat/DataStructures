package com.sharat.datastructures.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class ActivitySelectionProblem {
	
	// given a range of activity find the max activity 
	// combinations which can be performed at a given time without overlap
	public int findMaxActivity(List<Activity> activityTimeRangeList) {
		if (CollectionUtils.isEmpty(activityTimeRangeList)) {
			return 0;
		}
		
		Collections.sort(activityTimeRangeList);
		int totalActivity = activityTimeRangeList.size();
		int activityCount = 1;
		Activity curActivity = activityTimeRangeList.get(0);
		Activity nextActivity;
		for (int i = 1; i < totalActivity; i++) {
			nextActivity = activityTimeRangeList.get(i);
			if (curActivity.end <= nextActivity.start) {
				activityCount++;
				curActivity = nextActivity;
			}
		}
		
		return activityCount;
	}
	
	private Activity createActivity(int start, int end) {
		return new Activity(start, end);
	}
	
	public static void main(String[] args) {
		ActivitySelectionProblem asp = new ActivitySelectionProblem();
		List<Activity> activityTimeRangeList = new ArrayList<>();
		activityTimeRangeList.add(asp.createActivity(2, 3));
		activityTimeRangeList.add(asp.createActivity(1, 4));
		activityTimeRangeList.add(asp.createActivity(5, 8));
		activityTimeRangeList.add(asp.createActivity(6, 10));
		int maxActivity = asp.findMaxActivity(activityTimeRangeList);
		System.out.println("Max Activity for activityTimeRangeList: " + activityTimeRangeList + " is : " + maxActivity);
		activityTimeRangeList = new ArrayList<>();
		activityTimeRangeList.add(asp.createActivity(1, 3));
		activityTimeRangeList.add(asp.createActivity(2, 4));
		activityTimeRangeList.add(asp.createActivity(3, 8));
		activityTimeRangeList.add(asp.createActivity(10, 11));
		maxActivity = asp.findMaxActivity(activityTimeRangeList);
		System.out.println("Max Activity for activityTimeRangeList: " + activityTimeRangeList + " is : " + maxActivity);
	}
	
	
	class Activity implements Comparable<Activity>{
		int start, end;

		public Activity(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Activity o) {
			return this.end - o.end;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("{start=");
			builder.append(start);
			builder.append(", end=");
			builder.append(end);
			builder.append("}");
			return builder.toString();
		}
	}

}
