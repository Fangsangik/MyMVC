package mvc.my_mvc;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class MyMvcApplication {

	private static final Logger log = LoggerFactory.getLogger(MyMvcApplication.class);

	public static void main(String[] args) throws LifecycleException {
		SpringApplication.run(MyMvcApplication.class, args);

		String webapp = "webapps/"; //tomcat에 root directory를 webapps로 설정
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		//tomcat이 해당하는 webapps directroy에서 찾는다.
		tomcat.addWebapp("/", new File(webapp).getAbsolutePath());
		log.info("configuring app with basedir = {}" , new File("./" + webapp).getAbsolutePath());

		//이렇게 설정을 해야지만 tomcat이 해당 클래스를 실행할 수 있음
		//tomcat은 root directory 밑에 webapps\WEB-INF\classes에서 자바 클래스를 찾기 때문

		tomcat.start();
		tomcat.getServer().await();
	}
}
