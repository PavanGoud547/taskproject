package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.TaskDto;
import service.TaskService;

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

        //return new  RequestEntity<>(taskService.saveTask(userid, taskDto),HttpStatus.CREATED)
        return new ResponseEntity<>(taskService.saveTask(userid, taskDto);
    }
    //get all task




    //get indv task

    //delete indiv task

}
