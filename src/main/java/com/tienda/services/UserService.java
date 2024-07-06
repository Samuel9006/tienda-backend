package com.tienda.services;

import com.tienda.domain.dtos.UserRequestDto;
import com.tienda.domain.dtos.UserResponseDto;
import com.tienda.domain.entities.UsuarioEntity;
import com.tienda.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UserResponseDto register(UserRequestDto userRequestDto) {

        //Se arma un objeto entidad para almacenar en la bd
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsername(userRequestDto.getUsername());
        usuario.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        usuario.setRole(userRequestDto.getRole());

        //Se hace el guardado en la base de datos
        UsuarioEntity usuarioGuardado = this.usuarioRepository.save(usuario);

        //Se arma la respuesta para pasarla al controlador
        UserResponseDto respuesta = new UserResponseDto();
        respuesta.setUsername(usuarioGuardado.getUsername());
        respuesta.setRole(usuarioGuardado.getRole().name());
        return respuesta;
    }
}
