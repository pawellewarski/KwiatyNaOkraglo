package pl.pawellewarski.Kwiaty.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pawellewarski.Kwiaty.model.entities.User;
import pl.pawellewarski.Kwiaty.model.forms.LoginForm;
import pl.pawellewarski.Kwiaty.model.forms.RegisterForm;
import pl.pawellewarski.Kwiaty.repository.UserRepository;
import pl.pawellewarski.Kwiaty.services.UserSessionService;

import javax.validation.Valid;

@Controller
public class UserController {

    //    @Autowired
    private UserRepository userRepository;

    //    @Autowired
    private UserSessionService userSessionService;

    //    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserRepository userRepository, UserSessionService userSessionService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userSessionService = userSessionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String logUser(Model model) {

        model.addAttribute("loginForm", new LoginForm());

        return "login";
    }

    @PostMapping("/login")
    public String addUser(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult, Model model) {


        boolean logged = userSessionService.loginUser(loginForm.getUserName(), loginForm.getPassword());
        if (!logged) {
            bindingResult.rejectValue("userName", null, "Użytkownik nie istnieje");
        }

        if (bindingResult.hasErrors()) {
            return "login";
        }

        model.addAttribute("loggedUser", logged);

        return "redirect:/";


    }


    @GetMapping("/register")
    public String addUser(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid RegisterForm registerForm, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            System.out.println("NIe udało się zalogować");
            return "register";
        }

        User user = modelMapper.map(registerForm, User.class);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        userSessionService.logout();
        return "redirect:/login";
    }


}
