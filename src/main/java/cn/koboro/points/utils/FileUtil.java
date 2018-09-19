package cn.koboro.points.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * 文件工具类
 *
 * @author kingapex 2010-1-6下午02:14:41
 */
public class FileUtil {

    private FileUtil() {
    }

    public static InputStream getResourceAsStream(String resource) {
        String stripped = resource.startsWith("/") ? resource.substring(1)
                : resource;

        InputStream stream = null;
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        if (classLoader != null) {
            stream = classLoader.getResourceAsStream(stripped);
        }
        return stream;
    }

    public static String getStringByStream(InputStream in) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(in, "utf-8"))) {
            return br.lines()
                    .map(e -> e + System.getProperty("line.separator"))
                    .collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
