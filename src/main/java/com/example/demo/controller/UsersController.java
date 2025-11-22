package com.example.demo.controller;
import com.example.demo.model.Users;
import com.example.demo.service.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("api/users")
@RestController
@CrossOrigin
public class UsersController {

    private UsersRepository usersRepository;

    public UsersController (UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @GetMapping("/getAllUsers/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable Long id) {
        Users u =usersRepository.findById(id).orElse(null);
        if(u==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
    @DeleteMapping("/deleteUsers")
    public ResponseEntity deleteUsers(@RequestBody Users users){
        usersRepository.deleteById(users.getId());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAllUsers") public List<Users> getAll() {

        return usersRepository.findAll();
    }

    @PostMapping("/addUsers")
    public ResponseEntity<Users> addUsers(@RequestBody Users users) {
        usersRepository.save(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/updateUsers/{id}")
    public ResponseEntity updateUsers(@PathVariable Long id, @RequestBody Users users) {
        if(id!=users.getId()){
            return new ResponseEntity(HttpStatus.CONFLICT);}
        usersRepository.save(users);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Users user) {
        List<Users> users = usersRepository.findAll();
        for (Users u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                if (u.getPassword().equals(user.getPassword())) {
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
                }
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody Users user) {
        List<Users> users = usersRepository.findAll();
        for (Users u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
            }
        }
        if(user.getUsername()!=null ||user.getPassword()!=null||user.getEmail()!=null){
            usersRepository.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Username or password are required", HttpStatus.NOT_FOUND);
    }

}
