package com.example.teamwork_backend.controller;

import java.net.URI;
import java.util.Optional;

import com.example.teamwork_backend.model.Task;
import com.example.teamwork_backend.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping( "/api/tasks" )
public class TaskController 
{
    @Autowired
    public TaskService taskService;
    //create new task
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task newTask = taskService.saveTask(task);
        if(newTask != null)
        {// 返回 201.created
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/tasks/{id}")
                .buildAndExpand(task.getId())
                .toUri();
            return ResponseEntity.created(location).build();
        }else{
        //id already exists-返回 409.conflict
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
    }
}