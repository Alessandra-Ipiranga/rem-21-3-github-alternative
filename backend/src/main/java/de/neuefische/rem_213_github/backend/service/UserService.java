package de.neuefische.rem_213_github.backend.service;

import de.neuefische.rem_213_github.backend.model.UserEntity;
import de.neuefische.rem_213_github.backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity create(String name) {
        if (!hasText(name)) {
            throw new IllegalArgumentException("Name must not be blank");
        }

        Optional<UserEntity> existingUser = find(name);
        if (existingUser.isPresent()) {
            throw new EntityExistsException(String.format(
                    "User with name=%s already exists", name));
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);

        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> find(String name) {
        return userRepository.findByName(name);
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

}
