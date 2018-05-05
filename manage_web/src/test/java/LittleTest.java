import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.junit.Test;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * Created by wangzy on 2018/5/5.
 */
public class LittleTest {

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
