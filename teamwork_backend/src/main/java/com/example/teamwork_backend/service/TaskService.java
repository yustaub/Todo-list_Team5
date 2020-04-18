package com.example.teamwork_backend.service;

import java.util.ArrayList;
import java.util.List;

import com.example.teamwork_backend.model.Task;
import com.example.teamwork_backend.store.TaskStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TaskService{
    @Autowired TaskStore store;
    public Task saveTask(Task task) {
        List<Task> tasks= new ArrayList<Task>();
        //get all tasks from file
        tasks = store.readTasks();
        task.setUpdatedAt();
        tasks.add(task);
        //save all into file
        store.writeTasks(tasks);

        return task;
    }
}