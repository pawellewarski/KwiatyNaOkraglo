package pl.pawellewarski.Kwiaty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pawellewarski.Kwiaty.model.entities.Category;
import pl.pawellewarski.Kwiaty.model.entities.Post;
import pl.pawellewarski.Kwiaty.model.entities.PostComment;
import pl.pawellewarski.Kwiaty.repository.CategoryRepository;
import pl.pawellewarski.Kwiaty.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.*;

@Controller
public class PostController {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/addPost")
    public String addPost(Model model) {

        List<Category> categoryList = new ArrayList<>();

        Iterable<Category> categoryIterable = categoryRepository.findAll();

        for (Category category : categoryIterable) {
            categoryList.add(category);
        }

        model.addAttribute("categories", categoryList);

        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam(value = "title") String titleParam,
                          @RequestParam(value = "content") String content,
                          @RequestParam(value = "imgHtml") String imgHtml,
                          @RequestParam(value = "category") Long[] categoryId,
                          Model model) {
        List<Post> postList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAll();
        for (Post post : postIterable) {
            postList.add(post);
        }
        model.addAttribute("posts", postList);


        Post post = new Post(titleParam, content, imgHtml);
        PostComment postComment = new PostComment();
        postComment.setComment(titleParam);

        Set<Category> categoryList = new HashSet<>();
        for (Long id : categoryId) {
            categoryList.add(categoryRepository.getOne(id));
        }

        post.setCategories(categoryList);


        post.addComment(postComment);
        postRepository.save(post);

        return "redirect:/";
    }

/*
    @GetMapping("/post/{id}")
    public String post(@PathVariable Long id,
                       Model model) {
        Optional<Post> postOptional = postRepository.findById(id);

        postOptional.ifPresent(post -> {
            model.addAttribute("post", post);
        });

        return "post";
    }

    @Transactional
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deletePost(@PathVariable Long id) {

        postRepository.deleteById(id);

        return ("Usunięto " + id);
    }


    @PostMapping("/post/addComment")
    public String addComment(@RequestParam String commentBody,
                             @RequestParam Long postId) {
        PostComment postComment = new PostComment();

        postComment.setComment(commentBody);


        Optional<Post> postOptional = postRepository.findById(postId);
        postOptional.ifPresent(post -> {
            post.addComment(postComment);
            postRepository.save(post);
        });

        return "redirect:/post/" + postId;

    }
*/

}
