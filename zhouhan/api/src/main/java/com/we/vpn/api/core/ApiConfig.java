/**
 * Copyright (c) 2013-2016, Jieven. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.we.vpn.api.core;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.we.vpn.api.controller.IndexController;
import com.we.vpn.api.interceptor.ExceptionInterceptor;

import java.sql.Connection;

public class ApiConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        loadPropertyFile("context.cfg");
        me.setDevMode(getPropertyToBoolean("devMode", false));
        me.setEncoding(getProperty("encoding", "UTF-8"));
        me.setErrorRenderFactory(new RestErrorRenderFactory());
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
    }

    @Override
    public void configPlugin(Plugins plugins) {

        String url = getProperty("jdbc.url");
        DruidPlugin dp = new DruidPlugin(url,
            getProperty("jdbc.user"), getProperty("jdbc.password"),
            getProperty("jdbc.driver"));
        dp.addFilter(new StatFilter());
        dp.addFilter(new WallFilter());
        plugins.add(dp);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setTransactionLevel(Connection.TRANSACTION_REPEATABLE_READ);
        arp.setShowSql(getPropertyToBoolean("jdbc.showSql", false));
        plugins.add(arp);
        System.err.println("configPlugin finish...");
//            plugins.add(new EhCachePlugin());
//            EhCachePlugin ehCachePlugin = new EhCachePlugin(PathKit.getRootClassPath() + "/ehcache.xml");
//            me.add(ehCachePlugin);

//            QuartzPlugin quartzPlugin = new QuartzPlugin("quartz.cfg");
//            quartzPlugin.version("2");
//            me.add(quartzPlugin);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new ExceptionInterceptor());
    }

    @Override
    public void configHandler(Handlers me) {
//        DruidStatViewHandler dvh = new DruidStatViewHandler("/druid");
//        me.add(dvh);
    }
}