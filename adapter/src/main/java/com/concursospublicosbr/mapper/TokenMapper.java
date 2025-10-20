package com.concursospublicosbr.mapper;

import com.concursospublicosbr.api.model.TokenRequestRepresentation;
import com.concursospublicosbr.domain.model.Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TokenMapper {

    @Mapping(source = "username", target = "usuario")
    @Mapping(source = "roles", target = "perfis")
    Token toToken(TokenRequestRepresentation tokenRequestRepresentation);

}
