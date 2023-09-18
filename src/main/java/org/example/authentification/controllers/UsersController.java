package org.example.authentification.controllers;

import org.example.authentification.entity.Reaction;
import org.example.authentification.models.ReactionModel;
import org.example.authentification.repository.ReactionRepo;
import org.example.authentification.security.JWTUtil;
import org.example.authentification.entity.User;
import org.example.authentification.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/v1/users")
public class UsersController {
    @Autowired private UserRepo userRepo;
    @Autowired private ReactionRepo reactionRepo;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerHandler(@RequestBody User user) {
//        String encodedPass = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPass);

        user = userRepo.save(user);
        String token = jwtUtil.generateToken(String.valueOf(user.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("jwt-token", token));
    }

    @PostMapping("/reaction/{userid}")
    public ResponseEntity<Object> captureReaction(@PathVariable int userid, @RequestBody ReactionModel reactionStr,
                                                  @RequestParam(name = "book_id") long bookId,
                                                  @RequestParam(name = "story_id") long storyId) { // need jwt?
        Reaction reaction = new Reaction(bookId, userid, storyId, reactionStr.getReaction().name());
        reactionRepo.save(reaction);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @PostMapping("/login")
//    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body) {
//        try {
//            UsernamePasswordAuthenticationToken authInputToken =
//                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
//
//            authManager.authenticate(authInputToken);
//
//            String token = jwtUtil.generateToken(body.getEmail());
//            return Collections.singletonMap("jwt-token", token);
//        } catch (AuthenticationException authExc) {
//            throw new RuntimeException("Invalid Login Credentials");
//        }
//    }


}
