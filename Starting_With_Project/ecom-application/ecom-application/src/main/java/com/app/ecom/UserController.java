package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")

public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
           // return ResponseEntity.ok(userService.fetchAllUsers()); this will also work
        return new ResponseEntity<>(userService.fetchAllUsers(),
                HttpStatus.OK);
    }

   // @RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User Added Succesfully");
    }

    @PutMapping ("{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody User updatedUser){
       boolean updated  = userService.updateUser(id,updatedUser);
       if(updated)
           return ResponseEntity.ok("User Updated Sucessfully");

        return ResponseEntity.notFound().build();
    }


}
