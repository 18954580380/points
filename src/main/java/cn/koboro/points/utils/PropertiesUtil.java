package cn.koboro.points.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

    private static final Properties prop = new Properties();
    //ftp配置文件读取
    public static String getFtpProperties(){
        return "config/ftp.properties";
    }
    //配置文件读取
    private static String getConfigProperties() {
        return "application.yml";
    }
    //服务接口配置文件
    private static String getServiceInterfaceProperties() {
        return "config/serviceInterfaceUrl.properties";
    }
    /**
     * 读取服务接口配置文件获取值
     *
     * @return
     */
    public static String getServiceUrl(String key) {
        if (!prop.contains(key)) {
            ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
            try (InputStream ism = classLoader.getResourceAsStream(getServiceInterfaceProperties())) {
                prop.load(ism);
                prop.size();
            } catch (Exception e) {
                throw new IllegalAccessError("读取服务接口属性文件失败");
            }
        }
        return prop.getProperty(key);
    }
    /**
     * 读取配置文件获取值
     *
     * @return
     */
    public static String getProperties(String key) {
        if (!prop.contains(key)) {
            ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
            try (InputStream ism = classLoader.getResourceAsStream(getConfigProperties())) {
                prop.load(ism);
                prop.size();
            } catch (Exception e) {
                throw new IllegalAccessError("读取ftp属性文件失败");
            }
        }
        return prop.getProperty(key);
    }
    /**
     * 读取配置文件获取值
     * @return
     */
    public static Map<String,String> getPropertiesValue(String keyName[]) {
        Map<String,String>map=new HashMap<>();
        Properties prop = new Properties();
        try{
            ClassLoader classLoader=PropertiesUtil.class.getClassLoader();
            InputStream ism=classLoader.getResourceAsStream(getFtpProperties());
            prop.load(ism);
            for(String k:keyName){
                if(prop.containsKey(k)){
                    map.put(k, prop.getProperty(k));
                }else{
                    map.put(k, null);
                }
            }
            return map;
        }
        catch(Exception e){
            throw new IllegalAccessError("读取ftp属性文件失败");
        }

    }
}
