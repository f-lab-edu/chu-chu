package com.example.chuchu.member.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum UserRole {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static UserRole fromUserRole(String value) {
        return Stream.of(UserRole.values())
                .filter(userRole -> StringUtils.equals(userRole.getValue(), value))
                .findFirst()
                .orElse(null);
    }
}
