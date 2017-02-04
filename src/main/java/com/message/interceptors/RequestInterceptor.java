package com.message.interceptors;

import com.message.context.XContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhenwei on 2015/5/20.
 */
public class RequestInterceptor extends XInterceptor {

    private final String REQUEST_LOG_PATTERN = "接口[%s]收到来自[%s]的请求[Hashcode=%s]，参数为:{%s}";

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    public RequestInterceptor(){
    }

    @Override
    public boolean internalPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        XContext xContext = XContext.getCurrentContext();

        String paramsString = xContext.getParamsString();

        logger.info(String.format(REQUEST_LOG_PATTERN, xContext.getServiceUrl(), xContext.getSource(), xContext.getRequestHashCode(), paramsString));

        return true;
    }

}
