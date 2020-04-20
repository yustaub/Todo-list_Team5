package com.example.teamwork_backend.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.example.teamwork_backend.model.Task;
import com.example.teamwork_backend.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




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
    //delete task by id
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Optional<Task> any = taskService.delete(id);
        if(any.isPresent()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping(produces = "application/json")
    public List<Task> getAllTasks(){
        return taskService.getAll();
    }
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> updatedTask = taskService.updateTaskById(new Task(id, task.getContent()));
        return ResponseEntity.of(updatedTask);
    }
}