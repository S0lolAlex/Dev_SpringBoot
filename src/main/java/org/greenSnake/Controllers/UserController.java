package org.greenSnake.Controllers;

import lombok.RequiredArgsConstructor;
import org.greenSnake.Service.UserService;
import org.greenSnake.dto.UserDto;
import org.greenSnake.entities.User;
import org.greenSnake.enums.UsersRoles;
import org.greenSnake.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/list")
    public ModelAndView getAllUsers(){
        ModelAndView result = new ModelAndView("users/list");
//        User user = new User();
//        user.setUsername("Adam");
//        user.setEnabled(1);
//        user.setRole(UsersRoles.ADMIN_ROLE);
//        user.setPassword("default");
//        List<User> users = List.of(user);
        result.addObject("users",service.listAll());
        return result;
    }
    @GetMapping("/add")
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView("users/note");
        UserDto userDto = UserDto.builder().build();
        modelAndView.addObject("user",userDto);
        return modelAndView;

    }
    @GetMapping("/edit")
    public ModelAndView editUser(@RequestParam(value = "id") Long id) {
        ModelAndView result = new ModelAndView("users/note");
        User user = service.getById(id);
        String role = user.getRole().getRole();
        result.addObject("user", user.toUserDto());
        result.addObject("userRole", role);
        return result;
    }
    @PostMapping("/edit")
    public RedirectView editUserView(@RequestParam(value = "id") Long id,
                                     @RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "enabled", required = false) String enabled,
                                     @RequestParam(value = "role")String role) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        UsersRoles role1 = "ADMIN".equals(role) ? UsersRoles.ADMIN_ROLE : UsersRoles.USER_ROLE;
        if (id == 0) {
//            User user = new User(username,hashedPassword,
//                    role1,
//                    enabled!=null ? 1:0 );
//            service.add(user);
        } else {
            User user = service.getById(id);
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
