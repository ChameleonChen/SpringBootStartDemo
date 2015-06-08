package demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController	// 标志这是一个Controller
@EnableAutoConfiguration
public class SpringBootController {

	/*
	 * 这个路由直接返回一个JSON数组。
	 */
	@RequestMapping("/users")
	public User[] requestUsers() {
		User[] users = new User[10];
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setUserId("111111");
			user.setUserName("hehehe");
			users[i] = user;
		}
		
		return users;
	}
	
	/*
	 * 这个路由的效果是：在浏览器中返回一个表示User对象的Json字符串。
	 * 也就是说Spring默认提供Json支持。
	 */
	@RequestMapping("/user")
	public User requestUser() {
		User user = new User();
		user.setUserId("1234");
		user.setUserName("hehe");
		return user;
	}

	
	/*
	 * 这个路由的效果是：在浏览器中返回Hello World字符串。
	 */
	@RequestMapping("/hello")
	public String home() {
		return "Hello World";
	}
	
//	public static void main(String[] args) throws Exception {
//		/*
//		 * run() 能够返回一个上下文，提供应用程序环境配置和事件注册的接口。
//		 */
//		ApplicationContext context = SpringApplication.run(SpringBootController.class, args);
//		
//	}
	
}
