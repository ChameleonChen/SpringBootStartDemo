package demo.restdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController	// 体方面是控制器，另一方面让Spring完成数据序列化的工作
public class ProductDetailController {

	private final ProductDetailRepository repository;
	
	@Autowired	// 要求Spring容器自动注入依赖
	public ProductDetailController(ProductDetailRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * 在浏览器中输入链接，就属于GET类型。但是链接的长度有限，也就是GET方式携带的数据有限。
	 * 默认在浏览器中返回一个JSON数组。
	 */
	@RequestMapping(value="/products", method = RequestMethod.GET)
	public Iterable findAll() {
		return repository.findAll();
	}
	
	
	
	@RequestMapping(value="/product", method = RequestMethod.POST)
	public ProductDetail create(@RequestBody ProductDetail detail) {
		return repository.save(detail);
	}
}
