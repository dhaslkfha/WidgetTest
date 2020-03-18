package com.test.myapplication.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.test.myapplication.MainActivity;
import com.test.myapplication.R;
import com.test.myapplication.httputil.HttpUtils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**********************************************************************************
 * @Copyright (C), 2018-2020.
 * @FileName: com.test.myapplication.widget.OilProvider
 * @Author:　 　　　
 * @Version : V1.0
 * @Date: 2020/3/5 15:57
 * @Description: 作用　油价组建
 * *********************************************************************************
 * History:　　　update past records <Author>　 <Date>　　 <Version>
 *
 * 修改内容：涉及文件：
 * *********************************************************************************
 */
public class OilProvider extends AppWidgetProvider {
    private static String p92 = "";
    private static String p95= "";
    private static String p98= "";
    private static String time= "";

    public  void doGet(Context context,AppWidgetManager appWidgetManager, int[] appWidgetIds,RemoteViews remoteViews) {
        String host = "https://ali-todayoil.showapi.com";
        String path = "/todayoil";
        String method = "GET";
        String appcode = "Your appcode";//TODO
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("prov", "重庆");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            Log.e("OIL",response.toString());
            String json = EntityUtils.toString(response.getEntity());
//            String json = "{\n" +
//                    "                  \"showapi_res_error\": \"\",\n" +
//                    "                  \"showapi_res_id\": \"8796dfad35bd4c14853a8dc3718d72df\",\n" +
//                    "                  \"showapi_res_code\": 0,\n" +
//                    "                  \"showapi_res_body\": {\"ret_code\":0,\"list\":[{\"prov\":\"重庆\",\"p90\":\"\",\"p0\":\"6.02\",\"p95\":\"6.76\",\"p97\":\"\",\"p98\":\"7.61\",\"p89\":\"6.36\",\"p92\":\"6.40\",\"ct\":\"2020-03-05 07:02:00.309\",\"p93\":\"\"}]}\n" +
//                    "                }\n";
            JSONObject object = new JSONObject(json);
            JSONObject body = object.getJSONObject("showapi_res_body");
            JSONArray list = body.getJSONArray("list");
            JSONObject oilObject  = (JSONObject) list.get(0);
            p92 = oilObject.getString("p92");
            p95 = oilObject.getString("p95");
            p98 = oilObject.getString("p98");
            time = oilObject.getString("ct");

            remoteViews.setTextViewText(R.id.text92,p92);
            remoteViews.setTextViewText(R.id.text95,p95);
            remoteViews.setTextViewText(R.id.text98,p98);
            remoteViews.setTextViewText(R.id.time,time.substring(0,10));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat() ;
            simpleDateFormat.applyPattern("yyyy-MM-dd  HH:mm:ss");
            remoteViews.setTextViewText(R.id.button, "刷新时间：" + simpleDateFormat.format(new Date()));
            //设置点击
            Intent intent = new Intent().setAction("com.test.myapplication.click");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.button,pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.oilwidget);
        new Thread(new Runnable() {
            @Override
            public void run() {
                doGet(context,appWidgetManager,appWidgetIds,remoteViews);
            }
        }).start();
        remoteViews.setTextViewText(R.id.text92,p92);
        remoteViews.setTextViewText(R.id.text95,p95);
        remoteViews.setTextViewText(R.id.text98,p98);
        //设置点击
        Intent intent = new Intent().setAction("com.test.myapplication.click");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        remoteViews.setOnClickPendingIntent(R.id.rootlayout,pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.oilwidget);
        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        final int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context,OilProvider.class));
//        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
        new Thread(new Runnable() {
            @Override
            public void run() {
                doGet(context,appWidgetManager,appWidgetIds,remoteViews);
            }
        }).start();

//        Toast.makeText(context,"onReceive："+intent.getAction(),Toast.LENGTH_LONG).show();
        if (intent.getAction().equals("com.test.myapplication.click")){
            Toast.makeText(context,"点击刷新",Toast.LENGTH_LONG).show();
        }

    }
}
