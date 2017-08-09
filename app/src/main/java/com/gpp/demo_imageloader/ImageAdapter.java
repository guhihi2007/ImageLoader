package com.gpp.demo_imageloader;

import android.content.Context;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/5.
 */

public class ImageAdapter extends BaseAdapter {
    private List<String> list;
    private LayoutInflater layoutInflater;
    private ImageLoader mImageLoader;
    private int mImageWidth, mImageHeight;
    private boolean isGridViewIdle = true;

    public ImageAdapter(List<String> list, Context context) {
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        mImageLoader = ImageLoader.build(context);
//        ((MainActivity) context).setOnStateChanged(this);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_gv, null);
            holder = new ViewHolder();
            holder.squareImageView = view.findViewById(R.id.image_gv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        SquareImageView imageView = holder.squareImageView;
        String url = list.get(i);
        mImageLoader.bindBitmap(url, imageView, mImageWidth, mImageHeight);
        return view;
    }

//    @Override
//    public void isChanged(boolean isChanged) {
//        isGridViewIdle = isChanged;
//    }


    class ViewHolder {
        public SquareImageView squareImageView;
    }
}
