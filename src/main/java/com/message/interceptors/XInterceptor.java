package com.message.interceptors;

import com.message.context.XContext;
import com.message.context.XContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Zhenwei on 2015/5/20.
 */
public class XInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(XInterceptor.class);

    private List<XInterceptor> xInterceptorList;

    private List<String> ignoreList;

    public XInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        if(ignoreList != null && ignoreList.contains(request.getServletPath())){
            return true;
        }

        XContext xContext =  XContextFactory.createContext(request.getServletPath());

        xContext.init(request);

        if(null == xInterceptorList || xInterceptorList.size() == 0) {
            for(XInterceptor interceptor : xInterceptorList) {
                interceptor.internalPreHandle(request,response,handler);
            }
        }
        return true;
    }

    protected boolean internalPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    //在业务处理器处理请求执行完成后,生成视图之前执行的动作
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
    }

    protected void internalAfterCompletion(HttpServletResponse response, Exception e){
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
            throws Exception {
        XContext.clearCurrentContext();
    }

    public void setXInterceptorList(List<XInterceptor> xInterceptorList) {
        this.xInterceptorList = xInterceptorList;
    }

    public void setIgnoreList(List<String> ignoreList) {
        this.ignoreList = ignoreList;
    }

}