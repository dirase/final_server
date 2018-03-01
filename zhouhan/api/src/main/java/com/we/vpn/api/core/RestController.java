package com.we.vpn.api.core;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;

/**
 * Created by swn on 2017-04-07.
 */
public class RestController extends Controller {

//    protected void renderRest(int errorCode, String errorMsg) {
//        getResponse().setStatus(errorCode);
//        renderJson("error", errorMsg);
//    }

    public void renderRet(Ret ret) {
        renderJson(ret.getData());
    }
}
