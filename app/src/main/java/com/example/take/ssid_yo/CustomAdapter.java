package com.example.take.ssid_yo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by take on 7/28/14.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    static class ViewHolder {
        TextView labelText;
    }

    private LayoutInflater inflater;

    // コンストラクタ
    public CustomAdapter(Context context,int textViewResourceId, ArrayList<String> labelList) {
        super(context, textViewResourceId, labelList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        View view = convertView;

        // View を利用している場合は、新たに View を作らない
        if(view == null){
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_layout, null);
            TextView label = (TextView) view.findViewById(R.id.tv);
            holder = new ViewHolder();
            holder.labelText = label;
            view.setTag(holder);
        }else{
                holder = (ViewHolder) view.getTag();
        }

        // 特定の行のデータを取得
        String str = getItem(position);

        if(!TextUtils.isEmpty(str)){
            // TextView にラベルをセット
            holder.labelText.setText(str);
        }

        // 行毎に背景色を変える
        if(position%2==0){
            holder.labelText.setBackgroundColor(Color.parseColor("#aa22aa"));
        }else{
            holder.labelText.setBackgroundColor(Color.parseColor("#882288"));
        }

        // XML で定義したアニメーションを読み込む
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.item_motion);
        // リストアイテムのアニメーションを開始
        view.startAnimation(anim);

        return view;
    }
}
