package com.onenice.www.bean;

import java.io.Serializable;

/**
 *
 * @描述 在加入购物车之前查询购物车所使用的bean
 *
 * @创建日期 2019/1/8 23:42
 *
 */
public class ShopSelectListBean implements Serializable {
    private int commodityId;
    private int count;

    public ShopSelectListBean(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
