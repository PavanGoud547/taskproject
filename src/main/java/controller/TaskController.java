package controller;

import entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.TaskDto;
import service.TaskService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;
    // save the task
    @PostMapping("/{userid}/tasks")
    public RequestEntity<TaskDto> saveTask(
            @PathVariable(name = "userid") long userid,
            @RequestBody TaskDto taskDto
    ){

        return new RequestEntity<>(TaskService.saveTask(userid, taskDto), CREATED);

    }
    //get all task
    @GetMapping("/{userid}/tasks")
    public RequestEntity<List<TaskDto>> getAllTasks(
            @PathVariable(name = "userid") long userid
    ){
        return new RequestEntity<>(taskService.getAllTasks(userid), OK);
    }



    //get indv task
    @GetMapping("/{userid}/tasks/{taskid}")
    public  ResponseEntity<TaskDto> getTask(
            @PathVariable(name = "userid") long userid,
            @PathVariable(name = "taskid") long taskid
    ){
        return new ResponseEntity<>(taskService.getTasks(userid, taskid), OK);
    }
    //delete indiv task

}
