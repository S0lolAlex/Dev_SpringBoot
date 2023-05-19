package org.greenSnake.Controllers;

import org.greenSnake.Service.UserService;
import org.greenSnake.dto.UserDto;
import org.greenSnake.entities.User;
import org.greenSnake.enums.UsersRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/list")
    public ModelAndView getAllUsers() {
        ModelAndView result = new ModelAndView("users/list");
        result.addObject("users", service.listAll());
        return result;
    }

    @GetMapping("/add")
    public ModelAndView addUser() {
        return new ModelAndView("users/user");
    }

    @GetMapping("/edit")
    public ModelAndView editUser(@RequestParam(value = "id") Long id) {
        ModelAndView result = new ModelAndView("users/user");
        User user = service.getById(id);
        String role = user.getRole().getRole();
        result.addObject("user", user);
        result.addObject("role", role);
        return result;
    }

    @PostMapping("/edit")
    public RedirectView editUserView(@RequestParam(value = "id") Long id,
                                     @RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "enabled", required = false) String enabled,
                                     @RequestParam(value = "role") String role) {
        User user = null;
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(password);
        UsersRoles userRole = "Admin".equals(role) ? UsersRoles.ROLE_ADMIN : UsersRoles.ROLE_USER;
        if (id == -1) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEnabled(enabled != null ? 1 : 0);
            user.setRole(userRole);
            service.add(user);
        } else {
            user = service.getById(id);
            user.setUsername(username);
            if (!password.isBlank()) {
                user.setPassword(password);
            }
            user.setEnabled(enabled != null ? 1 : 0);
            user.setRole(userRole);
            service.update(user);
        }
        return new RedirectView("/users/list");
    }

    @PostMapping("/delete")
    public RedirectView deleteUserById(@RequestParam(value = "id") Long id) {
        service.deleteById(id);
        return new RedirectView("/users/list");
    }
}
