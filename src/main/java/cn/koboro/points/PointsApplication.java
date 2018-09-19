package cn.koboro.points;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;
@EnableScheduling
@ServletComponentScan
@SpringBootApplication
@MapperScan(basePackages = "cn.koboro.points.repository")
public class PointsApplication  extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        builder.sources(PointsApplication.class);
        builder.run(args);
    }
}
