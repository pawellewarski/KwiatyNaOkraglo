package pl.pawellewarski.Kwiaty.controller;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.pawellewarski.Kwiaty.model.entities.Category;
import pl.pawellewarski.Kwiaty.model.entities.Post;
import pl.pawellewarski.Kwiaty.model.entities.PostComment;
import pl.pawellewarski.Kwiaty.repository.CategoryRepository;
import pl.pawellewarski.Kwiaty.repository.PostRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class PostController {

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads";


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
                          @RequestParam(value = "file") MultipartFile file,
                          @RequestParam(value = "price") Double price,
                          @RequestParam(value = "category") Long[] categoryId,
                          Model model) {
        List<Post> postList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAll();
        for (Post post : postIterable) {
            postList.add(post);
        }
        model.addAttribute("posts", postList);

        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Post post = new Post(titleParam, content, file.getOriginalFilename());
        PostComment postComment = new PostComment();
        postComment.setComment(titleParam);

        Set<Category> categoryList = new HashSet<>();
        for (Long id : categoryId) {
            categoryList.add(categoryRepository.getOne(id));
        }

        post.setCategories(categoryList);


        post.addComment(postComment);
        postRepository.save(post);


        return "home";
    }

}
