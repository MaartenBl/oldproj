package com.user.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import com.user.userservice.model.UserModel;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<UserModel, Integer> {
    UserModel findByEmail(String email);
}
