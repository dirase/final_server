package com.we.vpn.api.core;

import com.jfinal.render.IErrorRenderFactory;
import com.jfinal.render.Render;

/**
 * Created by swn on 2017-04-07.
 */
public class RestErrorRenderFactory implements IErrorRenderFactory {
    @Override
    public Render getRender(int errorCode, String errorMsg) {
        return new RestErrorRender(errorCode, errorMsg);
    }
}
