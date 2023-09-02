package com.evantagesoft.util;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.jackrabbit.core.jndi.BindableRepositoryFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author Nand Khatri
 * @version 1.0
 * @date 3/3/2021
 */
public class AppContext implements ApplicationContextAware {

    private static ApplicationContext context;
    public static Session jcrSession;
    public static ApplicationContext getContext() {
        return context;
    }
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        System.out.println("Initializing Context");
        AppContext.context = context;
        try {
            InitialContext ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            Repository repo = (Repository) env.lookup("jcr/repository");
            AppContext.jcrSession = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
            System.out.println(jcrSession.getRootNode().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
