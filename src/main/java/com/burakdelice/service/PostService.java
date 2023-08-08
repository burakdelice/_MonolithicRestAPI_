package com.burakdelice.service;

import com.burakdelice.exception.ResourcesNotFoundException;
import com.burakdelice.model.Category;
import com.burakdelice.model.Post;
import com.burakdelice.model.User;
import com.burakdelice.repository.CategoryRepository;
import com.burakdelice.repository.PostRepository;
import com.burakdelice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    public List<Post> findAll() {
        List<Post> listPosts = postRepository.findAll();
        if(listPosts.size()>0){
            return listPosts;
        }else{
            return new ArrayList<>();
        }
    }

    public ResponseEntity<Post> findById(Long id)  throws ResourcesNotFoundException {
        Post post = postRepository.findById(id)
                .orElseThrow( ()-> new ResourcesNotFoundException("Post not found ID: " + id));
        return ResponseEntity.ok().body(post);
    }
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public ResponseEntity<Post> updateOne(Post postInfo) throws ResourcesNotFoundException{
        Post post = postRepository.findById(postInfo.getId()).orElseThrow( ()-> new ResourcesNotFoundException("Post not found ID: " +postInfo.getId()));
        return ResponseEntity.ok(postRepository.save(post));
    }

    public Map<String,Boolean> deleteOne(Long id)  throws ResourcesNotFoundException{
        Post post = postRepository.findById(id).orElseThrow( ()-> new ResourcesNotFoundException("Post not found ID: " + id));
        postRepository.deleteById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Delete",Boolean.TRUE);
        return response;
    }

    public Map<User, List<Post>> getOnesPosts(Long id) throws ResourcesNotFoundException{
        User user = userRepository.findById(id).orElseThrow(()-> new ResourcesNotFoundException("User not found ID: " + id));
        Map<User, List<Post>> response = new HashMap<>();
        response.put(user,postRepository.findAllByUserId(id));
        return response;
    }
    public Map<Category, List<Post>> getOneCategoryPosts(Long id) throws ResourcesNotFoundException{
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourcesNotFoundException("User not found ID: " + id));
        Map<Category, List<Post>> response = new HashMap<>();
        response.put(category,postRepository.findPostsByCategoryId(id));
        return response;
    }

    public List<Post> postOrderByPublish(){
        return postRepository.postOrderByPublish();
    }
    public List<Post> postOrderByCategoryId(Long id){
        return postRepository.postOrderByCategoryId(id);
    }
    public List<Post> postOrderByUserId(Long id){
        return postRepository.postOrderByUserId(id);
    }

}
