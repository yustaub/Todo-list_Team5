package com.example.teamwork_backend;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.teamwork_backend.model.Task;
import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TasksIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void noParamTasksShouldReturnAllTasksFromService() throws Exception {
        this.mockMvc.perform(get("/api/tasks")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("test"));
    }

    @Test
    @Order(2)
    public void shouldSaveTask() throws Exception {
        Task task = new Task(2L,"task2");
        this.mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(task)))
                .andDo(print()).andExpect(status().isCreated());
    }
    @Test
    @Order(3)
    public void shouldNotSaveTaskWhenIdExists() throws Exception {
        Task task = new Task(2L,"task2");
        this.mockMvc.perform(post("/api/tasks")
        .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(task)))
        .andDo(print()).andExpect(status().isConflict());
    }

    @Test
    @Order(4)
    public void shouldUpdateContentByTaskId() throws Exception {
        this.mockMvc.perform(put("/api/tasks/2")
                .content("{ \"content\" : \"it should work\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(jsonPath("$.content").value("it should work"));
    }

    @Test
    @Order(5)
    public void shouldDeleteByTaskId() throws Exception {
        this.mockMvc.perform(delete("/api/tasks/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    public void shouldGetNotFoundWhenTaskDoesNotExist() throws Exception {
        this.mockMvc.perform(delete("/api/tasks/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound());
    }
}
