package org.greenSnake.Controllers;

import lombok.RequiredArgsConstructor;
import org.greenSnake.Service.NoteService;
import org.greenSnake.Service.UserService;
import org.greenSnake.entities.Users;
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

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/list")
    public ModelAndView getAllUsers(){
        ModelAndView result = new ModelAndView("users/list");
        result.addObject("users",service.listAll());
        return result;
    }
    @GetMapping("/add")
    public ModelAndView addUser() {
        return new ModelAndView("users/note");
    }
    @GetMapping("/edit")
    public ModelAndView editUser(@RequestParam(value = "id") Long id) {
        ModelAndView result = new ModelAndView("users/note");
        result.addObject("user", service.getById(id));
        return result;
    }
    @PostMapping("/edit")
    public RedirectView editUserView(@RequestParam(value = "id") Long id,
                                     @RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "enabled", required = false) String enabled,
                                     @RequestParam(value = "role")UsersRoles role) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        UsersRoles role1 = "ADMIN".equals(role) ? UsersRoles.ADMIN_ROLE : UsersRoles.USER_ROLE;
        if (id == 0) {
            Users user = new Users(username,hashedPassword,
                    role1,
                    enabled!=null ? 1:0 );
            service.add(user);
        } else {
            Users user = service.getById(id);
            user.setUsername(username);
            user.setPassword(hashedPassword);
            user.setRole(role1);
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
