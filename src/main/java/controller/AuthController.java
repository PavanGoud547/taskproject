package controller;

import entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payload.UserDto;
import service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }



    //POST store the user in DB

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        System.out.println("Received request to register user");
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
}
