package demo;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

public class Demo {

    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
        String resource = "mybatisContext.xml";
    }
}
interface UserMapper {
    User selectUser(int id);
}

class User {
    private int id;
    private String username;
    private String password;
}