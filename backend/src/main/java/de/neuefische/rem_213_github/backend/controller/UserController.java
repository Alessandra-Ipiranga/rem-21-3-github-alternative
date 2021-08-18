package de.neuefische.rem_213_github.backend.controller;

import de.neuefische.rem_213_github.backend.api.User;
import de.neuefische.rem_213_github.backend.model.UserEntity;
import de.neuefische.rem_213_github.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.neuefische.rem_213_github.backend.controller.UserController.USER_CONTROLLER_TAG;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = USER_CONTROLLER_TAG, description = "Provides CRUD operations for an User")
@Api(
        tags = USER_CONTROLLER_TAG
)
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    public static final String USER_CONTROLLER_TAG = "User";

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = SC_BAD_REQUEST, message = "Unable to create User with blank name"),
            @ApiResponse(code = SC_CONFLICT, message = "Unable to create User, user already exists")
    }
    )
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            UserEntity createdUserEntity = userService.create(user.getName());
            User createdUser = map(createdUserEntity);
            return ok(createdUser);

        } catch (IllegalArgumentException e) {
            return badRequest().build();
        } catch (EntityExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @GetMapping(value = "{name}", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "User not found")
    }
    )
    public ResponseEntity<User> find(@PathVariable String name) {
        Optional<UserEntity> userEntityOptional = userService.find(name);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            User user = map(userEntity);
            return ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "User not found")
    }
    )
    public ResponseEntity<List<User>> findAllUsers() {
        List<UserEntity> userEntityList = userService.findAllUsers();
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            User user = map(userEntity);
            userList.add(user);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
        //return ok(userList);
    }

    private User map(UserEntity userEntity) {
        return User.builder()
                .name(userEntity.getName())
                .avatar(userEntity.getAvatarUrl())
                .build();
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "User not found")
    }
    )

    public ResponseEntity<User> deleteUser(@PathVariable String name) {
        Optional<UserEntity> userEntityOptional = userService.delete(name);
                if (userEntityOptional.isPresent()) {
                    UserEntity userEntity = userEntityOptional.get();
                    User user = map(userEntity);
                    return ok(user);
                }
        return ResponseEntity.notFound().build();
    }
}
