package demo.restdemo;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController	// 体方面是控制器，另一方面让Spring完成数据序列化的工作
public class ProductDetailController {

	private final ProductDetailRepository repository;
	private final ProductDetailValidator validator;
	
	@Autowired	// 要求Spring容器自动注入依赖
	public ProductDetailController(ProductDetailRepository repository, ProductDetailValidator validator) {
		this.repository = repository;
		this.validator = validator;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
		/*
		 * 一个Controller中可能会有多个方法需要对请求进行校验，
		 * 而不同求情对应的数据类型可能是不一样的，那么Controller怎么知道
		 * 如何为不同的方法分配不同的检验器呢？
		 * Validator中support方法指明了检验器处理的数据类型，这就帮助了
		 * Controller将检验器和请求函数一一对应起来。
		 */
		
		binder.addValidators(validator);
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
	public ProductDetail create(@RequestBody/*将POST请求的数据序列化*/ @Valid/*对数据进行校验*/ ProductDetail detail) {
		return repository.save(detail);
	}
}
