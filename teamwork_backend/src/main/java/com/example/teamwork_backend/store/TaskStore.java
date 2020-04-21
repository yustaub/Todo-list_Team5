package com.example.teamwork_backend.store;

import com.google.gson.*;
import com.example.teamwork_backend.model.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

@Service
public class TaskStore {
    @Value("${todo.store.filename}")//取出配置文件中的值
    private String fileName;
    @Value("${env}")
    private String env;
    
    private File getFile() {
        if (env.equals("test")) {
            return new File(getClass().getClassLoader().getResource(fileName).getFile());
        }
        return new File(fileName);
    }

    private Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                        (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                        (localDateTime, typeOfT, context) -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .create();
    }


    public List<Task> readTasks() {
        try {
            String contents = new String(Files.readAllBytes(getFile().toPath()));
            Task[] tasks = getGson().fromJson(contents, Task[].class);
            List<Task> arrayList = new ArrayList<Task>(Arrays.asList(tasks));
            return arrayList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void writeTasks(List<Task> tasks){
        try{
            FileWriter fileWriter =new FileWriter(getFile().getAbsolutePath());
            fileWriter.write(getGson().toJson(tasks));
            fileWriter.flush();
            fileWriter.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
