package org.greenSnake.Service;

import lombok.RequiredArgsConstructor;
import org.greenSnake.entities.Users;
import org.greenSnake.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService{
    @Autowired
    private UsersRepository repository;

    public List<Users> listAll(){
        return repository.findAll();
    }

    public Users add(Users user){
        return repository.save(user);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
    public Users getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public Users getByName(String name){
        return repository.findByName(name);
    }
    public void update(Users user){
        repository.saveAndFlush(user);
    }
}
