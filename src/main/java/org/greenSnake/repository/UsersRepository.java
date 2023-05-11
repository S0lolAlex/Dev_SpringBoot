package org.greenSnake.repository;

import org.greenSnake.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE users.username = :name")
    Users findByName(String name);
}
