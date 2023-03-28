package com.acc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acc.domain.Task;
import com.acc.service.SearchingService;

@Service
public class SearchingServiceImpl implements SearchingService {

	@Override
	public List<Task> search(List<Task> tasks, String searchString) {
		return searchTaskByTitle(tasks, searchString);

	}

	// Function to search for a pattern in a text using BM algorithm
	public static List<Task> searchTaskByTitle(List<Task> tasks, String pattern) {
		List<Task> result = new ArrayList<>();
		for (Task task : tasks) {
			boolean isFound = search(task.getTitle(), pattern);
			if (isFound) {
				result.add(task);
			}
		}
		return result;
	}

	 public static boolean search(String text, String pattern) {
	        int n = text.length();
	        int m = pattern.length();
	        int[] last = new int[256];
	        for (int i = 0; i < 256; i++) {
	            last[i] = -1;
	        }
	        for (int i = 0; i < m; i++) {
	            last[pattern.charAt(i)] = i;
	        }
	        int i = m - 1;
	        int j = m - 1;
	        while (i < n) {
	            if (text.charAt(i) == pattern.charAt(j)) {
	                if (j == 0) {
	                	return true;
	                }
	                i--;
	                j--;
	            } else {
	                i += m - Math.min(j, 1 + last[text.charAt(i)]);
	                j = m - 1;
	            }
	        }
	        return false;
	    }
}
