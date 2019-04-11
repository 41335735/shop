package cn.icloudit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
@MapperScan("cn.icloudit.dao")
public class MenberServiceMain {

	public static void main(String[] args) {
		SpringApplication.run(MenberServiceMain.class, args);
	} 
}
