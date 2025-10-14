package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> getAllUsers(){
           // return ResponseEntity.ok(userService.fetchAllUsers()); this will also work
        return new ResponseEntity<>(userService.fetchAllUsers(),
                HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.fetchUser(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User Added Succesfully");
    }

}
