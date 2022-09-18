package com.cloud.movie;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.common.base.web.Query;
import com.cloud.common.base.web.QueryVo;
import com.cloud.common.movie.dto.ResultDto;
import com.cloud.common.movie.po.Result;
import com.cloud.common.persist.util.QueryUtils;
import com.cloud.movie.service.ResultService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MovieApplicationTests {
    @Resource
    private ResultService resultService;

    @Test
    void contextLoads() {
    }

    @Test
    void add() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    Result result = new Result();
                    result.setTitle("我是" + i);
                    result.setMovieCountry("国家");
                    result.setYear(12L);
                    System.out.println(result);
                    resultService.save(result);
                }
            }
        }.start();
    }
}
