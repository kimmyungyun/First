package com.example.user.first;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 명윤 on 2018-01-23.
 */

public class DotShowAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> girdViewItemList = new ArrayList<ListViewItem>();
    public DotShowAdapter(){
    }
    //Adapter에 사용 되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount(){return girdViewItemList.size();}

    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //외부 XML 파일에 있는 resource로 부터 가져옴.
            //요 밑에 줄 file_find 수정 해야됨. xml 따로 만들어서 해야함.
            convertView = inflater.inflate(R.layout.dot_show,parent, false);
        }

        //화면에 표시될 View(Layout 이 inflate된)으로 부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView)convertView.findViewById(R.id.Dot_img);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.Dot_txt);

        //Data Set(ListViewItemList) 에서 position 에 위치한 데이터 참조 획득
        ListViewItem listViewItem = girdViewItemList.get(position);

        //아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getTitle());

        return convertView;
    }
    //지정한 위치에 있는 데이터와 관계된 아이템의 ID를 리턴 : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    //지정한 위치에 있는 데이터 리턴 : 필수 구현
    @Override
    public ListViewItem getItem(int position) {
        return girdViewItemList.get(position);
    }

    //아이템 데이터 추가를 위한 함수, 개발자가 원하는대로 작성 가능
    public void addItem(Drawable icon, String title) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        girdViewItemList.add(item);
    }
}
