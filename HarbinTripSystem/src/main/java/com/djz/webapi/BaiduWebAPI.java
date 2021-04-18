package com.djz.webapi;

/**
 * 百度web GPS定位(经纬度)
 * 注册账号及配置地址
 * http://lbsyun.baidu.com/apiconsole/key
 * 主类 BaiduWebAPI
 *
 * @author djz
 * @date 2021/4/2 -6:08
 */

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.djz.utils.JsonUtils;
import com.djz.utils.StringUtils;
import org.apache.log4j.Logger;


public class BaiduWebAPI {

    static Logger logger = Logger.getLogger(BaiduWebAPI.class);

    private static final String APP_ID = "18**********";
    private static final String AK = "eiEaGfbDnXawr0wpgInM10gkc1wDRPz4";

    public static void main(String[] args) {
        BaiduWebAPI.ipLocation("127.0.0.1");
        BaiduWebAPI.gpsLocation("116.840213", "39.196272");
    }

    // GPS接口
    public static String gpsLocation(String lng, String lat) {
        String result = null;
        try {
            String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=eiEaGfbDnXawr0wpgInM10gkc1wDRPz4&output=json&coordtype=wgs84ll&location=LAT_VALUE,LNG_VALUE";
            url = url.replace("MY_AK", AK).replace("LNG_VALUE", lng).replace("LAT_VALUE", lat);
            String reqResult = HttpClient.doGet(url);
            System.out.println(reqResult);
            Map map = JsonUtils.parseJSON2Map(reqResult);
            Map ac = (Map) ((Map) map.get("result")).get("addressComponent");
            result = ac.get("city").toString() + ac.get("district").toString();
        } catch (Exception e) {
            logger.error("GPS接口异常：", e);
        }
        logger.info("GPS接口：{lng:" + lng + ",lat:" + lat + ",result:" + result + "}");
        return result;
    }

    // IP接口
    public static String ipLocation(String ip) {
        if (BaiduWebAPI.isLan(ip)) {
            return "内网IP";

        }
        String result = null;
        try {
            String url = "http://api.map.baidu.com/location/ip?ak=MY_AK&ip=IP_VALUE&coor=bd09ll";
            url = url.replace("MY_AK", AK).replace("IP_VALUE", ip);
            String reqResult = decodeUnicode(HttpClient.doGet(url));
            System.out.println(reqResult);
            Map map = JsonUtils.parseJSON2Map(reqResult);
            result = ((Map) map.get("content")).get("address").toString();
            result = result.replace("省", "").replace("市", "");

        } catch (Exception e) {
            logger.error("IP接口异常：", e);

        }
        logger.info("IP接口：{ip:" + ip + ",result:" + result + "}");
        return result;
    }

    // unicode转化汉字
    public static String decodeUnicode(final String unicode) {
        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 0; i < hex.length; i++) {
            try {
                // 汉字范围 \u4e00-\u9fa5 (中文)
                // 取前四个，判断是否是汉字
                if (hex[i].length() >= 4) {

                    String chinese = hex[i].substring(0, 4);

                    try {
                        int chr = Integer.parseInt(chinese, 16);
                        boolean isChinese = isChinese((char) chr);

                        // 转化成功，判断是否在 汉字范围内
                        // 在汉字范围内
                        if (isChinese) {
                            // 追加成string
                            string.append((char) chr);
                            // 并且追加 后面的字符
                            String behindString = hex[i].substring(4);

                            string.append(behindString);

                        } else {
                            string.append(hex[i]);

                        }

                    } catch (NumberFormatException e1) {
                        string.append(hex[i]);
                    }
                } else {
                    string.append(hex[i]);
                }
            } catch (NumberFormatException e) {
                string.append(hex[i]);
            }
        }
        return string.toString();
    }

    /**
     * 判断是否为中文字符
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION

                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;

        }
        return false;
    }

    // 是否为局域网
    private static Boolean isLan(String ip) {
        if ("127.0.0.1".equals(ip)) {
            return true;

        }
        if (!StringUtils.isEmpty(ip) && ip.length() > 15) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        /*
         * 判断客户单IP地址是否为内网地址
         * 内网IP网段：
         * 10.0.0.0-10.255.255.255
         * 172.16.0.0-172.31.255.255
         * 192.168.0.0-192.168.255.255
         */
        String reg = "^(192\\.168|172\\.(1[6-9]|2\\d|3[0,1]))(\\.(2[0-4]\\d|25[0-5]|[0,1]?\\d?\\d)){2}$|^10(\\.([2][0-4]\\d|25[0-5]|[0,1]?\\d?\\d)){3}$";
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(ip);
        return matcher.find();
    }
}



