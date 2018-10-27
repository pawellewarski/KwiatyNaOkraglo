package pl.pawellewarski.Kwiaty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pawellewarski.Kwiaty.model.entities.Category;
import pl.pawellewarski.Kwiaty.model.entities.Post;
import pl.pawellewarski.Kwiaty.repository.CategoryRepository;
import pl.pawellewarski.Kwiaty.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/addCategory")
    public String addPost() {

        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addPost(@RequestParam(value = "title") String title,
                          @RequestParam(value = "imgHtml") String imgHtml,
                          @RequestParam(value = "categoryDescription") String categoryDescription,
                          Model model) {
        List<Category> categoryList = new ArrayList<>();
        Iterable<Category> categoryIterable = categoryRepository.findAll();
        for (Category category : categoryIterable) {
            categoryList.add(category);
        }
        model.addAttribute("categories", categoryList);

        Category category = new Category(title, imgHtml, categoryDescription);

        categoryRepository.save(category);

        return "redirect:/";
    }

    @GetMapping("/category/{id}")
    public String post(@PathVariable Long id,
                       Model model) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        categoryOptional.ifPresent(category -> {
            model.addAttribute("category", category);
        });


        List<Post> postList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAll();
        for (Post post : postIterable) {
            if (post.getCategories().contains(categoryRepository.getOne(id)))
                postList.add(post);
        }
        model.addAttribute("posts", postList);

        return "categories";
    }


}
