package com.example.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootTest
class SpringbootDataJdbcApplicationTests {

	//DI注入数据源
	@Autowired
	DataSource dataSource;

	@Autowired
	JavaMailSenderImpl javaMailSender;

	@Test
	public void contextLoads() throws SQLException {
		//看一下默认数据源
		System.out.println(dataSource.getClass());
		//获得连接
		Connection connection =   dataSource.getConnection();
		System.out.println(connection);

//		DruidDataSource druidDataSource = (DruidDataSource) dataSource;
//		System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
//		System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
		//关闭连接
		connection.close();
	}

	@Test
	public void contextLoads_1(){
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setSubject("Simple测试——标题");
		mailMessage.setText("测试1");

		mailMessage.setTo("jklllh@163.com");
		mailMessage.setFrom("1405309321@qq.com");

		javaMailSender.send(mailMessage);
	}

	@Test
	public void contextLoads_2() throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

		helper.setSubject("Simple测试——标题");
		helper.setText("测试1");

		helper.addAttachment("",new File("C:\\Users\\14053\\Desktop\\debug.log"));

		helper.setTo("jklllh@163.com");
		helper.setFrom("1405309321@qq.com");

		javaMailSender.send(mimeMessage);
	}

}
