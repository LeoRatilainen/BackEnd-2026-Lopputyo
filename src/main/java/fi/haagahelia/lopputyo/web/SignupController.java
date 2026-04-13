package fi.haagahelia.lopputyo.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.lopputyo.domain.User;
import fi.haagahelia.lopputyo.domain.UserRepository;

@Controller
public class SignupController {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
 
    public SignupController(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public String addEntity(Model model) {
        model.addAttribute("signupform", new User());
        return "signup";
    }

    @PostMapping("/saveuser")
    public String save(@ModelAttribute("signupform") User user, Model model) {
        if (repository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("errormsg", "Username already exists!");
            return "signup";
        }

        String pwd = user.getPasswordHash();
        String hashPwd = passwordEncoder.encode(pwd);
        user.setPasswordHash(hashPwd);

        user.setRole("USER");

        repository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
