package com.example.user.first;

import android.graphics.drawable.Drawable;

/**
 * Created by 명윤 on 2018-01-27.
 */

public class DotShowItem {
    private Drawable iconDrawable1;
    private Drawable iconDrawable2;
    private String titleStr;

    public void setIcon(Drawable icon){
        iconDrawable1 = icon;
    }
    public void setIcon2(Drawable icon){
        iconDrawable2 = icon;
    }
    public void setTitle(String title){
        titleStr= title;
    }
    public Drawable getIcon(){
        return this.iconDrawable1;
    }
    public Drawable getIcon2(){
        return this.iconDrawable2;
    }
    public String getTitle(){
        return this.titleStr;
    }

}
