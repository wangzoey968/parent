import com.it.service.UserService;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by wangzy on 2018/4/21.
 */
public class ReadXmlTest {

    @Test
    public void ss() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        Socket socket = new Socket();
        socket.setSoTimeout(1000);
        boolean b = socket.isConnected();
        InputStream in = socket.getInputStream();

        ServerSocket server = new ServerSocket();
        Socket incoming = server.accept();
        InputStream inputStream = incoming.getInputStream();
        Scanner i = new Scanner(inputStream);
        while (i.hasNextLine()) {
            System.out.println(i.nextLine());
        }
    }

    @Test
    public void s1() throws Exception {
        Locale china = Locale.CHINA;
        Collator collator = Collator.getInstance(china);
        if (collator.compare("a", "A") < 0) {
            //类加载器
            Thread thread = Thread.currentThread();
            ClassLoader loader = thread.getContextClassLoader();
            Class user = loader.loadClass("com.it.model.Tb_User");
        }

        //比较,通过使用collator,locale
        new Comparator<Locale>() {
            Collator c = Collator.getInstance(Locale.getDefault());
            @Override
            public int compare(Locale o1, Locale o2) {
                return c.compare(o1, o2);
            }
        };


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("this is title");
            }
        });

    }

}
