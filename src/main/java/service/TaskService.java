package service;

import payload.TaskDto;

public interface TaskService {

    public TaskDto saveTask(long userid, TaskDto taskDto);
}
