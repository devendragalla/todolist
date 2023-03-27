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

	// Function to search for a pattern in a text using KMP algorithm
	public static List<Task> searchTaskByTitle(List<Task> tasks, String pattern) {
		List<Task> result = new ArrayList<>();
		for (Task task : tasks) {
			boolean isFound = kmpSearch(task.getTitle(), pattern);
			if (isFound) {
				result.add(task);
			}
		}
		return result;
	}

	public static boolean kmpSearch(String text, String pattern) {
		int[] lps = computeLPSArray(pattern);
		int j = 0;
		for (int i = 0; i < text.length(); i++) {
			while (j > 0 && pattern.charAt(j) != text.charAt(i)) {
				j = lps[j - 1];
			}
			if (pattern.charAt(j) == text.charAt(i)) {
				j++;
			}
			if (j == pattern.length()) {
				return true;
			}
		}
		return false;
	}

	private static int[] computeLPSArray(String pattern) {
		int[] lps = new int[pattern.length()];
		int j = 0;
		for (int i = 1; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
				j = lps[j - 1];
			}
			if (pattern.charAt(j) == pattern.charAt(i)) {
				j++;
			}
			lps[i] = j;
		}
		return lps;
	}


}
