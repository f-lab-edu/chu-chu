package com.example.chuchu.common.utils;

import org.apache.commons.lang3.StringUtils;

public class ConfigUtils {

    // dev 환경 여부 체크
    public static final boolean IS_DEV;

    static {
        String profile = System.getProperty("spring.profiles.active");
        IS_DEV = StringUtils.equals(profile, "dev");
    }

}
