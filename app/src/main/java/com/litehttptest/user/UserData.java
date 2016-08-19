/*
 * (#)User.java 1.0 2014-2-16 2014-2-16 GMT+08:00
 */
package com.litehttptest.user;

import com.litehttptest.model.ApiModel;
import com.litehttptest.model.BaseModel;

/**
 * @author 郭世杰
 * @version $1.0, 2014-2-16 2014-2-16 GMT+08:00
 */
public class UserData extends ApiModel<UserData.Data> {
    // {"code":1,"data":{"birthday":"2015-11-22","sex":1,"mobile":"186*****823","avatar":"http://imagecache.bolaihui.com.cn/images/head/1448121672.jpg","userRank":"1","userName":"哈哈","email":"","token":"4c836d9cd1954fd997bd62505789e6e1"}}
    public class Data extends BaseModel {
        private String mobile;
        private int userRank;
        private String userName;
        private String token;
        private String avatar;
        private String idCard;
        private String nickName;
        private String email;
        private int sex;
        private String birthday;
        private String realName;

        ////////////////用户中心////////////////
        private int user_id;
        private int rank_id;
        private int point;
        private int bonus;
        private int collects;
        private int browse;
        private String rank_name;
        private String imToken;

        private int sms_notice;//短信通知
        private int weixin_notice;//微信通知
        private int order_sms;//下单通知
        private int pay_sms;//付款通知
        private int reg_sms;//注册通知
        private int dz_sms;//资金变动通知
        private String aiteId;

        public String getAiteId() {
            return aiteId;
        }

        public void setAiteId(String aiteId) {
            this.aiteId = aiteId;
        }

        public int getSms_notice() {
            return sms_notice;
        }

        public void setSms_notice(int sms_notice) {
            this.sms_notice = sms_notice;
        }

        public int getWeixin_notice() {
            return weixin_notice;
        }

        public void setWeixin_notice(int weixin_notice) {
            this.weixin_notice = weixin_notice;
        }

        public int getOrder_sms() {
            return order_sms;
        }

        public void setOrder_sms(int order_sms) {
            this.order_sms = order_sms;
        }

        public int getPay_sms() {
            return pay_sms;
        }

        public void setPay_sms(int pay_sms) {
            this.pay_sms = pay_sms;
        }

        public int getReg_sms() {
            return reg_sms;
        }

        public void setReg_sms(int reg_sms) {
            this.reg_sms = reg_sms;
        }

        public int getDz_sms() {
            return dz_sms;
        }

        public void setDz_sms(int dz_sms) {
            this.dz_sms = dz_sms;
        }

        public String getImToken() {
            return imToken;
        }

        public void setImToken(String imToken) {
            this.imToken = imToken;
        }

        public String getRank_name() {
            return rank_name;
        }

        public void setRank_name(String rank_name) {
            this.rank_name = rank_name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getRank_id() {
            return rank_id;
        }

        public void setRank_id(int rank_id) {
            this.rank_id = rank_id;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public int getCollects() {
            return collects;
        }

        public void setCollects(int collects) {
            this.collects = collects;
        }

        public int getBrowse() {
            return browse;
        }

        public void setBrowse(int browse) {
            this.browse = browse;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getUserRank() {
            return userRank;
        }

        public void setUserRank(int userRank) {
            this.userRank = userRank;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
