import com.it.model.Tb_User;
import com.it.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangzy on 2018/4/21.
 */
public class ReadXmlTest {

    @Test
    public void ss(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        Tb_User tb_user = userService.selectUserRole(1);
        System.out.println(tb_user.toString());
    }

}
