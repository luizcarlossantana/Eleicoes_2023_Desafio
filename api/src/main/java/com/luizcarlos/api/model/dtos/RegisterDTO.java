package com.luizcarlos.api.model.dtos;

import com.luizcarlos.api.enumeration.UserRoles;

public record RegisterDTO(String login, String senha, UserRoles role) {
}
