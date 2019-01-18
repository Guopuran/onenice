package com.onenice.www.bean;

import java.util.List;

public class SelectCircleBean {

    /**
     * result : [{"commodityId":55,"content":"我才十二岁","createTime":1547773706000,"greatNum":0,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2019-01-17/20190117182335.jpg","id":364,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-01-17/3451620190117190826.jpg","nickName":"水中花","userId":83},{"commodityId":55,"content":"长长的站台，漫漫的等待，只有出发的爱 ","createTime":1547773663000,"greatNum":0,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2019-01-17/20190117182335.jpg","id":363,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-01-17/1058720190117190743.jpg","nickName":"水中花","userId":83},{"commodityId":55,"content":"我要找到你 不管南北东西 ","createTime":1547773603000,"greatNum":0,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2019-01-17/20190117182335.jpg","id":362,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-01-17/1425320190117190643.jpg","nickName":"水中花","userId":83},{"commodityId":13,"content":"月亮之上  我的仰望  有多少梦想在自由的飞翔","createTime":1547773541000,"greatNum":0,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2019-01-17/20190117182335.jpg","id":361,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-01-17/7149820190117190541.jpg","nickName":"水中花","userId":83},{"commodityId":3,"content":"如倒影水中的鲜花","createTime":1547773354000,"greatNum":0,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2019-01-17/20190117182335.jpg","id":360,"image":"","nickName":"水中花","userId":83}]
     * message : 查詢成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 55
         * content : 我才十二岁
         * createTime : 1547773706000
         * greatNum : 0
         * headPic : http://mobile.bwstudent.com/images/small/head_pic/2019-01-17/20190117182335.jpg
         * id : 364
         * image : http://mobile.bwstudent.com/images/small/circle_pic/2019-01-17/3451620190117190826.jpg
         * nickName : 水中花
         * userId : 83
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
