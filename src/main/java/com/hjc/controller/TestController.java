package com.hjc.controller;

import com.hjc.common.ResponseEntity;
import com.hjc.domain.Test;
import com.hjc.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Tag(name = "测试接口")
public class TestController {

    @Resource
    private TestService testService;

    @Operation(summary = "测试接口")
    @GetMapping("/test")
    public ResponseEntity<List<Test>> test() {
        return ResponseEntity.success(testService.getBaseMapper().selectList(null));
    }
}
