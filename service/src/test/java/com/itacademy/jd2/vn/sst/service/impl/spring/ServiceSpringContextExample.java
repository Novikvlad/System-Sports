package com.itacademy.jd2.vn.sst.service.impl.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itacademy.jd2.vn.sst.dao.api.IPartnerDao;

public class ServiceSpringContextExample {
   
	private static final Logger LOGGER=LoggerFactory.getLogger(ServiceSpringContextExample.class);
	
	public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dao-context.xml");
        LOGGER.info(context.getBean(IPartnerDao.class).toString());
        
        //TODO show multiple candidates with Qualifier


    }
}
