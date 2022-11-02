package com.example.chuchu.heart.controller;

import com.example.chuchu.common.global.HttpResponseEntity;
import com.example.chuchu.common.global.HttpResponseEntity.ResponseResult;
import com.example.chuchu.heart.dto.HeartRequestDTO;
import com.example.chuchu.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.chuchu.common.global.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chuchu/heart")
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    public ResponseResult<?> insert(@RequestBody @Valid HeartRequestDTO heartRequestDTO) throws Exception {
        heartService.insert(heartRequestDTO);
        return success();
    }

    @DeleteMapping
    public ResponseResult<?> delete(@RequestBody @Valid HeartRequestDTO heartRequestDTO) {
        heartService.delete(heartRequestDTO);
        return success();
    }

}
