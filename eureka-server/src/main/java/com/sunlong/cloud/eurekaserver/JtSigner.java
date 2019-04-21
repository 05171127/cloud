package com.sunlong.cloud.eurekaserver;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 内部系统接口调用签名，避免非法调用
 * 签名用方法signature，验签用checkSign
 * @author : shipp
 * @data : 2018/11/12 9:30
 */
@Component
public class JtSigner {

    // hex digits
    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    // default key
    @Value("${interface.secure.key}")
    private String defaultKey = "E867D86EFAC8404E920C09A49DCB994D";

    public static void main(String[] args) throws IllegalAccessException {
//        Map<String,Object> billInfoMap = new HashMap<>();
//        billInfoMap.put("userId","998");
//
//        System.out.println(JSON.toJSONString(billInfoMap));
//        Map<String, String> map = new HashMap<>();
//        map.put("agency","flight");
//        map.put("amt","100");
//        map.put("authProcessUrl","http://172.30.3.141:8081/flight/order/authProcess");
//        map.put("billInfo","{\"userId\":\"998\"}");
//        map.put("currency","CNY");
//        map.put("orderId","1118052670100501");
//        map.put("orderSource","0");
//        map.put("paymentTimeLimit","2018-12-24 20:13:23");
//        map.put("returnUrl","http://172.30.3.141/jollytravel/index_en.html#!/hotel/booking-itinerary.html?lang=0&orderId=1118052670100501");
//
//        CheckoutParameterVo vo = new CheckoutParameterVo();
//        vo.setAgency("flight");
//        vo.setAmt(new BigDecimal(100));
//        vo.setAuthProcessUrl("http://172.30.3.141:8081/flight/order/authProcess");
//        vo.setBillInfo("{\"userId\":\"998\"}");
//        vo.setCurrency("CNY");
//        vo.setOrderId("1118052670100501");
//        vo.setOrderSource("0");
//        vo.setPaymentTimeLimit("2018-12-24 20:13:23");
//        vo.setReturnUrl("http://172.30.3.141/jollytravel/index_en.html#!/hotel/booking-itinerary.html?lang=0&orderId=1118052670100501");
//
//
//
//        JtSigner jtSigner = new JtSigner();
//        String sign = jtSigner.generateSignature(map, jtSigner.defaultKey);
//        System.out.println(sign);
//
//        jtSigner.sign(vo);
//        System.out.println(vo.getSign());
//        System.out.println("aa");
//        float f = 69.02f;
////        BigDecimal b = BigDecimal.valueOf(f);
//        BigDecimal b = new BigDecimal(Float.toString(f));
//
//        BigDecimal c = b.multiply(new BigDecimal(100));
//
//        System.out.println(c.toString());
//        test2();
        System.out.println(7 | 9);
        System.out.println(7 & 9);
    }

    public static void test1() throws IllegalAccessException {
        JtSigner signer = new JtSigner();
//        SignTest test = new SignTest();
//        test.setAa("aaa");
//        test.setBb("bbb");
//        test.setCc("ccc");
//        test.setSign("E6F41CBE3BD1E7C97197CE1E016D7B31");
//        System.out.println(signer.checkObjSign(test));
//        signer.sign(test);
//        System.out.println(test.getSign());
//
//        Map<String, String> map = new HashMap<>();
//        map.put("aa","aaa");
//        map.put("bb","bbb");
//        map.put("cc","ccc");
//        System.out.println(signer.generateSignature(map, signer.defaultKey));

        Map<String, String> map = new HashMap<>();
        map.put("agency","hotel");
        map.put("amt","71.00");
        map.put("authProcessUrl","http://172.30.3.149:8082/hotel/hotelBooking/updateOrderStatusPaying?orderNo=1219020170112027");
        map.put("billInfo","{\"userId\":53}");
        map.put("currency","USD");
        map.put("orderId","1219020170112027");
        map.put("orderSource","1");
        map.put("paymentTimeLimit","2019-02-01 17:40:37");
        map.put("returnUrl","http://172.30.3.141/jollytravel/#!/member/order-detail-hotel.html?frm=2&lang=0&currency=USD&appVersion=4.19&countryCode=undefined&prevpage=itinerary&id=1219020170112027");

        System.out.println(signer.generateSignature(map, signer.defaultKey));
        String json = "{\"agency\":\"hotel\",\"amt\":71,\"authProcessUrl\":\"http://172.30.3.149:8082/hotel/hotelBooking/updateOrderStatusPaying?orderNo=1219020170112027&sign=E357DB16178A412D6F86EA61945CE626\",\"billInfo\":\"{\\\"userId\\\":53}\",\"currency\":\"USD\",\"orderId\":\"1219020170112027\",\"orderSource\":\"1\",\"paymentTimeLimit\":\"2019-02-01 17:40:37\",\"returnUrl\":\"http://172.30.3.141/jollytravel/#!/member/order-detail-hotel.html?frm=2&lang=0&currency=USD&appVersion=4.19&countryCode=undefined&prevpage=itinerary&id=1219020170112027\",\"sign\":\"93878DCDB36F0EF2FC52691FE49B5AF7\"}";
        CheckoutParameterVo vo = JSON.parseObject(json, CheckoutParameterVo.class);
        signer.checkObjSign(vo);

    }

    public static void test2() throws IllegalAccessException {
        JtSigner signer = new JtSigner();

//        Map<String,Object> billInfoMap = new HashMap<>();
//        billInfoMap.put("userId",53);
//        System.out.println(JSON.toJSONString(billInfoMap));
        Map<String, String> map = new HashMap<>();
        map.put("agency","hotel");
        map.put("amt", "149.00");
        map.put("authProcessUrl","http://172.30.3.149:8082/hotel/hotelBooking/updateOrderStatusPaying?orderNo=1219021570112058&sign=08029CFFBD5FEA2F88E5078FC204D10E");
        map.put("billInfo","{\"userId\":53}");
        map.put("currency","USD");
        map.put("orderId","1219021570112058");
        map.put("orderSource","1");
        map.put("paymentTimeLimit","2019-02-15 10:48:00");
        map.put("returnUrl","http://172.30.3.141/jollytravel/#!/member/order-detail-hotel.html?frm=2&lang=0&currency=USD&appVersion=4.19&countryCode=undefined&prevpage=itinerary&id=1219021570112058");
//
        System.out.println(signer.generateSignature(map, signer.defaultKey));
//        String json = "{\"agency\":\"hotel\",\"amt\":71,\"authProcessUrl\":\"http://172.30.3.149:8082/hotel/hotelBooking/updateOrderStatusPaying?orderNo=1219020170112027&sign=E357DB16178A412D6F86EA61945CE626\",\"billInfo\":\"{\\\"userId\\\":53}\",\"currency\":\"USD\",\"orderId\":\"1219020170112027\",\"orderSource\":\"1\",\"paymentTimeLimit\":\"2019-02-01 17:40:37\",\"returnUrl\":\"http://172.30.3.141/jollytravel/#!/member/order-detail-hotel.html?frm=2&lang=0&currency=USD&appVersion=4.19&countryCode=undefined&prevpage=itinerary&id=1219020170112027\",\"sign\":\"93878DCDB36F0EF2FC52691FE49B5AF7\"}";
//        CheckoutParameterVo vo = JSON.parseObject(json, CheckoutParameterVo.class);
//        signer.checkObjSign(vo);
    }

    public static void test3() throws IllegalAccessException {
        String ss = "{\"agencyRef\":\"1219020270112035\",\"amt\":\"20\",\"ap\":\"AP1809110000001743\",\"code\":\"10000\",\"status\":\"Authorised\",\"sign\":\"\"}";
        AtplanPayReturnBookingParamDto dto = JSON.parseObject(ss, AtplanPayReturnBookingParamDto.class);
        JtSigner signer = new JtSigner();
        signer.sign(dto);
        System.out.println(dto.getSign());
    }

    /**
     * 给对象加签名
     * @author shipp
     * @date 2019/2/1 15:23
     * @param obj
     * @return boolean
     */
    public boolean sign(Object obj) throws IllegalAccessException {
        if(obj == null){
            return false;
        }

        Map<String, String> map = new HashMap<>();

        Field signField = null;
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals("sign")) signField = field;
            field.setAccessible(true);
            if (field.get(obj) == null) continue;
            map.put(field.getName(), field.get(obj).toString());
        }

        if (signField == null) return false;

        String sign = generateSignature(map, defaultKey);
        signField.set(obj, sign);
        return true;
    }

    /**
     * 检查签名
     * @author shipp
     * @date 2019/2/1 15:23
     * @param obj
     * @return boolean
     */
    public boolean checkObjSign(Object obj) throws IllegalAccessException {
        if(obj == null){
            return false;
        }

        Map<String, String> map = new HashMap<>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.get(obj) == null) continue;
            if (field.get(obj) instanceof BigDecimal) {
                map.put(field.getName(), ((BigDecimal) field.get(obj)).setScale(2, RoundingMode.HALF_UP).toString());
                continue;
            }
            map.put(field.getName(), field.get(obj).toString());
        }

        return checkSign(map);
    }

    /**
     * checkSign with defaultKey
     * @author shipp
     * @date 2018/11/12 13:41
     * @param map
     * @return boolean
     */
    public boolean checkSign(Map<String, String> map) {
        return checkSign(map, defaultKey);
    }

    /**
     * 检查签名是否正确
     * @author shipp
     * @date 2018/11/12 13:24
     * @param map received map
     * @param key sign key.
     * @return true if the signature is correct, false otherwise.
     */
    public boolean checkSign(Map<String, String> map, String key) {
        // received
        String receivedSign = map.get("sign");
        if (receivedSign == null || receivedSign.length() == 0) return false;

        // calculated
        String calculatedSign = generateSignature(map, key);

        // compare
        return receivedSign.equals(calculatedSign);
    }

    /**
     * sign with defaultKey
     * @author shipp
     * @date 2018/11/12 13:41
     * @param map
     * @return void
     */
    public void signature(Map<String, String> map) {
        signature(map, defaultKey);
    }

    /**
     * 生成签名并放到map中
     * @author shipp
     * @date 2018/11/12 13:26
     * @param map
     * @param key
     * @return void
     */
    public void signature(Map<String, String> map, String key) {
        if (map == null || map.isEmpty()) return;

        String sign = generateSignature(map, key);
        map.put("sign", sign);
    }

    /**
     * 生成签名并返回
     * @author shipp
     * @date 2018/11/12 13:26
     * @param map
     * @param key
     * @return java.lang.String
     */
    public String generateSignature(Map<String, String> map, String key) {
        if (map == null || map.isEmpty()) return "";
        Set<String> keySet = map.keySet();
        String[] str = new String[map.size()];
        StringBuilder tmp = new StringBuilder();
        // 进行字典排序
        str = keySet.toArray(str);
        Arrays.sort(str);
        for (int i = 0; i < str.length; i++) {
            if ("sign".equals(str[i])) continue;
            String t = str[i] + "=" + map.get(str[i]) + "&";
//            System.out.println(t);
            tmp.append(t);
        }

        if (null != key) {
            tmp.append("key=" + key);
        }

        return MD5Encode(tmp.toString(),"UTF-8").toUpperCase();
    }

    /**
     * md5 encode
     * @author shipp
     * @date 2018/11/12 13:13
     * @param origin
     * @param charset
     * @return java.lang.String
     */
    public String MD5Encode(String origin, String charset) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charset == null || "".equals(charset))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charset)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    /**
     * 二进制数组转16进制
     * @author shipp
     * @date 2018/11/12 13:33
     * @param b
     * @return java.lang.String
     */
    private String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    /**
     * 二进制byte转16进制
     * @author shipp
     * @date 2018/11/12 13:34
     * @param b
     * @return java.lang.String
     */
    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
