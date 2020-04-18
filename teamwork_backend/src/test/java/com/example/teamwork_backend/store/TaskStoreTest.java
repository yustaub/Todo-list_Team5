package com.example.teamwork_backend.store;

import com.example.teamwork_backend.model.Task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskStoreTest{
    @Autowired
    private TaskStore taskStore;

    @AfterEach
    void tearDown() {
        taskStore.writeTasks(Arrays.asList(createTask(1L, "test")));
    }

    
    @Test
    public void shouldReadTasks() {
        List<Task> tasks = taskStore.readTasks();
        //System.out.println(tasks);
        assertEquals(1, tasks.size());
        assertEquals(1L, tasks.get(0).getId());
        assertEquals("test", tasks.get(0).getContent());
        assertNotNull(tasks.get(0).getUpdatedAt());
        
    }

    @Test
    public void shouldWriteTasks() {
        List<Task> tasks = Arrays.asList(createTask(1L, "task 1"), createTask(2L, "task 666"));

        taskStore.writeTasks(tasks);

        List<Task> tasksInStore = taskStore.readTasks();
        assertEquals(2, tasksInStore.size());
        assertNotNull(tasksInStore.get(1).getUpdatedAt());
        assertEquals("task 666", tasksInStore.get(1).getContent());
    }

    private Task createTask(long l, String test) {
        Task task = new Task(l, test);
        task.setUpdatedAt();
        return task;
    }

}