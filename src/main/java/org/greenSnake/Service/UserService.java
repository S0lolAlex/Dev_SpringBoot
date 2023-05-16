package org.greenSnake.Service;

import lombok.RequiredArgsConstructor;
import org.greenSnake.entities.User;
import org.greenSnake.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UsersRepository repository;

    public List<User> listAll() {
        return repository.findAll();
    }

    public User add(User user) {
        return repository.save(user);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public User getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    //    public Users getByName(String name){
//        return repository.findByName(name);
//    }
    public void update(User user) {
        repository.saveAndFlush(user);
    }
}
