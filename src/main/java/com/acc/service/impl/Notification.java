package com.acc.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.acc.domain.Task;

public class Notification {
    
    public List<Task> getTasksDueToday(List<Task> tasks) {
        List<Task> dueTodayTasks = new ArrayList<>();
        LocalDate today = LocalDate.now();
        Queue<Task> queue = new LinkedList<>(tasks);
        
        while (!queue.isEmpty()) {
            Task task = queue.poll();
            LocalDateTime dueDate = task.getDueDate();
            if (dueDate != null && dueDate.toLocalDate().equals(today)) {
                dueTodayTasks.add(task);
            }
        }
        
        return dueTodayTasks;
    }
    
}
