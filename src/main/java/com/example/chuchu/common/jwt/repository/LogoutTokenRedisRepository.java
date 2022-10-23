package com.example.chuchu.common.jwt.repository;

import com.example.chuchu.common.jwt.entity.LogoutToken;
import org.springframework.data.repository.CrudRepository;


public interface LogoutTokenRedisRepository extends CrudRepository<LogoutToken, String> {

}
