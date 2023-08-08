package com.burakdelice.controller;

import com.burakdelice.dto.response.UserResponseDto;
import com.burakdelice.exception.ResourcesNotFoundException;
import com.burakdelice.model.User;
import com.burakdelice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.burakdelice.constant.RestApiUrl.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    //http://localhost:8090/user/
    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    //http://localhost:8090/user/id
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    //POST - https://localhost:8090/users
    @PostMapping("/")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    //UPDATE - https://localhost:8090/users/1
    @PutMapping("/{id}")
    public ResponseEntity<User> updateOne(@PathVariable("id") Long id,
                                          @RequestBody User user)  {

        User userInfo = userService.findById(id).getBody();
        if (userInfo != null) {
            userInfo.setId(id);
            userInfo.setFirstName(user.getFirstName());
            userInfo.setLastName(user.getLastName());
            userInfo.setEmail(user.getEmail());
            return userService.updateOne(userInfo);
        }
        return null;
    }

    //DELETE - https://localhost:8090/users/1
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteOne(@PathVariable("id") Long id)  {
        return userService.deleteOne(id);
    }

}
