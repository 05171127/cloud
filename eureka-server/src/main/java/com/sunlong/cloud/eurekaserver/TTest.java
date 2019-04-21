package com.sunlong.cloud.eurekaserver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/14 18:23
 */
public class TTest {
    public static void main(String[] args) throws Exception {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println(System.currentTimeMillis());
//            }
//        },3000,6000);
//        DecimalFormat df = new DecimalFormat("00.00");
//        double f = 1342.155545;
//        System.out.println(df.format(f));
//        List<String> list = new ArrayList<>(16);
//        for (int i = 0; i < 100; i++) {
//            list.add(String.valueOf(i));
//        }
//        int values = list.size();
//        int size = 10;
//        int num = 20;
//        if (values <= size * (num - 1) && values != 0) {
//            list = new ArrayList<>();
//        }
//        // 分页返回
//        if (values > size * num) {
//            list = list.subList(size * (num - 1), size * num);
//        } else {
//            list = list.subList(size * (num - 1), values);
//        }
//        System.out.println(list.size());

//        Lock lock = new ReentrantLock();
//        Condition condition1 = lock.newCondition();
//        condition1.
//        lock.lock();
//        condition1.signal();
//        lock.unlock();
//        System.out.println(11);
//        lock.lock();
//        System.out.println(1);
//        lock.lock();
//        System.out.println(2);
//        lock.lock();
//        System.out.println(3);
//        Condition condition2 = lock.newCondition();
//        Condition condition3 = lock.newCondition();
//        new Thread1(lock, condition1).start();
//        new Thread1(lock, condition1).start();
//        new Thread1(lock, condition1).start();
//        new Thread1(lock, condition1).start();
//        new Thread1(lock, condition1).start();
//
//        Thread1 t1 = new Thread1(lock, condition1);
//        Thread2 t2 = new Thread2(lock, condition1);
//        new Thread2(lock, condition1).start();
//        new Thread2(lock, condition1).start();
//        new Thread2(lock, condition1).start();
//        new Thread2(lock, condition1).start();
//        new Thread2(lock, condition1).start();
//
//        t1.start();
//        t2.start();
//        System.out.println(formatTime(1542656773943L));
//        test1();
//        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

//        String ss = "123123123";
//        String[] sss = ss.split(",");
//
//        System.out.println(sss);

//        System.out.println(secondToDate(1550894363L, "yyyy-MM-dd HH:mm:ss"));

//        BigDecimal b = new BigDecimal("-100.25");
//        System.out.println(b.setScale(2, RoundingMode.HALF_UP).toString());
//        System.out.println(b.abs().setScale(2, RoundingMode.HALF_UP).toString());

//        testaa();
//        Date d = new Date();
//        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(dateTimeFormat.getCalendar().getTimeZone());
//        System.out.println(dateTimeFormat.format(d));
//        Calendar c = Calendar.getInstance(new SimpleTimeZone(8, "GMT+3"));
//        dateTimeFormat.setCalendar(c);
//        System.out.println(dateTimeFormat.format(d));
//        List list = Arrays.asList("aaa");

//        test22();
//        Stream.generate(Math::random).limit(10).forEach(System.out::println);
//        Stream.of(1,3,2,4,5,1,4,3,2,1).peek(System.out::println);
        Stream.of(1,3,2,4,5,1,4,3,2,1).peek(System.out::println).distinct().mapToInt(p -> p).skip(3).sum();
//        System.out.println(Stream.of(1,3,2,4,5,1,4,3,2,1).peek(System.out::println).distinct().mapToInt(p -> p).skip(3).sum());
    }

    public static void test22() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormat.format(new Date()));
    }

    public static void testaa() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head></head>");
        sb.append("<body>");
        sb.append("<table width=\"700\" height=\"810\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"display:block; margin:0 auto;\"> ");
        sb.append("<tbody>");
        sb.append("<tr> ");
        sb.append("<td><a href=\"http://count.mail.163.com/statistics/c9d6Om.do?product=bedm_334978&amp;domain=email&amp;uid=shipp_21@163.com&amp;area=1\" target=\"_blank\">");
        sb.append("<img src=\"http://www.zjgt.com/public/edm/walletbag2/edm_01.jpg\" width=\"700\" height=\"70\" alt=\"\" style=\"display:block;border:none;\" /></a></td> ");
        sb.append("</tr> ");
        sb.append("<tr> ");
        sb.append("<td><a href=\"http://count.mail.163.com/statistics/S7O5rh.do?product=bedm_334978&amp;domain=email&amp;uid=shipp_21@163.com&amp;area=2\" target=\"_blank\">");
        sb.append("<img src=\"http://www.zjgt.com/public/edm/walletbag2/edm_02.jpg\" width=\"700\" height=\"170\" alt=\"\" style=\"display:block;border:none;\" /></a></td> ");
        sb.append("</tr> ");
        sb.append("<tr> ");
        sb.append("<td><a href=\"http://count.mail.163.com/statistics/S7O5rh.do?product=bedm_334978&amp;domain=email&amp;uid=shipp_21@163.com&amp;area=3\" target=\"_blank\">");
        sb.append("<img src=\"http://www.zjgt.com/public/edm/walletbag2/edm_03.jpg\" width=\"700\" height=\"170\" alt=\"\" style=\"display:block;border:none;\" /></a></td> ");
        sb.append("</tr> ");
        sb.append("<tr> ");
        sb.append("<td><a href=\"http://count.mail.163.com/statistics/S7O5rh.do?product=bedm_334978&amp;domain=email&amp;uid=shipp_21@163.com&amp;area=4\" target=\"_blank\">");
        sb.append("<img src=\"http://www.zjgt.com/public/edm/walletbag2/edm_04.jpg\" width=\"700\" height=\"170\" alt=\"\" style=\"display:block;border:none;\" /></a></td> ");
        sb.append("</tr> ");
        sb.append("<tr> ");
        sb.append("<td><a href=\"http://count.mail.163.com/statistics/S7O5rh.do?product=bedm_334978&amp;domain=email&amp;uid=shipp_21@163.com&amp;area=5\" target=\"_blank\">");
        sb.append("<img src=\"http://www.zjgt.com/public/edm/walletbag2/edm_05.jpg\" width=\"700\" height=\"162\" alt=\"\" style=\"display:block;border:none;\" /></a></td> ");
        sb.append("</tr> ");
        sb.append("<tr> ");
        sb.append("<td><a href=\"http://count.mail.163.com/statistics/S7O5rh.do?product=bedm_334978&amp;domain=email&amp;uid=shipp_21@163.com&amp;area=6\" target=\"_blank\">");
        sb.append("<img src=\"http://www.zjgt.com/public/edm/walletbag2/edm_06.jpg\" width=\"700\" height=\"68\" alt=\"\" style=\"display:block;border:none;\" /></a></td> ");
        sb.append("</tr>");
        sb.append("</tbody>");
        sb.append("</table>");
        sb.append("<img src=\"http://count.mail.163.com/beacon/bedm.gif?no=bedm_334978&amp;domain=email&amp;date=20160419&amp;uid=shipp_21@163.com\" style=\"display:none\" />");
        sb.append("<div>");
        sb.append("<div style=\"text-align:center;padding-top:15px;font-size:10px;color:#777\">");
        sb.append("如果你不想再收到该产品的推荐邮件，请点击 ");
        sb.append("<a style=\"font-size:10px\" href=\"http://em.mail.163.com/anonymous/adsubscribe/unsubscribe?depId=1&amp;proId=3327801&amp;mitId=334978&amp;sec=L0598OMnI6IKwTcSP7NqwgvhXJuFHQM1\" hidefocus=\"true\">这里退订</a>");
        sb.append("</div>");
        sb.append("</div> ");
        sb.append("<style type=\"text/css\">");
        sb.append("body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}");
        sb.append("td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}");
        sb.append("pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}");
        sb.append("th,td{font-family:arial,verdana,sans-serif;line-height:1.666}");
        sb.append("img{ border:0}");
        sb.append("header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}");
        sb.append("</style> ");
        sb.append("<style id=\"ntes_link_color\" type=\"text/css\">a,td a{color:#653528}</style> ");
        sb.append("</body>");
        sb.append("</html>");
        System.out.println(sb.toString());
    }



    private static String secondToDate(long second,String patten) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second * 1000);//转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String dateString = format.format(date);
        return dateString;
    }

    public static void test1() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread2(lock, condition).start();
        new Thread2(lock, condition).start();
        new Thread2(lock, condition).start();
        new Thread2(lock, condition).start();
        new Thread2(lock, condition).start();
        lock.lock();
        System.out.println("aaa1");
        condition.await();
        System.out.println("aaa2");
        lock.unlock();
        System.out.println("aaa");
    }


    public static String formatTime(long ms) {
        Date d = new Date(ms);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormat.format(d);
    }
}
