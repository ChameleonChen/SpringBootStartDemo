package demo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data 中提供了一种便捷的抽象，也就是“repository”的概念，它本质上是一种对数据库的访问对象（DAO），
 * 该对象在启动时由框架为我们自动装配。
 * 这个接口支持实现ProductDetail实体的CRUD功能。
 * 
 * 虽然这只是一个接口，但是Spring能够实现该对象，并且作为bean为需要的地方注入依赖。
 * 
 */

@Repository	// 告诉Spring，这个类的作用就是DAO
public interface ProductDetailRepository extends
		CrudRepository<ProductDetail, String> {

}
