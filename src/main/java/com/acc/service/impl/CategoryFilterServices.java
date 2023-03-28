package com.acc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acc.domain.Task;
import com.acc.exception.TasksNotFoundException;

@Service
public class CategoryFilterServices {

    public List<Task> filterByCategory(List<Task> tasks, String categoryKey) throws TasksNotFoundException {
        // Create a CuckooHashTable to store tasks by category
        CuckooHashing<String, Task> tasksByCategory = new CuckooHashing<>(2);

        for (Task task : tasks) {
            String category = task.getCategoryType() != null ? task.getCategoryType() : null;
            if (category != null) {
                tasksByCategory.insert(category.toLowerCase(), task);
            }
        }

        List<Task> filteredTasks = tasksByCategory.search(categoryKey.toLowerCase());

        if (filteredTasks != null && !filteredTasks.isEmpty()) {
            return filteredTasks;
        } else {
            throw new TasksNotFoundException("No Tasks Found matching with category key " + categoryKey);
        }
    }
}
