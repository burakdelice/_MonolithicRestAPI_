package com.burakdelice.web;

import com.burakdelice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.burakdelice.constant.RestApiUrl.*;

@Controller
@RequestMapping("/api"+POST)
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String getAddPost(Model model) {
        return "add-posts";
    }
}


