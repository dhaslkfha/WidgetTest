# WidgetTest
a little Widget to showing oil price. refresh one day a time
#油气价格的小组件
用接口实现获取实时油价，在OilProvider中要设置自己找到的油价免费接口；
继承 AppWidgetProvider
#实现每日励志金句的小组件
内容写死在程序里，也可以用接口的方式后台获取；

#不在桌面添加应用图标
在Manifest中MainActivity的intent-filter添加
<data
                    android:host="MainActivity"
                    android:scheme="com.test.myapplication"
                    tools:ignore="AppLinkUrlError" />
                    这样不会隐式声明启动方式，不会在桌面创建图标，goolge等应用服务估计也是这么做
                    
#xml文件配置
两个组建要在res下建一个xml文件夹，存放两个widget的配置文件
<appwidget-provider xmlns:android="http://schemas.android.com/apk/res/android"

    android:minHeight="80dp"
    android:minWidth="300dp"
    android:previewImage="@drawable/ic_launcher_background"//组建在预览中的图标
    android:initialLayout="@layout/oilwidget"//关联布局
    android:updatePeriodMillis="86400000"//更新组建时间，毫秒计算，这里1天，最少30分钟
    android:resizeMode="horizontal|vertical"
    android:widgetCategory="home_screen">
</appwidget-provider>

#Manifest中添加recever

<receiver android:name=".widget.OilProvider">
            <intent-filter>
                <action android:name="android.appwidget.action.APPWIDGET_UPDATE" /><!--固定-->
                <action android:name="com.test.myapplication.click" /><!--定义自己组建上的点击事件，没有可不要-->
            </intent-filter>

            <meta-data
                android:name="android.appwidget.provider"<!--固定-->
                android:resource="@xml/widget_provider" /><!--与xml中配置关联-->
        </receiver>
        
        
