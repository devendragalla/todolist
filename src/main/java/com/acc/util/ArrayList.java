package com.acc.util;

import java.util.Arrays;

import com.acc.domain.Task;

public class ArrayList<T> {

    private int size;
    private Object[] elements;

    public ArrayList() {
        this.size = 0;
        this.elements = new Object[10];
    }

    public void add(T element) {
        if (this.size == this.elements.length) {
            this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
        }
        this.elements[this.size] = element;
        this.size++;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) this.elements[index];
    }

    public int size() {
        return this.size;
    }
    
    public static String getListAsString(ArrayList<Task> inProgressTasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inProgressTasks.size(); i++) {
            sb.append("Task ").append(i).append(": ").append(" \n  { \n").append(getString(inProgressTasks.get(i))).append("\n");
        }
        return sb.toString();
    }
    
    
    public static String getString(Task task) {
        StringBuilder sb = new StringBuilder();
            sb.append("    Title: ").append(task.getTitle()).append("\n");
            sb.append("    Due Date: ").append(task.getDueDate()).append("\n");
            sb.append("    Priority: ").append(task.getPriority().getPriorityName()).append("\n");
            sb.append("    Status: ").append(task.getStatus().getAction()).append("\n");
            sb.append("  } ").append("\n");
        return sb.toString();
    }
}
