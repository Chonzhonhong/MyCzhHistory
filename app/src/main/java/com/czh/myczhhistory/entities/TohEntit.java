package com.czh.myczhhistory.entities;

/**
 * @Created By Admin  on 2020/9/27
 * @Email : 163235610@qq.com
 * @Author:Mrczh
 * @Instructions:
 */
public class TohEntit {
    /**
     *          "_id":"18141001",
     * 			"title":"反法联盟各参加国在奥地利首都维也纳召开会议",
     * 			"pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201110/2/1F81726127.jpg",
     * 			"year":1814,
     * 			"month":10,
     * 			"day":1,
     * 			"des":"在206年前的今天，1814年10月1日 (农历八月十八)，反法联盟各参加国在奥地利首都维也纳召开会议。",
     * 			"lunar":"甲戌年八月十八"
     * 	    "_id":"13920928",
     *
     * */
    public String _id;
    public String title;
    public String pic;
    public String year;
    public String month;
    public String day;
    public String des;
    public String lunar;
    public String content;

    @Override
    public String toString() {
        return "TohEntit{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", des='" + des + '\'' +
                ", lunar='" + lunar + '\'' +
                '}';
    }
}
