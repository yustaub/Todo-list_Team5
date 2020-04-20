package com.example.teamwork_backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.teamwork_backend.model.Task;
import com.example.teamwork_backend.service.TaskService;
import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private List<Task> tasks = new ArrayList<Task>();

    @BeforeEach
    void setUp() {
        tasks.add(new Task(1L, "a"));
    }
    @Test
    public void shouldCreateTask() throws Exception {
        Task task = new Task(1L, "new");
        Task savedTask = new Task(1L, "new");
        when(taskService.saveTask(any(Task.class))).thenReturn(savedTask);
        this.mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(task)))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void shouldNotCreateTaskWhenIdEixts() throws Exception {
        Task task = new Task(1L, "new");
        when(taskService.saveTask(any(Task.class))).thenReturn(null);
        this.mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(task)))
                .andDo(print()).andExpect(status().isConflict());
    }
    @Test
    public void shouldDeleteWhenTaskExists() throws Exception {
        Task task = new Task(1L, "new");
        when(taskService.delete(task.getId())).thenReturn(Optional.of(task));
        this.mockMvc.perform(delete("/api/tasks/1")).andDo(print()).andExpect(status().isNoContent());
    }
    @Test
    public void shouldNotDeleteIfTaskNotExists() throws Exception {
        when(taskService.delete(1L)).thenReturn(Optional.empty());
        this.mockMvc.perform(delete("/api/tasks/1")).andDo(print()).andExpect(status().isNotFound());
    }
}