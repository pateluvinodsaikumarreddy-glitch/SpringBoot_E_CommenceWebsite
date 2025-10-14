package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;


//    @Autowired
//  private UserService userService; or you just use constructor

// Instead of this you can use Loombook based anotations @RequiredArgsConstructor
//    private UserService userService;
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }


    @GetMapping("/api/users")
    public List<User> getAllUsers(){
            return userService.fetchAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.fetchUser(id);
    }

    @PostMapping("/api/users")
    public String createUser(@RequestBody User user){
        userService.addUser(user);
        return "User Added Succesfully";
    }

}
