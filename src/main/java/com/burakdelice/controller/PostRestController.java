package com.burakdelice.controller;
import com.burakdelice.exception.ResourcesNotFoundException;
import com.burakdelice.model.Category;
import com.burakdelice.model.Post;
import com.burakdelice.model.User;
import com.burakdelice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import static com.burakdelice.constant.RestApiUrl.*;

@RestController
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    //http://localhost:8090/posts/
    @GetMapping ("/")
    public List<Post> findAll(){
        return postService.findAll();
    }
    //http://localhost:8090/posts/id
    @GetMapping(FINDBYID+"/{id}")
    public ResponseEntity<Post> findById(@PathVariable("id") Long id) {
        return postService.findById(id);
    }
    //POST - https://localhost:8090/posts
    @PostMapping("/create")
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    //UPDATE - https://localhost:8090/posts/1
    @PutMapping("/{id}")
    public ResponseEntity<Post> updateOne(@PathVariable("id") Long id,
                                          @RequestBody Post post) {

        Post postInfo = postService.findById(id).getBody();
        if(postInfo!=null){
            postInfo.setId(id);
            postInfo.setTitle(post.getTitle());
            postInfo.setContent(post.getContent());
            postInfo.setPublishedAt(post.getPublishedAt());
            postInfo.setUserId(post.getUserId());
            return postService.updateOne(postInfo);
        }
        return null;
    }
    //DELETE - https://localhost:8090/posts/1
    @DeleteMapping ("/{id}")
    public Map<String,Boolean> deleteOne(@PathVariable("id") Long id)  {
        return postService.deleteOne(id);
    }
    @GetMapping("/user/{id}")
    public Map<User,List<Post>> getOnesPosts(@PathVariable("id") Long id){
        return postService.getOnesPosts(id);
    }
    @GetMapping("/category/{id}")
    public Map<Category,List<Post>> getOneCategoryPosts(@PathVariable("id") Long id){
        return postService.getOneCategoryPosts(id);
    }
    @GetMapping("/orderpublish")
    public ResponseEntity<List<Post>> postOrderByPublish(){
        return ResponseEntity.ok(postService.postOrderByPublish());
    }
    @GetMapping("/ordercategory")
    public ResponseEntity<List<Post>> postOrderByCategoryId(Long id){
        return ResponseEntity.ok(postService.postOrderByCategoryId(id));
    }
    @GetMapping("/orderuser")
    public ResponseEntity<List<Post>> postOrderByUserId(Long id){
        return ResponseEntity.ok(postService.postOrderByUserId(id));
    }


}
