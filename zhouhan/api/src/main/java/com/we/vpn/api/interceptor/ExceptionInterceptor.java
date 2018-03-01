package com.we.vpn.api.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.ActionException;
import com.jfinal.kit.LogKit;

/**
 * Created by jay on 2017/3/21.
 */
public class ExceptionInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation ai) {
        try {
            ai.invoke();
        } catch (ActionException e) {
            throw e;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder().append("action: ").append(ai.getActionKey())
                    .append(", e: ").append(e.toString()).append(", ").append(e.getStackTrace()[0].toString());
            LogKit.fatal(sb.toString());
        }
    }

}
