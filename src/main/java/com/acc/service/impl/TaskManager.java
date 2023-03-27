package com.acc.service.impl;

import com.acc.domain.Priority;
import com.acc.domain.Task;
import java.util.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
	private SplayTree<Integer, Task> taskTree = new SplayTree<>();
//	private List<Task> taskList = new ArrayList<>();
//
//	public void addTask(Task task) {
//		taskTree.insert(task.getPriority().getId(), task);
//		taskList = taskTree.getValuesInOrder();
//	}
//
//	public void removeTask(Task task) {
//		taskTree.remove(task.getPriority().getId());
//		taskList = taskTree.getValuesInOrder();
//	}
//
//	public List<Task> getAllTasks() {
//		return taskList;
//	}
//
//	public List<Task> getTasksByPriority(Priority priority) {
//		List<Task> tasks = new ArrayList<>();
//		for (Task task : taskList) {
//			if (task.getPriority().equals(priority)) {
//				tasks.add(task);
//			}
//		}
//		return tasks;
//	}
//
//	public void sortTasksByPriority() {
//		List<Task> sortedList = quickSort(taskList);
//		taskList = sortedList;
//		taskTree = new SplayTree<>();
//		for (Task task : taskList) {
//			taskTree.insert(task.getPriority().getId(), task);
//		}
//	}
//
//	private List<Task> quickSort(List<Task> list) {
//		if (list.size() <= 1) {
//			return list;
//		}
//		Task pivot = list.get(0);
//		List<Task> less = new ArrayList<>();
//		List<Task> greater = new ArrayList<>();
//		for (int i = 1; i < list.size(); i++) {
//			Task current = list.get(i);
//			if (current.getPriority().getId() < pivot.getPriority().getId()) {
//				less.add(current);
//			} else {
//				greater.add(current);
//			}
//		}
//		List<Task> sorted = quickSort(less);
//		sorted.add(pivot);
//		sorted.addAll(quickSort(greater));
//		return sorted;
//	}
}