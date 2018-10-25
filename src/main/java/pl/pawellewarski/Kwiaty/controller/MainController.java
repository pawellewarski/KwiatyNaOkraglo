package pl.pawellewarski.Kwiaty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawellewarski.Kwiaty.model.entities.Category;
import pl.pawellewarski.Kwiaty.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String home(Model model){


        List<Category> categoryList = new ArrayList<>();

        Iterable<Category> categoryIterable = categoryRepository.findAll();

        for (Category category : categoryIterable) {
            categoryList.add(category);
        }

        model.addAttribute("categories", categoryList);
        return "home";
    }
}
