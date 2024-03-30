package com.luizcarlos.api.model.dtos;

import lombok.Data;


public record AuthenticationDTO(String login, String senha) {
}
