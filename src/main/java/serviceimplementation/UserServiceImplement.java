package serviceimplementation;

import entity.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payload.UserDto;
import repository.UserRepository;
import service.UserService;
@Service
public class UserServiceImplement implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto){
        Users user = userDtoToEntity(userDto);//converted userDto to users to store the data
        Users savedUsers = userRepository.save(user);

        return entityToUserDto(savedUsers);
    }

    private Users userDtoToEntity(UserDto userDto){
        Users users = new Users();
        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        return users;
    }

   private UserDto entityToUserDto(Users savedUser){
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setName(savedUser.getName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setPassword(savedUser.getPassword());
        userDto.setPassword(savedUser.getPassword());
        return userDto;
   }
}
