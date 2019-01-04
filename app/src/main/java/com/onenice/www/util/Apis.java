package com.onenice.www.util;

/**
 *
 * @详情 所有的请求接口
 *
 * @创建日期 2018/12/29 15:02
 *
 */
public class Apis {

    //登录接口
    public static final String LOGIN_URL="user/v1/login";
    //注册接口
    public static final String REGISTER_URL="user/v1/register";
    //首页轮播图的接口
    public static final String SHOW_BANNER_URL="commodity/v1/bannerShow";
    //首页商品的接口
    public static final String SHOW_SHOP_URL="commodity/v1/commodityList";
    //首页商品详细信息的接口
    public static final String SHOW_CLASSIFY_URL="commodity/v1/findCommodityListByLabel?labelId=%s&page=%d&count=%d";
    //商品查询的接口
    public static final String SHOW_SEARCH_URL="commodity/v1/findCommodityByKeyword?keyword=%s&page=%d&count=%d";
}
