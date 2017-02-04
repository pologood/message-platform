package com.message.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.message.output.Result;
import com.message.util.ResponseUtils;
import com.message.util.SignType;
import com.message.util.SignUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Created by hpj on 2015/5/20.
 */
public class XMessageConverter extends AbstractHttpMessageConverter<Object> {

    private final String NORMAL_LOG_PATTERN = ">>>[HashCode=%s]请求,返回给[%s]数据:{%s}";
    private final String ERROR_LOG_PATTERN = ">>>[HashCode=%s]请求[%s]发生异常:%s";


    public final static Charset UTF8 = Charset.forName("UTF-8");
    private static final Logger logger = LoggerFactory.getLogger(XMessageConverter.class);

    private Charset charset = UTF8;

    private SerializerFeature[] serializerFeature;

    public XMessageConverter(String charset) {
        super(new MediaType("application", "json", Charset.forName(charset)), new MediaType("application", "*+json", Charset.forName(charset)));
        this.charset = Charset.forName(charset);
    }

    public XMessageConverter() {
        super(new MediaType("application", "json", UTF8), new MediaType("application", "*+json", UTF8));
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public SerializerFeature[] getFeatures() {
        return serializerFeature;
    }

    public void setFeatures(SerializerFeature... features) {
        this.serializerFeature = features;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        XContext context = XContext.getCurrentContext();
        /**判断HttpServletRequest是否初始化。*/
        String jsonBody =null;
        if(context.isInited() && context.isOpenApiRequest()){
            jsonBody = context.getPostJsonBodyStr();
        }else{
            jsonBody = IOUtils.toString(inputMessage.getBody(), "UTF-8");
        }
        if (StringUtils.isEmpty(jsonBody)) {
            jsonBody = "{}";
        }

        return JSON.parseObject(jsonBody, clazz);
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        XContext context = XContext.getCurrentContext();
        byte[] jsonBytes ;
        /**判断HttpServletRequest是否初始化。*/
        if(context.isInited() && context.isOpenApiRequest()){
           Result result;
           try {
               logger.info(String.format(NORMAL_LOG_PATTERN, context.getRequestHashCode(), context.getSource(), JSON.toJSONString(obj)));
               result = ResponseUtils.getSuccessResult(obj);
           } catch (Exception ex) {
               result = ResponseUtils.getUnknownResult();
               String errorLog = String.format(ERROR_LOG_PATTERN, context.getRequestHashCode(), context.getServiceUrl(), ex.getMessage());
               logger.error(errorLog, ex);
           }
           jsonBytes = convertToJsonBytes(result);
           setSignToHeader(outputMessage, jsonBytes, context);
        }else{
            jsonBytes = convertToJsonBytes(obj);
        }
        OutputStream out = outputMessage.getBody();
        out.write(jsonBytes);
    }

    private byte[] convertToJsonBytes(Object obj) {
        if (null == obj) {
            return "\"\"".getBytes(charset);
        } else if (obj instanceof String) {
            String value = (String) obj;
            if (value.equals(StringUtils.EMPTY))
                return "\"\"".getBytes(charset);
            else
                return value.getBytes(charset);
        } else {
            if (serializerFeature != null) {
                return JSON.toJSONBytes(obj, serializerFeature);
            } else {
                return JSON.toJSONBytes(obj);
            }
        }
    }

    /**
     * 设置签名相关的http头
     *
     * @param outputMessage
     * @param jsonBytes
     * @param context
     */
    private void setSignToHeader(HttpOutputMessage outputMessage, byte[] jsonBytes, XContext context) {
        try {
            if (!context.isSkipCheckSignature()) {
                String sign = getResponseSign(jsonBytes, context);
                outputMessage.getHeaders().add("sign", sign);
                outputMessage.getHeaders().add("signType", context.getSignType().getValue());
            }
        } catch (Exception e) {
            //do nothing
        }

    }

    /**
     * response返回的签名
     *
     * @return
     */
    private String getResponseSign(byte[] jsonBytes, XContext context) {
        if (jsonBytes == null) {
            throw new RuntimeException();
        }

        SignType type = context.getSignType();
        String appId = context.getParameterMap().get("appId");
//        SecretService secretService = ApplicationContextHelper.getContext().getBean(SecretService.class);
//        Secret secret = secretService.getSecret(appId, type);
        String jsonStr = "data=" + new String(jsonBytes, charset);

        return SignUtils.sign(jsonStr, type, "","");
    }
}