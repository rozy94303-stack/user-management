package com.inn.cafe.serviceImp;

import com.inn.cafe.constents.CafeConstents;
import com.inn.cafe.model.User;
import com.inn.cafe.repository.UserRepository;
import com.inn.cafe.service.UserService;
import com.inn.cafe.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;


    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Map<String, String>> signUp(Map<String, String> requestMap) {

        try {
            // signup logic
            log.info("Inside signup {}", requestMap);
            if(ValidateSignUpMap(requestMap)){
                Optional<User> optionalUser = repository.findByEmail(requestMap.get("email"));
                if(optionalUser.isEmpty()){
                    User user = new User();
                    user.setName(requestMap.get("name"));
                    user.setContactNumber(requestMap.get("contactNumber"));
                    user.setEmail(requestMap.get("email"));
                    user.setPassword(requestMap.get("password"));
                    user.setRole("role");
                    repository.save(user);
                }else {
                    return CafeUtils.getResponseEntity(
                            CafeConstents.EMAIL_ALREADY_EXISTS,
                            HttpStatus.BAD_REQUEST);
                }

            }else {
                return CafeUtils.getResponseEntity(
                        CafeConstents.INVALID_DATA,
                        HttpStatus.BAD_REQUEST);
            }

            return CafeUtils.getResponseEntity(
                    CafeConstents.USER_CREATED_SUCCESSFULLY,
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(
                CafeConstents.SomeThing_Went_Wrong,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private boolean ValidateSignUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("contactNumber") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("password");
    }

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User getByUserId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(Integer id, User user) {

        User existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setContactNumber(user.getContactNumber());
        existing.setPassword(user.getPassword());
        existing.setRole(user.getRole());

        return repository.save(existing);
    }

    @Override
    public void deleteUser(Integer id) {

        User existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        repository.delete(existing);
    }
}