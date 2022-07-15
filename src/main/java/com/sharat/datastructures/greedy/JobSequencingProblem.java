package com.sharat.datastructures.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class JobSequencingProblem {

	public int maximizeProfitFromJobs(List<Job> jobList, int maxTimeUnit) {
		if (CollectionUtils.isEmpty(jobList)) {
			return 0;
		}
		
		Collections.sort(jobList);
		
		int[] jobTimeSlot = new int[maxTimeUnit];
		int maxProfit = 0;
		int curTimeUnit = 0;
		int jobTimeUnit;
		int jobProfit;
		for (Job job: jobList) {
			if (curTimeUnit >= maxTimeUnit) {
				break;
			}
			jobTimeUnit = job.timeUnit - 1;
			jobProfit = job.profit;
			if (jobTimeSlot[jobTimeUnit] == 0) {
				jobTimeSlot[jobTimeUnit] = jobProfit;
				maxProfit += jobProfit;
				curTimeUnit++;
				continue;
			}
			
			while (jobTimeUnit > 0) {
				if (jobTimeSlot[--jobTimeUnit] == 0) {
					jobTimeSlot[jobTimeUnit] = jobProfit;
					maxProfit += jobProfit;
					curTimeUnit++;
					break;
				}
			}
		}
		
		return maxProfit;
	}
	
	public Job createJob(int timeUnit, int profit) {
		return new Job(timeUnit, profit);
	}
	
	public static void main(String []args) {
		JobSequencingProblem jsp = new JobSequencingProblem();
		List<Job> jobList = new ArrayList<>();
		jobList.add(jsp.createJob(4, 70));
		jobList.add(jsp.createJob(1, 80));
		jobList.add(jsp.createJob(1, 30));
		jobList.add(jsp.createJob(1, 100));
		int maxProfit = jsp.maximizeProfitFromJobs(jobList, 4);
		System.out.println("Max profit for jobList: "+ jobList + " is: " + maxProfit);
		
		jobList = new ArrayList<>();
		jobList.add(jsp.createJob(2, 50));
		jobList.add(jsp.createJob(2, 60));
		jobList.add(jsp.createJob(3, 20));
		jobList.add(jsp.createJob(3, 30));
		maxProfit = jsp.maximizeProfitFromJobs(jobList, 3);
		System.out.println("Max profit for jobList: "+ jobList + " is: " + maxProfit);
		
		jobList = new ArrayList<>();
		jobList.add(jsp.createJob(2, 100));
		jobList.add(jsp.createJob(1, 50));
		jobList.add(jsp.createJob(2, 10));
		jobList.add(jsp.createJob(1, 20));
		jobList.add(jsp.createJob(3, 30));
		maxProfit = jsp.maximizeProfitFromJobs(jobList, 3);
		System.out.println("Max profit for jobList: "+ jobList + " is: " + maxProfit);
		
		jobList = new ArrayList<>();
		jobList.add(jsp.createJob(4, 50));
		jobList.add(jsp.createJob(1, 5));
		jobList.add(jsp.createJob(1, 20));
		jobList.add(jsp.createJob(5, 10));
		jobList.add(jsp.createJob(5, 80));
		maxProfit = jsp.maximizeProfitFromJobs(jobList, 5);
		System.out.println("Max profit for jobList: "+ jobList + " is: " + maxProfit);
	}
	
	class Job implements Comparable<Job>{
		int timeUnit, profit;
		
		public Job(int timeUnit, int profit) {
			this.timeUnit = timeUnit;
			this.profit = profit;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("{timeUnit=");
			builder.append(timeUnit);
			builder.append(", profit=");
			builder.append(profit);
			builder.append("}");
			return builder.toString();
		}

		@Override
		public int compareTo(Job o) {
			return o.profit - this.profit;
		}
		
	}
}
