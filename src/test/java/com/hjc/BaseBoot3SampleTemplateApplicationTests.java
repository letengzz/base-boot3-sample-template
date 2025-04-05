package com.hjc;

import com.hjc.common.ResponseEntity;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseBoot3SampleTemplateApplicationTests {


    @Test
    void contextLoads() {
        String jsonString = ResponseEntity.success().asJsonString();
        System.out.println("jsonString = " + jsonString);
    }

}
