package org.greenSnake.repository;

import org.greenSnake.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM users u WHERE u.username = :name")
    Users findByName(String name);
}
