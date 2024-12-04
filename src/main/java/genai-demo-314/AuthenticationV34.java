```java
//code-start
package com.example.loginapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class.
 */
@SpringBootApplication
public class LoginApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApiApplication.class, args);
    }

}
//code-end

//code-start
package com.example.loginapi.controller;

import com.example.loginapi.model.LoginRequest;
import com.example.loginapi.model.LoginResponse;
import com.example.loginapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling login-related requests.
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * Endpoint to authenticate user login.
     * @param loginRequest The login request payload containing username and password.
     * @return A response entity with login result.
     */
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = loginService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }
}
//code-end

//code-start
package com.example.loginapi.model;

/**
 * Model class for login request payload.
 */
public class LoginRequest {
    
    private String username;
    private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
//code-end

//code-start
package com.example.loginapi.model;

/**
 * Model class for login response payload.
 */
public class LoginResponse {

    private boolean success;
    private String message;

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
//code-end

//code-start
package com.example.loginapi.service;

import com.example.loginapi.model.LoginRequest;
import com.example.loginapi.model.LoginResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils; // Security: Validation

@Service
public class LoginService {

    // Dummy credentials for demonstration purposes
    private static final String DUMMY_USERNAME = "admin";
    private static final String DUMMY_PASSWORD = "password";

    /**
     * Authenticate user based on login request data.
     * @param loginRequest The login request payload containing username and password.
     * @return The login response with authentication result.
     */
    public LoginResponse authenticate(LoginRequest loginRequest) {
        LoginResponse response = new LoginResponse();

        // Validate user input // Security: Validation
        if (StringUtils.isEmpty(loginRequest.getUsername()) || StringUtils.isEmpty(loginRequest.getPassword())) {
            response.setSuccess(false);
            response.setMessage("Username or password cannot be empty.");
            return response;
        }

        // Check credentials
        if (DUMMY_USERNAME.equals(loginRequest.getUsername()) && DUMMY_PASSWORD.equals(loginRequest.getPassword())) {
            response.setSuccess(true);
            response.setMessage("Login successful.");
        } else {
            response.setSuccess(false);
            response.setMessage("Invalid username or password.");
        }

        return response;
    }
}
//code-end
```