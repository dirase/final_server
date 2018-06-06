package com.we.vpn.api.controller;

import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.we.vpn.api.core.RestController;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

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
        String id = null;
        try {
            id = URLDecoder.decode(getPara(0),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
        String id = null;
        try {
            id = URLDecoder.decode(getPara(0),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            id = "%";
            e.printStackTrace();
        }
        List<Record> data = Db.find("select * from room_info where from_hotel LIKE ? ","%"+id+"%");
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
    public void updateperson(){
        String name = null;
        try {
            name = URLDecoder.decode(getPara(0),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String phone = getPara(1);
        int num = getParaToInt(2);
        Db.update("update people_info set people_name = ?, people_phone = ? where people_num=?",name,phone, num);
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
        String hotelname = null;
        String personname = null;
        try {
            personname = URLDecoder.decode(getPara(10),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String info = null;
        try {
            info = URLDecoder.decode(getPara(11),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String room_name = null;
        try {
            room_name = URLDecoder.decode(getPara(12),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            hotelname = URLDecoder.decode(getPara(13),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int room_num = getParaToInt(14);
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
        user.set("tips_room",room_name);
        user.set("tips_hotelname",hotelname);
        //Record user = new Record().set("tips_time_start",time_start).set("tips_time_stop",time_end).set("tips_hotel",hotel_num).set("tips_people",people_num).set("tips_phone",tips_phone).set("tips_rateyear",year).set("tips_ratemonth",month).set("tips_rateday",day).set("tips_ratehour",hour).set("tips_rateminutes",minutes);
        Db.save("tips_info",user);
        Db.update("update room_info set room_used = room_used - 1 where room_num=?",room_num);
        renderText("OK");
    }

    public void addhotel(){
        String  hotel_name = null;
        String  hotel_information = null;
        String hotel_address = null;
        String sheng = null;
        String city = null;
        String view = null;
        String jing = getPara(7);
        String wei = getPara(8);
        try {
            hotel_name = URLDecoder.decode(getPara(0),"UTF-8");
            hotel_information = URLDecoder.decode(getPara(1),"UTF-8");
            hotel_address = URLDecoder.decode(getPara(2),"UTF-8");
            sheng = URLDecoder.decode(getPara(4),"UTF-8");
            city = URLDecoder.decode(getPara(5),"UTF-8");
            view = URLDecoder.decode(getPara(6),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String hotel_phone = getPara(3);
        int  star = getParaToInt(7);
        Record user = new Record().set("name",hotel_name).set("hotel_information",hotel_information).set("hotel_adress",hotel_address).set("hotel_phone",hotel_phone).set("hotel_sheng",sheng).set("hotel_city",city).set("hotel_view",view).set("hotel_jing",jing).set("hotel_wei",wei);
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
        String  room_name = null;
        try {
            room_name = URLDecoder.decode(getPara(0),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int room_level = getParaToInt(1);
        String info = null;
        try {
            info = URLDecoder.decode(getPara(2),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String name = null;
        try {
            name = URLDecoder.decode(getPara(3),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String things = null;
        try {
            things = URLDecoder.decode(getPara(4),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Record user = new Record().set("room_name",room_name).set("hotel_level",room_level).set("room_information",info).set("from_hotel",name).set("room_things",things);
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
        String sheng =null;
        String city =null;
        int star = getParaToInt(4);
        int leixing = getParaToInt(5);
        String view = null;
        try {
            sheng = URLDecoder.decode(getPara(2),"UTF-8");
            city = URLDecoder.decode(getPara(3),"UTF-8");
            view = URLDecoder.decode(getPara(6),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            view = "%";
            city= "%";
            sheng= "%";
            e.printStackTrace();
        }
        //String view = getPara(6);
        //List<Record> tips = Db.find("select * from hotel_info where hotel_stars = ?",star);
        List<Record> tips = Db.find("select * from hotel_info where hotel_stars = ? and hotel_min >= ? and  hotel_max <= ? and hotel_sheng LIKE ? and hotel_city LIKE ? and hotel_leixing in (?,3) and hotel_view LIKE ?",star,min,max,"%"+sheng+"%","%"+city+"%",leixing,"%"+view+"%");
        renderJson(JsonKit.toJson(tips));
    }

    public void gethotel2(){
        int min = getParaToInt(0);
        int max = getParaToInt(1);
        String sheng =null;
        String city= null ;
        int leixing = getParaToInt(4);
        //String view = getPara(5);
        String view = null;
        try {
            sheng = URLDecoder.decode(getPara(2),"UTF-8");
            city = URLDecoder.decode(getPara(3),"UTF-8");
            view = URLDecoder.decode(getPara(5),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            view = "%";
            city= "%";
            sheng= "%";
            e.printStackTrace();
        }
        //List<Record> tips = Db.find("select * from hotel_info where hotel_stars = ?",star);
        List<Record> tips = Db.find("select * from hotel_info where hotel_min >= ? and  hotel_max <= ? and hotel_sheng LIKE ? and hotel_city LIKE ? and hotel_leixing in (?,3) and hotel_view LIKE ? ",min,max,"%"+sheng+"%","%"+city+"%",leixing,"%"+view+"%");
        renderJson(JsonKit.toJson(tips));
    }

}
