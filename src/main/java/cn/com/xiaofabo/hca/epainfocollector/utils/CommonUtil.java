package cn.com.xiaofabo.hca.epainfocollector.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    public static boolean isNumber(String str) {
        if (str == null)
            return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(str).matches();
    }

    public static int fixPageNumber(String str){
        List<String> strList = numberList(str);
        if (strList.size() == 1){
            str = strList.get(0);
        }

        if (str.contains("/")){
            String[] strArr = str.split("/");
            str = numberList(strArr[1]).get(0);
        }

        return Integer.parseInt(str);
    }

    public static List<String> numberList(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    public static String getNumber(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    public static void main(String[] args) {
        String content = "满39元减2元";
        //正则表达式，用于匹配非数字串，+号用于匹配出多个非数字串

        System.out.println(JSON.toJSONString(numberList(content)));
    }

}
