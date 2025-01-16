package com.project.todoapp.service;


import com.project.todoapp.entity.Task;
import com.project.todoapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);

    }


}
