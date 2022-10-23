package com.example.chuchu.common.jwt.repository;

import com.example.chuchu.common.jwt.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {
}
