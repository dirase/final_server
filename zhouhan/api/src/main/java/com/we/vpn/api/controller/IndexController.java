package com.we.vpn.api.controller;

import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.we.vpn.api.core.RestController;
import java.util.*;

/**
 * Created by swn on 2017-04-06.
 */
public class IndexController extends RestController {

    public void index() {
        renderText("hello hanhan");
    }

    public void findHotelInfo() {
        int id = getParaToInt(0);
        Record hotel = Db.findFirst("select * from hotel_info where num=?", id);
        renderJson(JsonKit.toJson(hotel));
    }

    public void findhotelnum(){
        int id = getParaToInt(0);
        Record user = Db.findFirst("select * from hotel_num where hotel_num_id=?",id);
        renderJson(JsonKit.toJson(user));
}

    public void findhotelbyname(){
        String id = getPara(0);
        Record user = Db.findFirst("select * from hotel_info where name=?",id);
        renderJson(JsonKit.toJson(user));
    }

    public void updatehotelnum(){
        int id = getParaToInt(0);
        String num = getPara(1);
        //Record user = new Record();
        //user = Db.findById("",id).set("hotel_num_num",num);
        //Db.update("hotel_num",user);
        //Record user = Db.update("select * from hotel_num where hotel_num_id=?",id);
        //Record user =Db.update("update table hotel_num hotel_num_num=? where id=?",num, id);
        Db.update("update hotel_num set hotel_num_num = ? where hotel_num_id=?", num, id);
        renderText("OK");
    }

    public void finduserinfo(){
        String id = getPara(0);
        Record user = Db.findFirst("select * from people_info where people_name=?",id);
        renderJson(JsonKit.toJson(user));
    }

    public void findroombyhotel(){
        int id = getParaToInt(0);
        List<Record> data = Db.find("select * from room_info where from_hotel=?",id);
        renderJson(JsonKit.toJson(data));
    }

    public void findroombynum(){
        int id = getParaToInt(0);
        Record room = Db.findFirst("select * from room_info where room_num=?",id);
        renderJson(JsonKit.toJson(room));
    }

    public void findtipsbynum(){
        int id = getParaToInt(0);
        Record tips = Db.findFirst("select * from tips_info where tips_num=?",id);
        renderJson(JsonKit.toJson(tips));
    }

    public void findtipsbypeople(){
        int id = getParaToInt(0);
        List<Record> data = Db.find("select * from tips_info where tips_people=?",id);
        renderJson(JsonKit.toJson(data));
    }

    public void findtipsbyhotel(){
        int id = getParaToInt(0);
        Record tips = Db.findFirst("select * from room_info where tips_hotel=?",id);
        renderJson(JsonKit.toJson(tips));
    }

    public void findtipsbyroom(){
        int id = getParaToInt(0);
        Record tips = Db.findFirst("select * from room_info where tips_room=?",id);
        renderJson(JsonKit.toJson(tips));
    }

    public void signup(){
        String name = getPara(0);
        String password = getPara(1);
        long phone = getParaToLong(2);
        int level = 0;
        Record user = new Record().set("people_name",name).set("people_password",password).set("people_phone",phone).set("people_level",level);
        Db.save("people_info",user);
        renderText("OK");
    }

    public void addtips(){
        int num = getParaToInt(0);
        String  time_start = getPara(1);
        String  time_end = getPara(2);
        int room_num = getParaToInt(3);
        int hotel_num = getParaToInt(4);
        int people_num = getParaToInt(5);
        int star = getParaToInt(6);
        Record user = new Record().set("tips_num",num).set("tips_time_start",time_start).set("tips_time_stop",time_end).set("tips_room",room_num).set("tips_hotel",hotel_num).set("tips_people",people_num).set("tips_star",star);
        Db.save("tips_info",user);
        renderText("OK");
    }


}
