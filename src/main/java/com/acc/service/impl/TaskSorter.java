package com.acc.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acc.domain.Task;

@Service
public class TaskSorter {

	// sort basing on the priority
	public void sortByPriority(List<Task> tasks) {
		quickSort(tasks, 0, tasks.size() - 1);
	}

	private void quickSort(List<Task> tasks, int low, int high) {
		if (low < high) {
			int partitionIndex = partition(tasks, low, high);
			quickSort(tasks, low, partitionIndex - 1);
			quickSort(tasks, partitionIndex + 1, high);
		}
	}

	private int partition(List<Task> tasks, int low, int high) {
		Task pivot = tasks.get(high);
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if ((tasks.get(j).getPriority().getId()) < (pivot.getPriority().getId())) {
				i++;
				Task temp = tasks.get(i);
				tasks.set(i, tasks.get(j));
				tasks.set(j, temp);
			}
		}

		Task temp = tasks.get(i + 1);
		tasks.set(i + 1, tasks.get(high));
		tasks.set(high, temp);

		return i + 1;
	}

	// Sort basing on the updated time
	public static void sortbyUpdatedTime(List<Task> tasks) {
		quicksortbyUpdatedTime(tasks, 0, tasks.size() - 1);
	}

	private static void quicksortbyUpdatedTime(List<Task> tasks, int low, int high) {
		if (low < high) {
			int pivotIndex = partitionbyUpdatedTime(tasks, low, high);
			quicksortbyUpdatedTime(tasks, low, pivotIndex - 1);
			quicksortbyUpdatedTime(tasks, pivotIndex + 1, high);
		}
	}

	private static int partitionbyUpdatedTime(List<Task> tasks, int low, int high) {
		LocalDateTime pivotDate = tasks.get(high).getUpdatedDate();
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (tasks.get(j).getUpdatedDate().compareTo(pivotDate) > 0) {
				i++;
				Task temp = tasks.get(i);
				tasks.set(i, tasks.get(j));
				tasks.set(j, temp);
			}
		}
		Task temp = tasks.get(i + 1);
		tasks.set(i + 1, tasks.get(high));
		tasks.set(high, temp);
		return i + 1;
	}

	// Sort basing on the due date
	public static void sortByDueDate(List<Task> tasks) {
		quicksortByDueDate(tasks, 0, tasks.size() - 1);
	}

	public static void quicksortByDueDate(List<Task> tasks, int low, int high) {
		if (low < high) {
			int pivotIndex = partitionByDueDate(tasks, low, high);
			quicksortByDueDate(tasks, low, pivotIndex - 1);
			quicksortByDueDate(tasks, pivotIndex + 1, high);
		}
	}

	private static int partitionByDueDate(List<Task> tasks, int low, int high) {
		LocalDateTime pivot = tasks.get(high).getDueDate();
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (tasks.get(j).getDueDate().isBefore(pivot)) {
				i++;
				Task temp = tasks.get(i);
				tasks.set(i, tasks.get(j));
				tasks.set(j, temp);
			}
		}
		Task temp = tasks.get(i + 1);
		tasks.set(i + 1, tasks.get(high));
		tasks.set(high, temp);
		return i + 1;
	}

}
