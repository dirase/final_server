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
        String id = getPara(0);
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
        List<Record> tips = Db.find("select * from tips_info where tips_hotel=?",id);
        renderJson(JsonKit.toJson(tips));
    }

    public void findtips(){
        List<Record> tips = Db.find("select * from tips_info");
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

    public void rate(){
        int num = getParaToInt(0);
        int star = getParaToInt(1);
        int hotel = getParaToInt(2);
        String info = getPara(3);
        int year = getParaToInt(4);
        int month = getParaToInt(5);
        int day = getParaToInt(6);
        Db.update("update tips_info set tips_star = ? where tips_num=?",star, num);
        Db.update("update tips_info set tips_text = ? where tips_num = ?",info,num);
        Db.update("update tips_info set rateyear = ? where tips_num = ?",year,num);
        Db.update("update tips_info set ratemonth = ? where tips_num = ?",month,num);
        Db.update("update tips_info set rateday = ? where tips_num = ?",day,num);
        if(star==1){
            Db.update("update hotel_info set hotel_star_1 = hotel_star_1 + 1 where num=?", hotel);
        }
        if(star==2){
            Db.update("update hotel_info set hotel_star_2 = hotel_star_2 + 1 where num=?", hotel);
        }
        if(star==3){
            Db.update("update hotel_info set hotel_star_3 = hotel_star_3 + 1 where num=?", hotel);
        }
        if(star==4){
            Db.update("update hotel_info set hotel_star_4 = hotel_star_4 + 1 where num=?", hotel);
        }
        if(star==5){
            Db.update("update hotel_info set hotel_star_5 = hotel_star_5 + 1 where num=?", hotel);
        }
        //Record user = new Record().set("people_name",name).set("people_password",password).set("people_phone",phone).set("people_level",level);
        //Db.save("people_info",user);
        renderText("OK");
    }
    public void cancel(){
        int tips_num = getParaToInt(0);
        int num = 2;
        Db.update("update tips_info set tips_now = ? where tips_num=?",num,tips_num);
        renderText("OK");
    }

    public void addtips(){
        String  time_start = getPara(0);
        String  time_end = getPara(1);
        int hotel_num = getParaToInt(2);
        int people_num = getParaToInt(3);
        int tips_phone = getParaToInt(4);
        int year = getParaToInt(5);
        int month = getParaToInt(6);
        int day = getParaToInt(7);
        int hour = getParaToInt(8);
        int minutes = getParaToInt(9);
        String personname = getPara(10);
        String info = getPara(11);
        Record user = new Record();
        user.set("tips_time_start",time_start);
        user.set("tips_time_stop",time_end);
        user.set("tips_hotel",hotel_num);
        user.set("tips_people",people_num);
        user.set("tips_phone",tips_phone);
        user.set("tips_rateyear",year);
        user.set("tips_ratemonth",month);
        user.set("tips_rateday",day);
        user.set("tips_ratehour",hour);
        user.set("tips_rateminutes",minutes);
        user.set("tips_personname",personname);
        user.set("tips_yaoqiu",info);
        //Record user = new Record().set("tips_time_start",time_start).set("tips_time_stop",time_end).set("tips_hotel",hotel_num).set("tips_people",people_num).set("tips_phone",tips_phone).set("tips_rateyear",year).set("tips_ratemonth",month).set("tips_rateday",day).set("tips_ratehour",hour).set("tips_rateminutes",minutes);
        Db.save("tips_info",user);
        renderText("OK");
    }

    public void addhotel(){
        String  hotel_name = getPara(0);
        String  hotel_information = getPara(1);
        String hotel_address = getPara(2);
        String hotel_phone = getPara(3);
        Record user = new Record().set("name",hotel_name).set("hotel_information",hotel_information).set("hotel_adress",hotel_address).set("hotel_phone",hotel_phone);
        Db.save("hotel_info",user);
        Db.update("update hotel_num set hotel_num_num = hotel_num_num + 1");
        renderText("OK");
    }

    public void changepsd(){
        String id = getPara(0);
        String psd = getPara(1);
        Db.update("update people_info set people_password = ? where people_name=?", psd, id);
        renderText("OK");
    }

    public void deletehotel(){
        String name = getPara(0);
        Db.update("delete from hotel_info where name = ?",name);
        renderText("OK");
    }

    public void deleterate(){
        int name = getParaToInt(0);
        String hotel = getPara(1);
        int star = getParaToInt(2);
        Db.update("update tips_info set tips_star = 0 where tips_num=?",name);
        Db.update("update tips_info set tips_text = null where tips_num=?",name);
        if(star==1){
            Db.update("update hotel_info set hotel_star_1 = hotel_star_1 - 1 where num=?", hotel);
        }
        if(star==2){
            Db.update("update hotel_info set hotel_star_2 = hotel_star_2 - 1 where num=?", hotel);
        }
        if(star==3){
            Db.update("update hotel_info set hotel_star_3 = hotel_star_3 - 1 where num=?", hotel);
        }
        if(star==4){
            Db.update("update hotel_info set hotel_star_4 = hotel_star_4 - 1 where num=?", hotel);
        }
        if(star==5){
            Db.update("update hotel_info set hotel_star_5 = hotel_star_5 - 1 where num=?", hotel);
        }
        renderText("OK");
    }

    public void addroom(){
        String  room_name = getPara(0);
        int room_level = getParaToInt(1);
        String info = getPara(2);
        String name = getPara(3);
        Record user = new Record().set("room_name",room_name).set("hotel_level",room_level).set("room_information",info).set("from_hotel",name);
        Db.save("room_info",user);
        Db.update("update hotel_info set hotel_room_num = hotel_room_num + 1 where name = ?",name);
        renderText("OK");
    }

    public void deletepeople(){
        String name = getPara(0);
        Db.update("delete from people_info where people_name = ?",name);
        renderText("OK");
    }

    public void gethotel(){
        int min = getParaToInt(0);
        int max = getParaToInt(1);
        String sheng = getPara(2);
        String city = getPara(3);
        int star = getParaToInt(4);
        int leixing = getParaToInt(5);
        //List<Record> tips = Db.find("select * from hotel_info where hotel_stars = ?",star);
        List<Record> tips = Db.find("select * from hotel_info where hotel_stars = ? and hotel_min >= ? and  hotel_max <= ? and hotel_sheng = ? and hotel_city = ? and hotel_leixing in (?,3)",star,min,max,sheng,city,leixing);
        renderJson(JsonKit.toJson(tips));
    }

    public void gethotel2(){
        int min = getParaToInt(0);
        int max = getParaToInt(1);
        String sheng = getPara(2);
        String city = getPara(3);
        int leixing = getParaToInt(4);
        //List<Record> tips = Db.find("select * from hotel_info where hotel_stars = ?",star);
        List<Record> tips = Db.find("select * from hotel_info where hotel_min >= ? and  hotel_max <= ? and hotel_sheng = ? and hotel_city = ? and hotel_leixing in (?,3)",min,max,sheng,city,leixing);
        renderJson(JsonKit.toJson(tips));
    }

}
