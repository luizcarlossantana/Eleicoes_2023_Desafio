package com.luizcarlos.api.dtos.securityDTO;

import com.luizcarlos.api.enumeration.UserRoles;

public record RegisterDTO(String login, String senha, UserRoles role) {
}
