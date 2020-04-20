package com.example.teamwork_backend.service;

import java.util.ArrayList;
import java.util.List;
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
    @Test
    public void shouldGetAllTasks(){
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTasks()).thenReturn(tasks);
        List<Task> taskLists = taskService.getAll();
        assertEquals(tasks,taskLists);
    }
    @Test
    public void shouldReturnTaskIfExists(){
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTasks()).thenReturn(tasks);
        Optional<Task> task = taskService.getTaskById(1L);
        assertTrue(task.isPresent());
        assertEquals(1L, task.get().getId());
        assertEquals("task", task.get().getContent());
    }
    @Test
    public void shouldNotReturnTaskIfNotExists(){
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTasks()).thenReturn(tasks);
        Optional<Task> task = taskService.getTaskById(2L);
        assertFalse(task.isPresent()); 
    }
    @Test
    public void shouldUpdateTaskIfExists(){
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTasks()).thenReturn(tasks);

        Optional<Task> optionalTask = taskService.updateTaskById(new Task(1L, "new task"));
        
        assertTrue(optionalTask.isPresent());

        Task task = optionalTask.get();
        assertEquals(1L, task.getId());
        assertEquals("new task", task.getContent());
        assertNotNull(task.getUpdatedAt());
        verify(taskStore).writeTasks(any());
    }
    @Test
    public void shouldNotUpdateTaskIfNotExists(){
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTasks()).thenReturn(tasks);

        Optional<Task> optionalTask = taskService.updateTaskById(new Task(2L, "new task"));
        
        assertTrue(optionalTask.isPresent());

        Task task = optionalTask.get();
        assertEquals(1L, task.getId());
        assertEquals("new task", task.getContent());
        assertNotNull(task.getUpdatedAt());
        verify(taskStore).writeTasks(any());
    }
}