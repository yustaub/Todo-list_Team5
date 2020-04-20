package com.example.teamwork_backend.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.teamwork_backend.model.Task;
import com.example.teamwork_backend.store.TaskStore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
@SpringBootTest
public class TaskServiceTest{
    @Mock
    private TaskStore taskStore;

    @InjectMocks
    private TaskService taskService = new TaskService();
    
    private ArrayList<Task> tasks;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
    }

    @Test
    public void shouldSaveTask() {
        when(taskStore.readTasks()).thenReturn(tasks);

        Task savedTask = taskService.saveTask(new Task(1L, "newTask"));

        assertNotNull(savedTask.getUpdatedAt());
        verify(taskStore).writeTasks(any());
    }
    @Test
    public void shouldNotSaveTaskWhenIdExists(){
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTasks()).thenReturn(tasks);

        Task savedTask = taskService.saveTask(new Task(1L, "newTask"));
        assertNull(savedTask);
    }
    @Test
    public void shouldDeleteTask(){
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTasks()).thenReturn(tasks);

        Optional<Task> optionalTask = taskService.delete(1L);
        assertTrue(optionalTask.isPresent());
        
        Task task = optionalTask.get();
        assertEquals(1L, task.getId());
        verify(taskStore).writeTasks(any());
    }
    @Test
    public void shouldNotDeleteTaskWhenIdNotExists(){
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTasks()).thenReturn(tasks);

        Optional<Task> optionalTask = taskService.delete(2L);
        assertFalse(optionalTask.isPresent());
    }
}