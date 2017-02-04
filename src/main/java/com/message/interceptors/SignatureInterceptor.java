package com.message.interceptors;

import com.message.context.XContext;
import com.message.util.DateUtils;
import com.message.util.SignType;
import com.message.util.SignUtils;
import org.joda.time.DateTime;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class SignatureInterceptor extends XInterceptor {
    private Long EXPIRED_MILLSECONDS = 300L * 1000L;

    private List<String> ignoreList;

    private boolean skipCheckTime = false;
    private boolean skipCheckSignature = false;

    public void setIgnoreList(List<String> ignoreList) {
        this.ignoreList = ignoreList;
    }

    public void setSkipCheckTime(boolean skipCheckTime) {
        this.skipCheckTime = skipCheckTime;
    }

    public void setSkipCheckSignature(boolean skipCheckSignature) {
        this.skipCheckSignature = skipCheckSignature;
    }

    @Override
    public boolean internalPreHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if(skipCheckSignature){
            XContext.getCurrentContext().setSkipCheckSignature(skipCheckSignature);
            return true;
        }

        if(requestCanBeIgnored(httpServletRequest)){
            return true;
        }

        XContext context = XContext.getCurrentContext();
        if(!skipCheckTime) {
            checkUpTimestamp(context.getParameterMap().get("timestamp"));  //判断请求是否超过5分钟
        }

        String appId = context.getParameterMap().get("appId");

        SignType type = context.getSignType();
//        SecretService secretService = ApplicationContextHelper.getContext().getBean(SecretService.class);
//        Secret secret = secretService.getSecret(appId, type);
        String path = context.getServiceUrl();

        //String clientSignature = httpServletRequest.getParameter("sign");
        String clientSignature = httpServletRequest.getHeader("sign");

        Map<String, String> signParamMap = new HashMap();
        if(context.isPostRequest()){
            // this url is servlet path
            signParamMap.put("data", context.getPostJsonBodyStr());
            signParamMap.put("path", path);
            if(!SignUtils.validateSignature(clientSignature, signParamMap, type, "","")){
                throw new RuntimeException();
            }
        } else {
            if(!SignUtils.validateSignature(clientSignature, path, type, "","")){
                throw new RuntimeException();
            }
        }

        return true;
    }

    private void checkUpTimestamp(String requestTimestamp) {
        DateTime requestTimestampDate = new DateTime(Long.valueOf(requestTimestamp));

        Long betweenSeconds = DateUtils.getMillSecondsBetween(new DateTime(), requestTimestampDate);
        if (Math.abs(betweenSeconds) > EXPIRED_MILLSECONDS) {
            throw new RuntimeException();
        }
    }

    public boolean requestCanBeIgnored(HttpServletRequest httpServletRequest){
        String servletPath = httpServletRequest.getServletPath();

        if(ignoreList != null && ignoreList.contains(servletPath)){
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
