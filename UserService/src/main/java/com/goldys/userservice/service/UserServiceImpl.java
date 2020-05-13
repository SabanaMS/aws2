package com.goldys.userservice.service;

import com.goldys.userservice.exception.InvalidCredentialsException;
import com.goldys.userservice.exception.UserAlreadyExistsException;
import com.goldys.userservice.exception.UserNotFoundException;
import com.goldys.userservice.model.User;
import com.goldys.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * This class is implementing the UserService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
@Service
public class UserServiceImpl implements UserService {

    /*
     * Constructor Autowiring should be implemented for the UserRepository.
     */

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * Register a new User. Throw UserAlreadyExistsException if the user with specified
     * email already exists.
     */
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    /*
     * Update an existing User by it's email. Throw UserNotFoundException if the
     * user with specified email does not exist.
     */
    @Override
    public User updateProfile(User user) throws UserNotFoundException {
        if (userRepository.findById(user.getEmail()).isEmpty()) {
            throw new UserNotFoundException();
        }
        return userRepository.save(user);
    }

    /* Perform Login operation.
     * Retrieve an existing user by it's email. Throw UserNotFoundException if the
     * user with specified email does not exist.
     * Throw InvalidCredentialsException if the password does not match with the password
     * stored in DB.
     * Caching implementation should be done.
     */
    @Cacheable(value = "users-cache", key = "#user.email")
    @Override
    public User login(User user) throws UserNotFoundException, InvalidCredentialsException {
        if (userRepository.findById(user.getEmail()).isEmpty()) {
            throw new UserNotFoundException();
        }
        User retrievedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (retrievedUser == null) {
            throw new InvalidCredentialsException();
        }
        return retrievedUser;
    }

    /* Validate a particular user to check whether he has role "Executive".
     * Retrieve an existing user by his email and role.
     */
    @Override
    public boolean validateUser(String email) {
        return ((userRepository.findByEmailAndRole(email, "Executive")) != null);
    }

    /*
     * Retrieve all existing users
     */
    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    /*
     * Retrieve an existing user by it's email. Throw UserNotFoundException if the
     * user with specified email does not exist.
     */
    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        if (userRepository.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        return userRepository.findById(email).get();
    }

}
