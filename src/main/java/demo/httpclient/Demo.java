package demo.httpclient;

import demo.restdemo.ProductDetail;

public class Demo {

	private RestHttpClient client;
	
	public Demo() {
		client = new RestHttpClient();
	}
	
	private void postToServerValidatorDemo() {
		ProductDetail detail = new ProductDetail();
		detail.setInventoryId("invalid_inventoryid");	// Validator
		detail.setLongDescription("test_longDescription");
		detail.setProductId("test_productId");
		detail.setProductName("test_productName");
		detail.setShortDescription("test_shortDescription");
		
		String result = client.doPostRequest("http://localhost:8080/product", detail);
		if (result == null) 
			System.out.println("postToServerValidatorDemo post 失败");
		else 
			System.out.println("postToServerValidatorDemo post 成功");
	}
	
	private void postToServerDemo() {
		ProductDetail detail = new ProductDetail();
		detail.setInventoryId("test_inventory");
		detail.setLongDescription("test_longDescription");
		detail.setProductId("test_productId");
		detail.setProductName("test_productName");
		detail.setShortDescription("test_shortDescription");
		
		String result = client.doPostRequest("http://localhost:8080/product", detail);
		if (result == null) 
			System.out.println("postToServerDemo post 失败");
		else 
			System.out.println("postToServerDemo post 成功");
	}
	
	/**
	 * 用例介绍：
	 * 服务器对POST数据中的inventoryId字段进行验证，如果字段的值为 invalid_inventoryid，
	 * 则POST失败，其他值则POST成功。
	 */
	public void postValidatorTestCase() {
		Demo demo = new Demo();
		demo.postToServerDemo();
		demo.postToServerValidatorDemo();
	}
	
	public static void main(String[] args) {
		
	}
}
