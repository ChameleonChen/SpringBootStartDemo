package demo.restdemo;

import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class ProductDetailValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ProductDetail.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductDetail productDetail = (ProductDetail) target;
		if (isValidInventory(productDetail.getInventoryId())) {
			errors.rejectValue("inventoryId", "inventory.id.invalid", "Invalid inventory id");
		}
	}
	
	private static boolean isValidInventory(String inventory) {
		return (inventory.equals("invalid_inventoryid"));	// 当字符串的值为 invalid_inventoryid 时，验证失败
	}

}
