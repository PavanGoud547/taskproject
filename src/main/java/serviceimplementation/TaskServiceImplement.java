package serviceimplementation;

import entity.Task;
import entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payload.TaskDto;
import repository.TaskRepository;
import repository.UserRepository;
import service.TaskService;

import java.util.List;

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
       Users users = userRepository.findById(userid).get();
       Task task = modelMapper.map(taskDto, Task.class);
       task.setUsers(users);
       //after setting the user, we are storing the data in db
       Task saveTask = taskRepository.save(task);
       return modelMapper.map(saveTask, TaskDto.class);
    }

    //@Override
    public List<TaskDto> getAlltasks(long userid) {
        return null;
    }


}
