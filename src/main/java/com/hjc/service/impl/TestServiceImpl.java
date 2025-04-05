package com.hjc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.domain.Test;
import com.hjc.service.TestService;
import com.hjc.mapper.TestMapper;
import org.springframework.stereotype.Service;

/**
* @author hjc
* @description 针对表【test】的数据库操作Service实现
* @createDate 2025-04-04 23:18:01
*/
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test>
    implements TestService{

}




