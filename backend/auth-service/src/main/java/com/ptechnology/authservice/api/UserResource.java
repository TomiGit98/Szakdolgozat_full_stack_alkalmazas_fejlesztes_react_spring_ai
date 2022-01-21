package com.ptechnology.authservice.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptechnology.authservice.domain.Role;
import com.ptechnology.authservice.domain.TokenResponseMessage;
import com.ptechnology.authservice.domain.User;
import com.ptechnology.authservice.repo.UserRepository;
import com.ptechnology.authservice.response.ResponseMessage;
import com.ptechnology.authservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @Autowired
    UserRepository userRepository;

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/user")
    public ResponseEntity<ResponseMessage> getUser(@CookieValue(value = "access_token", defaultValue = "no_access_token_found") String access_token) {
        System.out.println(access_token);
        if(!access_token.equals("no_access_token_found")){
            // Same secret as signed before
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(access_token);
            String username = decodedJWT.getSubject();
            System.out.println("Username: " + username);
            return ResponseEntity.ok().body(new ResponseMessage(1L, username));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/userbyid")
    public ResponseEntity<TokenResponseMessage> gettokens(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getServletPath().toString());
        //String refresh_token = authorizationHeader.substring("Bearer ".length());
        // Same secret as signed before
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        //JWTVerifier verifier = JWT.require(algorithm).build();
        //DecodedJWT decodedJWT = verifier.verify(refresh_token);
        //String username = decodedJWT.getSubject();
        //User user = userService.getUser(username);
        User user = userRepository.findUserById(1L);
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000 ))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000 ))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);

        System.out.println("ACCESS_TOKEN: " + access_token);
        System.out.println("REFRESH_TOKEN: " + refresh_token);
        //new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        return new ResponseEntity<>(new TokenResponseMessage(access_token, refresh_token), HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/tokenisvalid")
    public ResponseEntity<Long> validateToken(@CookieValue(value = "access_token", defaultValue = "no_access_token_found") String access_token) {
        System.out.println("Access_token1: " + access_token);
        access_token = access_token.toString().substring("access_token=".length());
        System.out.println("Access_token: " + access_token);
        if(!access_token.equals("no_access_token_found")){
            try {
                System.out.println("accept");
                // Same secret as signed before
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                System.out.println(verifier.toString());
                DecodedJWT decodedJWT = verifier.verify(access_token);

                String username = decodedJWT.getSubject();
                System.out.println("USERNAME: " + username);
                User user = userService.getUser(username);
                System.out.println(decodedJWT.toString());
                return ResponseEntity.ok().body(user.getId());
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("exception");
                return ResponseEntity.ok().body(-1L);
            }
        } else {
            System.out.println("no_access_token_found");
            return ResponseEntity.ok().body(-2L);
        }
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                // Same secret as signed before
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000 ))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }



    @Data
    class RoleToUserForm {
        private String username;
        private String roleName;
    }

}
