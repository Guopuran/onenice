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

    //查询一级列表分类的接口
    public static final String SHOW_NAV_ONE_URL="commodity/v1/findFirstCategory";
    //查询二级列表分类的接口
    public static final String SHOW_NAV_TWO_URL="commodity/v1/findSecondCategory?firstCategoryId=%s";
    //查询二级商品的接口
    public static final String SHOW_NAV_TWO_SHOP_URL="commodity/v1/findCommodityByCategory?categoryId=%s&page=%d&count=%d";
    //查询圈子列表接口
    public static final String SHOW_CIRCLE_LIST_URL="circle/v1/findCircleList?page=%d&count=%d";
    //圈子点赞接口
    public static final String SHOW_CIRCLE_LIKE_URL="circle/verify/v1/addCircleGreat";
    //圈子取消点赞接口
    public static final String SHOW_CIRCLE_CANCEL_URL="circle/verify/v1/cancelCircleGreat?circleId=%d";


}
