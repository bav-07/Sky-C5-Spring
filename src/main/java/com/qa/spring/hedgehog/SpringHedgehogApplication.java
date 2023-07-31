package com.qa.spring.hedgehog;

import com.qa.spring.hedgehog.rest.HedgehogController;
import com.qa.spring.hedgehog.services.HedgehogService;
import com.qa.spring.hedgehog.services.HedgehogServiceDB;
import com.qa.spring.hedgehog.services.HedgehogServiceList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringHedgehogApplication {

    public static void main(String[] args) {
        ApplicationContext beanBag = SpringApplication.run(SpringHedgehogApplication.class, args);
		System.out.println(beanBag.getBean(HedgehogService.class));
		System.out.println(beanBag.getBean(HedgehogController.class));
	}

}
