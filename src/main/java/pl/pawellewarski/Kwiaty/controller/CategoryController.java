package pl.pawellewarski.Kwiaty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pawellewarski.Kwiaty.model.entities.Category;
import pl.pawellewarski.Kwiaty.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/addCategory")
    public String addPost(){

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
            categoryList.add(category);}
        model.addAttribute("categories", categoryList);

        Category category = new Category(title, imgHtml, categoryDescription);

        categoryRepository.save(category);

        return "redirect:/";
    }

}
