package com.luizcarlos.api.dtos.securityDTO;

import lombok.Data;


public record AuthenticationDTO(String login, String senha) {
}
