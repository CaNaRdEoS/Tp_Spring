package com.example.accessingdatamysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.accessingdatamysql.UserRepository;
import com.example.accessingdatamysql.ArticleRepository;
import com.example.accessingdatamysql.model.Article;
import com.example.accessingdatamysql.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(path = "/users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping(path = "/user/add")
    public ResponseEntity<String> addNewUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Sécurisation du mot de passe
        user.setRole(role);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> oneUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Si l'utilisateur n'est pas trouvé
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> replaceUser(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String role, @PathVariable Long id) {

        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUsername(username);
            user.setPassword(password); // Sécurisation du mot de passe
            user.setRole(role);
            userRepository.save(user);
            return ResponseEntity.ok("User Updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Code HTTP 204
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Si l'utilisateur n'existe pas
        }
    }

    
    @GetMapping(path = "/article/all")
    public @ResponseBody Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @PostMapping(path = "/article/add")
    public @ResponseBody String addNewArticle(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime publishedDate,
            @RequestParam Long userId,
            @RequestParam String message) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return "Error: User not found";
        }

        User user = userOptional.get();

        Article article = new Article();
        article.setPublishedDate(publishedDate);
        article.setUser(user);
        article.setMessage(message);
        articleRepository.save(article);
        return "Saved";
    }
    
    @GetMapping("/article/{id}")
    public @ResponseBody Article oneArticle(@PathVariable Long id) {
      
      return articleRepository.findById(id).get();
    }
}
