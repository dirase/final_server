package com.we.vpn.api.core;

import com.jfinal.render.JsonRender;
import com.jfinal.render.RenderException;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by swn on 2017-04-07.
 */
public class RestErrorRender extends JsonRender {

    private static final String contentType = "application/json; charset=" + getEncoding();
    private int errorCode;
    private String jsonText;

    public RestErrorRender(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.jsonText = "{\"error\":\"" + errorMsg + "\"}";
    }

    @Override
    public void render() {
        response.setStatus(errorCode);
        PrintWriter writer = null;
        try {
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            response.setContentType(contentType);
            writer = response.getWriter();
            writer.write(jsonText);
            writer.flush();
        } catch (IOException e) {
            throw new RenderException(e);
        }
        finally {
            if (writer != null)
                writer.close();
        }

    }
}
