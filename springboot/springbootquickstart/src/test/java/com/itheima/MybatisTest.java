package com.itheima;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringBootApplication.class)
public class MybatisTest {
    @Resource
    private UserMapper userMapper;
    @Test
    public void test(){
        List<User> users = userMapper.queryUserList();
        System.out.println(users);
    }
}
