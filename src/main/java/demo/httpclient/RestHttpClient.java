package demo.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class RestHttpClient {
	public void echoErroInfo(String info) {
		// 实现这个函数来打印错误信息
	}
	
	/**
	 * 每次发起HTTP请求之后都会返回{@link HttpResponse},可以从response中获取{@link HttpEntity}，
	 * 解析entity中的字符内容，可以调用此方法。
	 * @param entity	需要解析的Entity
	 * @return	
	 */
	private String getContentFromHttpEntity(HttpEntity entity) {
		if (null == entity)
			throw new IllegalArgumentException();
		
		try {
			// 获取返回内容
			BufferedReader buffer = new BufferedReader(
					new InputStreamReader(entity.getContent(),	/*内容的输入流*/
					EntityUtils.getContentCharSet(entity)),		/*内容的编码*/
					4*1024	/*缓存的大小*/);
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = buffer.readLine()) != null) {
				stringBuilder.append(line);
			}
			buffer.close();
			return stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 采用RESTful，用HTTP GET请求方式想服务器获取一个字符串。
	 * @param url	服务器地址
	 * @return	JSON格式的字符串
	 */
	public String doGetRequest(String url) {
		if (url == null)
			throw new IllegalArgumentException("url can not be null");
		
		String result = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			
			if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				result = getContentFromHttpEntity(entity);			
			} else {	// 连接失败
				return null;
			}
			
			httpClient.close();		// 关闭HTTP连接
			
		} catch (IOException e) {
			e.printStackTrace();
			echoErroInfo("对地址 " + url + " 执行GET请求失败");
			return null;
		}
		
		return result;
	}
	
	/**
	 * 如果JSON字符串表示数组，则返回true。
	 * @param jsonString
	 * @return
	 */
	private boolean isJsonArray(String jsonString) {
		char fistChar = jsonString.charAt(0);
		if (fistChar == '[')
			return true;
		else
			return false;
	}
	
	/**
	 * 在REST规范中，服务器经常返回一个JSON格式的字符串。客户端并不知道服务器返回的JSON字符串
	 * 是表示单个对象还是一个数组，这就给解析造成麻烦。这次统一将JSON字符串解析成对象数组，如果
	 * JSON字符串表示单个对象则解析出来的数组里只有一个元素。
	 * @param jsonString	被解析的字符串
	 * @param clazz		转换的类型
	 * @return
	 */
	private <T> List<T> jsonString2Objects(String jsonString, Class<T> clazz) {
		if (null == jsonString || null == clazz)
			throw new IllegalArgumentException();
		
		List<T> objs = null;
		
		if (isJsonArray(jsonString)) {
			objs = JSON.parseArray(jsonString, clazz);
		} else {
			objs = new ArrayList<T>(1);
			T obj = JSON.parseObject(jsonString, clazz);
			objs.add(obj);
		}
		
		return objs;
	}
	
	/**
	 * 采用RESTful，向服务器求情一个JSON字符串，并把字符串转化成数组对象返回。
	 * @see #doGetRequest(String)
	 * @see #jsonString2Objects(String, Class)
	 * @param url	服务器地址
	 * @param clazz	对象的类型
	 * @return
	 */
	public <T> List<T> doGetRequest(String url, Class<T> clazz) {
		if (null == clazz || null == url)
			throw new IllegalArgumentException();
		
		String jsonString = doGetRequest(url);
		return jsonString2Objects(jsonString, clazz);
	}
	
	/**
	 * 采用RESTful规范，用HTTP POST方式向服务器提交JSON字符串
	 * @param url
	 * @param postContent
	 * @return
	 */
	public String doPostRequest(String url, String postContent) {
		if (null == url || null == postContent)
			throw new IllegalArgumentException();
		
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		try {
			StringEntity se = new StringEntity(postContent);
			se.setContentEncoding("UTF-8");
			se.setContentType("application/json");
			httpPost.setEntity(se);
			
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				result = getContentFromHttpEntity(entity);
				httpClient.close();
				return result;
			} else {
				echoErroInfo("对地址 " + url + " 执行POST请求失败");
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			echoErroInfo("对地址 " + url + " 执行POST请求失败");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			echoErroInfo("对地址 " + url + " 执行POST请求失败");
			return null;
		}
	}
	
	/**
	 * 采用RESTful规范，用HTTP POST方式向服务器提交obj对象
	 * @param url	服务器地址
	 * @param obj	提交对象
	 * @return
	 */
	public String doPostRequest(String url, Object obj) {
		if (null == url || null == obj)
			throw new IllegalArgumentException();
		
		String jsonString = JSON.toJSONString(obj);
		return doPostRequest(url, jsonString);
	}
	
}
