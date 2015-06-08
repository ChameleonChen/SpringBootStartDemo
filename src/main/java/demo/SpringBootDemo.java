package demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan	// 告诉Spring对项目的classpath进行扫描寻找组件
@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootDemo {

	public static void main(String[] args) throws Exception {
//		ApplicationContext context = SpringApplication.run(SpringBootDemo.class, args);
//		dealProductDetailData(context);
		
		mvcDemo(args);
	}
	
	/*
	 * 运行的时候需要数据库支持才行。这里链接了内存数据库。
	 */
	
	private static void dealProductDetailData(ApplicationContext context) {
		ProductDetail productDetail = new ProductDetail();
		productDetail.setProductId("12345");
		productDetail.setProductName("haha");
		productDetail.setShortDescription("haha yes");
		productDetail.setLongDescription("haha yes yes");
		productDetail.setInventoryId("72387423");
		
		// 保存product
		// 数据库需要进行配置，我现在用的时h2内存数据库
		ProductDetailRepository reqository = context.getBean(ProductDetailRepository.class);
		reqository.save(productDetail);
		
		// 检索product
		for (ProductDetail detail : reqository.findAll()) {
			System.out.println(detail.getProductId());
		}
	}
	
	private static void mvcDemo(String[] args) {
		SpringApplication.run(SpringBootDemo.class, args);
	}
}
