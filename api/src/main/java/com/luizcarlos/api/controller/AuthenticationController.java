package com.luizcarlos.api.controller;

import com.luizcarlos.api.model.Users;
import com.luizcarlos.api.dtos.securityDTO.AuthenticationDTO;
import com.luizcarlos.api.dtos.securityDTO.LoginResponseDTO;
import com.luizcarlos.api.dtos.securityDTO.RegisterDTO;
import com.luizcarlos.api.repository.UserRepository;
import com.luizcarlos.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping("/logar")
    public ResponseEntity login(@RequestBody  AuthenticationDTO data){

        var userNameSenha = new UsernamePasswordAuthenticationToken(data.login(),data.senha());
        var auth = this.authenticationManager.authenticate(userNameSenha);

        var token = tokenService.geradorDeToken((Users) auth.getPrincipal());


        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody @Valid RegisterDTO data){

        if(this.userRepository.findByLogin(data.login()) != null){

            return ResponseEntity.badRequest().build();
        }else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
            Users newUser = new Users(data.login(), encryptedPassword,data.role());
            this.userRepository.save(newUser);
        }

        return ResponseEntity.ok().build();
    }


}
