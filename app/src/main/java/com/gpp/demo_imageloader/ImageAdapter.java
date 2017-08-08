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

public class ImageAdapter extends BaseAdapter implements MainActivity.OnStateChanged {
    private List<String> list;
    private LayoutInflater layoutInflater;
    private ImageLoader mImageLoader;
    private int mImageWidth, mImageHeight;
    private boolean isGridViewIdle;

    public ImageAdapter(List<String> list, Context context) {
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        mImageLoader = ImageLoader.build(context);
        ((MainActivity) context).setOnStateChanged(this);
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
            view = layoutInflater.inflate(R.layout.item_gv, viewGroup, false);
            holder = new ViewHolder();
            holder.ImageView = view.findViewById(R.id.image_gv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ImageView imageView = holder.ImageView;

        mImageWidth = imageView.getWidth();
        mImageHeight = imageView.getHeight();
        String url = (String) getItem(i);
        imageView.setTag(url);
//        if (isGridViewIdle) {
            mImageLoader.bindBitmap(url, holder.ImageView, mImageWidth, mImageHeight);
//        }
        return view;
    }

    @Override
    public void isChanged(boolean isChanged) {
        isGridViewIdle = isChanged;
    }


    class ViewHolder {
        public ImageView ImageView;
    }
}
