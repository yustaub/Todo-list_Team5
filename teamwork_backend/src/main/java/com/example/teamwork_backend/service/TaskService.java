package com.example.teamwork_backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.teamwork_backend.model.Task;
import com.example.teamwork_backend.store.TaskStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TaskService{
    @Autowired TaskStore store;
    public Task saveTask(Task task) {
        //get all tasks from file
        List<Task> tasks= new ArrayList<Task>();
        tasks = store.readTasks();
        //check if ID exists
        Optional<Task> any = tasks.stream().filter(task1 -> task1.getId() == task.getId()).findAny();
        if (!any.isPresent()) {//id does not exist
            task.setUpdatedAt();
            tasks.add(task);
            //save all into file
            store.writeTasks(tasks);
            return task;
        }
        return null;
    }
    public Optional<Task> delete(Long id) {
        List<Task> tasks = store.readTasks();
        Optional<Task> any = tasks.stream().filter(task1 -> task1.getId() == id).findAny();
        if (any.isPresent()) {//check if ID exists
            store.writeTasks(tasks.stream().filter(task -> task.getId() != id).collect(Collectors.toList()));
            return any;
        }
        return any;
    }
    public List<Task> getAll(){
        List<Task> tasks = store.readTasks();
        return tasks;
    }
    public Optional<Task> getTaskById(Long id){
        List<Task> tasks = store.readTasks();
        Optional<Task> any = tasks.stream().filter(task1 -> task1.getId() == id).findAny();
        return any;
    }
    public Optional<Task> updateTaskById(Task newTask){
        List<Task> tasks = store.readTasks();
        Optional<Task> any = tasks.stream().filter(task -> task.getId() == newTask.getId()).findAny();
        if (any.isPresent()) {
            any.get().setContent(newTask.getContent());
            any.get().setUpdatedAt();
            store.writeTasks(tasks);
        }
        return any;
    }
}