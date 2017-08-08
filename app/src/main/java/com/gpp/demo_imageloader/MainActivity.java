package com.gpp.demo_imageloader;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gv;
    private ImageAdapter adapter;
    private boolean isGridViewIdle;
    private List<String> list = new ArrayList<>();
    public static final String mURL = "http://interface.ttigame.com/api/ranking?type=online&page=1";
    private OnStateChanged stateChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv = (GridView) findViewById(R.id.gv);
        for (String ss : imageUrls) {
            list.add(ss);
        }
        gv.setNumColumns(3);
        adapter = new ImageAdapter(list, this);
        gv.setAdapter(adapter);
//        stateChanged.isChanged(isGridViewIdle);
//        adapter.notifyDataSetChanged();
//        gv.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int i) {
//                if (i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
//                    isGridViewIdle = true;
//                } else {
//                    isGridViewIdle = false;
//                }
//                stateChanged.isChanged(isGridViewIdle);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//            }
//        });
//        new NewsAsycTask().execute(mURL);
    }

    private String[] imageUrls = {
            "http://b.hiphotos.baidu.com/zhidao/pic/item/a6efce1b9d16fdfafee0cfb5b68f8c5495ee7bd8.jpg",
            "http://pic47.nipic.com/20140830/7487939_180041822000_2.jpg",
            "http://pic41.nipic.com/20140518/4135003_102912523000_2.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1133260524,1171054226&fm=21&gp=0.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c0f1f6e9efff2b21192138ac0.jpg",
            "http://pic42.nipic.com/20140618/9448607_210533564001_2.jpg",
            "http://pic10.nipic.com/20101027/3578782_201643041706_2.jpg",
            "http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1375841395686.jpg",
            "http://cdn.duitang.com/uploads/item/201311/03/20131103171224_rr2aL.jpeg",
            "http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1210/17/c1/spcgroup/14468225_1350443478079_1680x1050.jpg",
            "http://pic41.nipic.com/20140518/4135003_102025858000_2.jpg",
            "http://www.1tong.com/uploads/wallpaper/landscapes/200-4-730x456.jpg",
            "http://pic.58pic.com/58pic/13/00/22/32M58PICV6U.jpg",
            "http://h.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=429e7b1b92ef76c6d087f32fa826d1cc/7acb0a46f21fbe09cc206a2e69600c338744ad8a.jpg",
            "http://pica.nipic.com/2007-12-21/2007122115114908_2.jpg",
            "http://cdn.duitang.com/uploads/item/201405/13/20140513212305_XcKLG.jpeg",
            "http://photo.loveyd.com/uploads/allimg/080618/1110324.jpg",
            "http://img4.duitang.com/uploads/item/201404/17/20140417105820_GuEHe.thumb.700_0.jpeg",
            "http://cdn.duitang.com/uploads/item/201204/21/20120421155228_i52eX.thumb.600_0.jpeg",
            "http://img4.duitang.com/uploads/item/201404/17/20140417105856_LTayu.thumb.700_0.jpeg",
            "http://img04.tooopen.com/images/20130723/tooopen_20530699.jpg",
            "http://pic.dbw.cn/0/01/33/59/1335968_847719.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/a8773912b31bb051a862339c337adab44bede0c4.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0feeea8a30f5e6034a85edf720f.jpg",
            "http://img0.pconline.com.cn/pconline/bizi/desktop/1412/ER2.jpg",
            "http://pic.58pic.com/58pic/11/25/04/91v58PIC6Xy.jpg",
            "http://img3.3lian.com/2013/c2/32/d/101.jpg",
            "http://pic25.nipic.com/20121210/7447430_172514301000_2.jpg",
            "http://img02.tooopen.com/images/20140320/sy_57121781945.jpg",
    };


    public void setOnStateChanged(OnStateChanged onStateChanged) {
        this.stateChanged = onStateChanged;
    }

    interface OnStateChanged {
        void isChanged(boolean isChanged);
    }
//    private List<String> getJasonString(String url) {
//        String result = "";
//        try {
//            result = resultString(new URL(url).openStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return praseJasonData(result);
//    }
//
//    private List<String> praseJasonData(String result) {
//        List<String> newsBeanList = new ArrayList<>();
//        try {
//            JSONArray json = new JSONArray(result);
//            for (int i = 0; i < json.length(); i++) {
//                JSONObject object = json.getJSONObject(i);
//                JSONObject data = object.getJSONObject("data");
//                String NewIcon = data.getString("icon_url");
//                String NewTitle = data.getString("name");
//                String NewContent = data.getString("reviews");
////                NewBean news = new NewBean(NewTitle, NewContent, NewIcon);
//                newsBeanList.add(NewIcon);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return newsBeanList;
//    }
//
//    private String resultString(InputStream is) {
//        String result = "";
//        try {
//            String line = "";
//            InputStreamReader isr = new InputStreamReader(is, "utf-8");
//            BufferedReader br = new BufferedReader(isr);
//            while ((line = br.readLine()) != null) {
//                result += line;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//
//    class NewsAsycTask extends AsyncTask<String, Void, List<String>> {
//        @Override
//        protected List<String> doInBackground(String... params) {
//            return getJasonString(params[0]);
//        }
//
//        @Override
//        protected void onPostExecute(List<String> newsBeen) {
//            super.onPostExecute(newsBeen);
//            adapter = new ImageAdapter(newsBeen, MainActivity.this);
//            gv.setAdapter(adapter);
//        }
//    }
//

}
