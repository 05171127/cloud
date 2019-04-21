package com.sunlong.cloud.servzuul;

/**
 * @author : shipp
 * @data : 2018/12/3 16:14
 */
public class TravelConstants {

    // 请求ok
    public static final Byte GENERAL_RESPONSE_CODE_SUCCESS = 1;

    // 请求失败
    public static final Byte GENERAL_RESPONSE_CODE_FAILED = 0;

    // 需要登录
    public static final Byte GENERAL_RESPONSE_CODE_NEED_LOGIN = 2;

    // 登陆：成功
    public static final Byte LOGIN_RET_SUCCESS = 1;

    // 登陆：用户名/密码错误
    public static final Byte LOGIN_RET_FAILED_NAMEPWD = 2;

    // 登陆：用户名/密码错误 快达到数量
    public static final Byte LOGIN_RET_FAILED_NAMEPWD_EXCESSIVE = 3;

    // 登陆：账号被锁定
    public static final Byte LOGIN_RET_FAILED_LOCKED = 4;

    // 登录重试次数
    public static final int LOGIN_RETRY_COUNT = 5;

    // 登录重试提醒次数
    public static final int LOGIN_RETRY_REMIND_COUNT = 3;

    // 一个月的秒数
    public static final long ONE_MONTH_SECONDS = 30 * 24 * 60 * 60;

    // 一天的秒数
    public static final long ONE_DAY_SECONDS = 24 * 60 * 60;

    // 半小时秒数
    public static final long HALF_AN_HOUR = 30 * 60;

    // 访问者身份-游客
    public static final Byte ACCESSOR_ROLE_TOURIST = 1;

    // 访问者身份-登录用户
    public static final Byte ACCESSOR_ROLE_USER = 2;

    // token 签发
    public static final String JWT_TOKEN_ISSUER = "jolly tavel";

    // token的秘钥
    public static final String JWT_TOKEN_KEY = "8aa2a45c7e594e79bc91b396dbe57b19";

    // token的subject
    public static final String JWT_TOKEN_SUBJECT = "jolly travel login token";

    // byte是
    public static final Byte YES = 1;

    // byte否
    public static final Byte NO = 0;
}
