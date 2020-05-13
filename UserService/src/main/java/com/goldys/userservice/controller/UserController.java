package com.goldys.userservice.controller;

import com.goldys.userservice.exception.InvalidCredentialsException;
import com.goldys.userservice.exception.UserAlreadyExistsException;
import com.goldys.userservice.exception.UserNotFoundException;
import com.goldys.userservice.model.User;
import com.goldys.userservice.proxy.UserServiceProxy;
import com.goldys.userservice.security.SecurityTokenGenerator;
import com.goldys.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized
 * format. Starting from Spring 4 and above, we can use @RestController annotation which
 * is equivalent to using @Controller and @ResponseBody annotation
 *
 * Please note that the default path to use this controller should be "/api/v1/userservice"
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/userservice")
public class UserController {
    /*
     * Constructor Autowiring should be implemented for the Service Layer for Programs and
     * SecurityTokenGenerator. Please note that we should not create any object
     * using the new keyword. Autowiring should be also implemented for
     * UserServiceProxy interface which is used for implementing client side load
     * balancer.
     */

    private UserService userService;

    private SecurityTokenGenerator securityTokenGenerator;

    private UserServiceProxy userServiceProxy;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator, UserServiceProxy userServiceProxy) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
        this.userServiceProxy = userServiceProxy;
    }

    /* API Version: 1.0
     * Define a handler method which will register a new user by reading the Serialized
     * User object from request body and save the user in database.
     * Please note that the email has to be unique.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 201(CREATED - In case of successful creation of the user
     * 2. 409(CONFLICT) - In case of duplicate email
     *
     * This handler method should map to the URL "/api/v1/userservice" using HTTP POST
     * method".
     */
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }


    /* API Version: 1.0
     * Define a handler method which will update a specific user by reading the
     * Serialized object from request body and save the updated user in
     * user table in database and handle UserNotFoundException as well.
     *
     * This handler method should return any one of the status
     * messages basis on different situations:
     * 1. 200(OK) - If the user is updated successfully.
     * 2. 404(NOT FOUND) - If the user with specified email is not found.
     *
     * This handler method should map to the URL "/api/v1/userservice" using HTTP PUT
     * method
     */
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateProfile(user), HttpStatus.OK);
    }

    /* API Version: 1.0
     * Define a handler method which will authenticate a user by reading the Serialized user
     * object from request body containing the username and password. The username and password should be validated
     * before proceeding ahead with JWT token generation. The user credentials will be validated against the database entries.
     * The error should be return if validation is not successful. If credentials are validated successfully, then JWT
     * token will be generated. The token should be returned back to the caller along with the API response.
     * This handler method should return any one of the status messages basis on different
     * situations:
     * 1. 200(OK) - If the login is successful.
     * 2. 404(NOT FOUND) - If the user with specified email is not found.
     * 3. 401(UNAUTHORIZED) - If the credentials are incorrect
     *
     * This handler method should map to the URL "/api/v1/userservice/login" using HTTP POST
     * method.
     *
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws InvalidCredentialsException, UserNotFoundException {

        Map<String, String> map = null;

        User retrievedUser = userService.login(user);

        if (retrievedUser != null) {
            map = securityTokenGenerator.generateToken(retrievedUser);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    /* API Version: 1.0
     * Define a handler method which will validate a user by reading the email from the request
     * URL. The token should be returned back to the caller along with the API response.
     * This handler method should return the following status message:
     * 1. 200(OK) - both in case the authorization is successful or failure.
     *
     * This handler method should map to the URL "/api/v1/userservice/{email}" using HTTP GET
     * method where email needs to be replaced with user email.
     * This handler method should call validateUser() method of UserService, which
     * will return true if the user authorization is successful, otherwise, it should
     * return false.
     *
     */
    @GetMapping("/{email}")
    public ResponseEntity<?> validateUser(@PathVariable String email) {
        return new ResponseEntity<>(userService.validateUser(email), HttpStatus.OK);
    }

    /* API Version: 1.0
     * Define a handler method which will get us all users.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - If all users found successfully.
     *
     * This handler method should map to the URL "/api/v1/userservice" using HTTP GET
     * method.
     */
    @GetMapping
    public ResponseEntity<?> listAllUsers() {
        return new ResponseEntity<>(userService.listAllUsers(), HttpStatus.OK);
    }

    /* API Version: 1.0
     * Define a handler method which will get us the user by email.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - If the user found successfully.
     * 2. 404(NOT FOUND) - If the user with specified email is not found.
     *
     *
     * This handler method should map to the URL "/api/v1/userservice/getByEmail/{email}" using HTTP GET
     * method, where "programCode" should be replaced by a programCode without {}
     */
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

}