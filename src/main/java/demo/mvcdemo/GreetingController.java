package demo.mvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	/**
	 * 此路由能够返回一个View（HTML）.
	 * 当URL:localhost:8080 ，返回 HelloWorld
	 * 当URL:localhost:8080?name=ChameleonChen ，返回 HelloChameleonChen.
	 * <p>
	 * greeting.html的位置为 src/main/resources/templates/greeting.html，
	 * 使用到Thymeleaf模板引擎对HTML文件进行处理。
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";	// 与 greeting.html 对应
	}
}
