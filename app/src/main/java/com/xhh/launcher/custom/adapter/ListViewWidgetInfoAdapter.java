package com.xhh.launcher.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xhh.launcher.custom.R;
import com.xhh.launcher.custom.model.AppWidgetInfo;

import java.util.ArrayList;

/**
 * Created by nameh on 2018/3/21 0021.
 */

public class ListViewWidgetInfoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AppWidgetInfo> appWidgetInfos=new ArrayList<>();

    public ListViewWidgetInfoAdapter(Context context,ArrayList<AppWidgetInfo> appWidgetInfos){
        this.context=context;
        this.appWidgetInfos=appWidgetInfos;
    }

    @Override
    public int getCount() {
        return appWidgetInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return appWidgetInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.listview_widgetinfo,null);
        ImageView icon=view.findViewById(R.id.widget_icon);
        TextView name=view.findViewById(R.id.widget_name);
        icon.setBackground(appWidgetInfos.get(position).getIcon());
        name.setText(appWidgetInfos.get(position).getName());
        return view;
    }
}
