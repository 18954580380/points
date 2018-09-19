package cn.koboro.points.utils;

//import cn.koboro.circle.conterver.TextMappingJackson2HttpMessageConverter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author xdw
 */
public class Connection {

    private Connection() {

    }

    private MultiValueMap params;

    private Connection(HttpMethod method) {
        this.method = method;
        this.params = new LinkedMultiValueMap<>();
    }

    private HttpMethod method;

    private static RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());

    static {
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        restTemplate.getMessageConverters().add(new TextMappingJackson2HttpMessageConverter()); //增加消息转换器 TEXT_PLAIN - JSON
    }

    public static Connection newPOST() {
        return new Connection(HttpMethod.POST);
    }

    public static Connection newGET() {
        return new Connection(HttpMethod.GET);
    }

    public <T> T send(String url) {
        return send(url, (Class<T>) String.class);
    }

    public <T> T send(String url, Class<T> responseType) {
        return send(url, new HttpHeaders() {{
            add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        }}, responseType);
    }

    public <T> T send(String url, HttpHeaders headers) {
        return (T) send(url, headers, String.class);
    }

    public <T> T send(String url, Object params, Class<T> responseType) {
        return send(url, params, new HttpHeaders(), responseType);
    }

    /**
     * @param url          请求地址
     * @param headers      请求头
     * @param responseType 返回类型
     * @param <T>
     * @return
     */
    public <T> T send(String url, HttpHeaders headers, Class<T> responseType) {
        return (T) restTemplate.exchange(url, method, new HttpEntity(params, headers), responseType).getBody();
    }

    /**
     * @param url          请求地址
     * @param params       请求参数
     * @param headers      请求头
     * @param responseType 返回类型
     */
    public <T> T send(String url, Object params, HttpHeaders headers, Class<T> responseType) {
        MediaType contentType = headers.getContentType();
        if (contentType == null) {
            headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        }
        return restTemplate.exchange(url, method, new HttpEntity(params, headers), responseType).getBody();
    }


    public Connection setParam(String key, String value) {
        params.set(key, value);
        return this;
    }

    public Connection setParam(Map map) {
        params.setAll(map);
        return this;
    }

}
