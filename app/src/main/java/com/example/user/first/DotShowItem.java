package com.example.user.first;

import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by 명윤 on 2018-01-27.
 */

public class DotShowItem {
    private Drawable iconDrawable1;
    private Drawable iconDrawable2;
    private String titleStr;
    private int Type;
    public DotShowItem(){

    }
    public DotShowItem(Drawable icon, Drawable icon2, String title, int Type){
        this.iconDrawable1 = icon;
        this.iconDrawable2 = icon2;
        this.titleStr = title;
        this.Type = Type;
        Log.d("ADd 값.", "생성자");
    }
    public void setIcon(Drawable icon){
        iconDrawable1 = icon;
    }
    public void setIcon2(Drawable icon){
        iconDrawable2 = icon;
    }
    public void setTitle(String title){
        titleStr= title;
    }
    public void setType(int type){Type = type;}
    public Drawable getIcon(){
        return this.iconDrawable1;
    }
    public Drawable getIcon2(){
        return this.iconDrawable2;
    }
    public String getTitle(){
        return this.titleStr;
    }
    public int getType(){return Type;}
}
