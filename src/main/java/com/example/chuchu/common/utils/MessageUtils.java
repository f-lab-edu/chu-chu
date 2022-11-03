package com.example.chuchu.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Locale;

@Component
public class MessageUtils {

    @Resource
    private MessageSource source;
    private static MessageSource messageSource;

    @PostConstruct
    public void initialize() {
        messageSource = source;
    }

    public static String getMessage(String code, Object[] arg, Locale locale) {
        return messageSource.getMessage(code, arg, locale);
    }

    public static String getMessage(String code, Object[] arg) {
        return getMessage(code, arg, Locale.getDefault());
    }

    public static String getMessage(String code) {
        return getMessage(code, new Object[]{}, Locale.getDefault());
    }
}
