package com.message.context;

/**
 * Created by hpj on 2015-06-18.
 */
public class XContextFactory {

    private static final String XPOS = "xpos";
    private static final String XGENERAL = "general";


    public static XContext createContext(String str) {
        switch (str) {
            case XPOS:
                return XContext.createContext();
            case XGENERAL:
                return XGeneralContext.createXGeneralContext();
            default:
                return XWebContext.createXWebContext();
        }

    }
}
