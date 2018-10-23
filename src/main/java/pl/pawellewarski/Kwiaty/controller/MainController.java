package pl.pawellewarski.Kwiaty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawellewarski.Kwiaty.model.entities.Post;
import pl.pawellewarski.Kwiaty.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/")
    public String home(Model model){


        List<Post> postList = new ArrayList<>();

        Iterable<Post> postIterable = postRepository.findAll();

        for (Post post : postIterable) {
            postList.add(post);
        }

        model.addAttribute("posts", postList);
        return "home";
    }




}
