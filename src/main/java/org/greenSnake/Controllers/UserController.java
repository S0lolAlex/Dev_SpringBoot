package org.greenSnake.Controllers;

import lombok.RequiredArgsConstructor;
import org.greenSnake.Service.UserService;
import org.greenSnake.entities.Users;
import org.greenSnake.enums.UsersRoles;
import org.springframework.beans.factory.annotation.Autowired;
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
        return new ModelAndView("users/list");
    }
    @GetMapping("/add")
    public ModelAndView addNote() {
        return new ModelAndView("users/note");
    }
    @GetMapping("/edit")
    public ModelAndView editNote(@RequestParam(value = "id") int id) {
        ModelAndView result = new ModelAndView("users/note");
        result.addObject("user", service.getById(id));
        return result;
    }
    @PostMapping("/edit")
    public RedirectView editNoteView(@RequestParam(value = "id") int id,
                                     @RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "role")UsersRoles role) {
        if (id == -1) {
            Users user = new Users(id,username,password,role);
            service.add(user);
        } else {
            Users user = service.getById(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            service.update(user);
        }
        return new RedirectView("/users/list");
    }
    @PostMapping("/delete")
    public RedirectView deleteNoteById(@RequestParam(value = "id") int id) {
        service.deleteById(id);
        return new RedirectView("/users/list");
    }
}
