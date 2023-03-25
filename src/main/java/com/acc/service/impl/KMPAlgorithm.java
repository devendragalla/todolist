package com.acc.service.impl;

import java.util.List;
import java.util.*;

import com.acc.domain.Task;

public class KMPAlgorithm {

    // Utility function to compute the LPS array
    private static int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length();) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[i] = j + 1;
                j++;
                i++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // Function to search for a pattern in a text using KMP algorithm
    public static List<Task> searchTaskByTitle(List<Task> tasks, String pattern) {
        List<Task> result = new ArrayList<>();
        int[] lps = computeLPSArray(pattern);
        int i = 0;
        int j = 0;
        while (i < tasks.size()) {
            Task task = tasks.get(i);
            String title = task.getTitle();
            while (j < title.length()) {
                if (title.charAt(j) == pattern.charAt(j)) {
                    j++;
                    if (j == pattern.length()) {
                        result.add(task);
                        j = lps[j - 1];
                    }
                } else {
                    if (j != 0) {
                        j = lps[j - 1];
                    } else {
                        break;
                    }
                }
            }
            i++;
            j = 0;
        }
        return result;
    }

}

