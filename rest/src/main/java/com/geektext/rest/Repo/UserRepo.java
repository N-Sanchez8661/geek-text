package com.geektext.rest.Repo;

import com.geektext.rest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepo extends JpaRepository<User, Long>{

}
