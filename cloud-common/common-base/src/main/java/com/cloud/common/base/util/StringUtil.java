package com.cloud.common.base.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fangcy
 * @date 2022-09-18
 */
public class StringUtil extends StringUtils {
    private static final String mixStr = "\\*";

    public StringUtil() {
    }

    public static String humpToUnderline(String str) {
        String regex = "([A-Z])";

        String target;
        for(Matcher matcher = Pattern.compile(regex).matcher(str); matcher.find(); str = str.replaceAll(target, "_" + target.toLowerCase())) {
            target = matcher.group();
        }

        return str;
    }

    public static String underlineToHump(String str) {
        String regex = "_(.)";

        String target;
        for(Matcher matcher = Pattern.compile(regex).matcher(str); matcher.find(); str = str.replaceAll("_" + target, target.toUpperCase())) {
            target = matcher.group(1);
        }

        return str;
    }
    public static String removeSuffix(String str, String suffix) {
        if (!isEmpty(str) && !isEmpty(suffix)) {
            return str.endsWith(suffix.toString()) ? str.substring(0, str.length() - suffix.length()) : str;
        } else {
            return "";
        }
    }
}
