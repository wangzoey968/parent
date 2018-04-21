import com.it.model.Tb_User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by wangzy on 2018/4/18.
 */
public class SqlSessionFactoryTest {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;

    @Before
    public void init() throws IOException {
        String resource = "/config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @After
    public void destroy() throws Exception {
        session.commit();
        session.close();
    }

    @Test
    public void getUsers() {
        session = sqlSessionFactory.openSession();
        List<Tb_User> list = session.selectList("com.it.dao.UserDao.select");
    }
}
