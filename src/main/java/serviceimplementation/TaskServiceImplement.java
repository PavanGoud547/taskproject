package serviceimplementation;

import entity.Task;
import entity.Users;
import exception.APIException;
import exception.TaskNotFound;
import exception.UserNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payload.TaskDto;
import repository.TaskRepository;
import repository.UserRepository;
import service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImplement implements TaskService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskDto saveTask(long userid, TaskDto taskDto) {
       Users users = userRepository.findById(userid).orElseThrow(
               () -> new UserNotFound(String.format("User Id % not found", userid))
       );
       Task task = modelMapper.map(taskDto, Task.class);
       task.setUsers(users);
       //after setting the user, we are storing the data in db
       Task saveTask = taskRepository.save(task);
       return modelMapper.map(saveTask, TaskDto.class);
    }

    @Override
    public List<TaskDto> getAllTasks(long userid) {
        return List.of();
    }

    @Override
    public TaskDto getTasks(long userid, long taskid) {
        Users users = userRepository.findById(userid).orElseThrow(
                () -> new UserNotFound(String.format("User Id % not found", userid))
        );
        Task task = taskRepository.findAllById(taskid).orElseThrow(
                () -> new TaskNotFound(String.format("Task Id % not Found",taskid))
        );
        if (users.getId() != task.getUsers().getId()){
            throw new APIException(String.format("Task Id % id is not belongs to ser Id %", taskid,userid));
        }
        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public void deleteTask(long userid, long taskid) {
        Users users = userRepository.findById(userid).orElseThrow(
                () -> new UserNotFound(String.format("User Id % not found", userid))
        );
        Task task = taskRepository.findAllById(taskid).orElseThrow(
                () -> new TaskNotFound(String.format("Task Id % not Found",taskid))
        );
        if (users.getId() != task.getUsers().getId()){
            throw new APIException(String.format("Task Id % id is not belongs to ser Id %", taskid,userid));
        }
        taskRepository.deleteById(taskid);
    }

    //@Override
    public List<TaskDto> getAlltasks(long userid) {
        userRepository.findById(userid).orElseThrow(
                () -> new UserNotFound(String.format("User Id % not found", userid))
        );

        List<Task> tasks = taskRepository.findAllByUsersId(userid);
        return tasks.stream().map(
                task -> modelMapper.map(task, TaskDto.class)
        ).collect(Collectors.toList());
    }


}
