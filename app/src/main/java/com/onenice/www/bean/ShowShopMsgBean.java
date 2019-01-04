package com.onenice.www.bean;
/**
 *
 * @详情 EventBus使用的Bean类
 *
 * @创建日期 2019/1/3 0:35
 *
 */
public class ShowShopMsgBean {
    private Object object;

    private String flag;

    public ShowShopMsgBean(Object object, String flag) {
        this.object = object;
        this.flag = flag;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
