package com.burakdelice.service;

import com.burakdelice.dto.response.UserResponseDto;
import com.burakdelice.exception.ResourcesNotFoundException;
import com.burakdelice.mapper.IUserMapper;
import com.burakdelice.model.User;
import com.burakdelice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        List<User> listUsers = userRepository.findAll();
        if(listUsers.size()>0){
            return listUsers;
        }else{
            return new ArrayList<User>();
        }
    }

    public ResponseEntity<User> findById(Long id)  throws ResourcesNotFoundException{
        User user = userRepository.findById(id)
                .orElseThrow( ()-> new ResourcesNotFoundException("User not found ID: " + id));
        return ResponseEntity.ok().body(user);
    }
    public UserResponseDto createUser(User user) {
        UserResponseDto dto = IUserMapper.INSTANCE.toUserResponseDto(userRepository.save(user));
        return dto;
    }

    public ResponseEntity<User> updateOne(User userInfo) throws ResourcesNotFoundException{
        User user = userRepository.findById(userInfo.getId()).orElseThrow( ()-> new ResourcesNotFoundException("User not found ID: " +userInfo.getId()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    public Map<String,Boolean> deleteOne(Long id)  throws ResourcesNotFoundException{
        User user = userRepository.findById(id).orElseThrow( ()-> new ResourcesNotFoundException("User not found ID: " + id));
        userRepository.deleteById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Delete",Boolean.TRUE);
        return response;
    }


}
