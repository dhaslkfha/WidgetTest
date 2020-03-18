package com.test.myapplication.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.test.myapplication.R;

import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class EveryDayWordsWidget extends AppWidgetProvider {
    public static String[] strs = new String[]{
            "一个今天抵得上两个明天。撕一张日历，很简单，把握住一天，却不容易。",
            "一路走来，太累了，停下来歇歇吧，多休息一会，是为了走更远的路",
            "不想当将军的士兵不是好士兵，但是一个当不好士兵的将军一定不是好将。",
            "不经巨大的困难，不会有伟大的事业。",
            "不要笑话别人身上的疤，因为那可能只是你没受过或还没受过的伤",
            "世上总有几个这样的人，他们对生活要求很高，对自己的愚蠢和粗野又不甘心。",

            "为了生存，我要维护身体健康，身体健康不但对自己有利，也让朋友家人放心，所以也是孝亲的行为。",

            "人世间所有的相遇，都是久别重逢。而每一次分开可能今生再也不相见。珍惜当下，别纠结过去，别贪想未来。",

            "人最可悲的是，有自由的思想，却没有冲破羁绊的勇气。",

            "人生像一杯茶不会苦一辈子但会苦一阵子!",

            "人生寂寞是一种力量。人经得起寂寞，就能获得自由;耐不住寂寞，就会受人牵制",

            "人生是一次旅行，有理想和追求的人永远年青。",

            "人生没有再来一次的机会，不想后悔，那就，跟看自己的心走。",

            "人生真的很多繁琐事，所谓鱼与熊掌不可兼得。",

            "人的一生，应当像这美丽的花，自己无所求，而却给人间以美。",

            "从绝望中寻找希望，人生终将辉煌。",

            "你不能改变过去，但你可以改变未来。",

            "你永远要感谢给你逆境的众生。",

            "信念之所以宝贵，只是因为它是现实了的，而决不是因为它是我们的。",

            "充沛的精力加上顽强的决心，曾经创造出许多奇迹。",

            "凡事都不能无中生有，实现愿望一定是要有补偿，或者说是付出代价。",

            "前进的路也许会很迷茫，但请不要在迷茫中失去自我，失去前进的信念。",

            "勤奋，坚持到底!相信自己，一切皆有可能。",

            "友谊的花朵需要联系来浇灌，感情的种子需要联络来营养，和谐的社会需要短信来慰问，淡定生活，促进和谐，活并快乐着!",

            "只要努力一定会能够实现自己的梦想，也没有什么能够阻挡我力争上游的步伐，相信自己，我一直是最棒!",

            "命运，你残忍的诉说着我的悲痛。",

            "在任何行业中，走向成功的第一步，是对它产生兴趣。-威廉?奥斯勒爵士",

            "坚持就是胜利。胜利不重要，重要的是能坚持。",

            "失望多了，期望就少了。",

            "如果人生是一场修行，一定找一个善良的人同行。",

            "如果把才华比作剑那么勤奋就是磨刀石。",

            "学到很多东西的诀窍，就是一下子不要学很多。",

            "对自己要有信心，失败是向成功靠近一步，失败了不要后悔。",

            "希望是生命的源泉，失去它生命就会枯萎。",

            "张扬梦想，自信青春。你我同是天使的翅膀。",

            "当初有胆量去选，同样该有勇气承受后果。",

            "心是个口袋，东西装的少时叫心灵，多一点儿时叫心眼。",

            "性命很短暂，别把那些重要的话憋着。",

            "懂得珍惜，才配拥有;知道感恩，才能长久!",

            "成功的秘诀第一个是坚持到底，永不放弃;第二个就是当你想放弃的时候，再照着第一个秘诀去做：坚持到底，永不放弃。",

            "我们不怕不懂，就怕装懂;不怕不足，就怕满足。",

            "我可以接受挫折，打击，讽刺，嘲笑，蔑视，甚至失败;但我唯一不能接受的就是放弃!",

            "我进入梦想，怀念现实，真好。",

            "抬起头，踮踮脚，离阳光又近了一分。",

            "敢于尝试，就等于你已经向成功迈出了第一步。",

            "时钟可以指向原点，只是已不再是昨天。",

            "是非窝里，人用口，我用耳;热闹场中，人向前，我落后。",

            "有些人，说好忘记，却做不到;有些感情，走了一圈，可最终还是会回到原点。",

            "有的时候，几个馒头就可以过好长时间;有的时候，一堆金条反而会把日子过的乱七八糟。",

            "欲知过去世，今生受者是。欲知来世果，今生做者是。",

            "每天都有精彩，每天都在演奏。让动听的校园交响乐留住你的脚步，抚平你的心绪，带你走过一段长途，走出一路美丽。",

            "没有人会因为你秘而不宣的思想而记住你。",

            "活得愉悦在己，活得长久在天。豁达，不是没有烦恼，而是懂得放下;世间无完美，曲折亦风景，心通一切通，修好此心，才能成功。",

            "现在的我你爱理不理。记住未来的我，你高攀不起。",

            "生活不是单行线，一条路走不通，你还可以转弯。",

            "用今天的泪播种，收获明天的微笑。",

            "相信自己，你能作茧自缚，就能破茧成蝶。",

            "知识是个封闭的富矿，打开它的钥匙是持之以恒的努力。",

            "累了，就要休息，休息好了之后，把所的都忘掉，重新开始!",

            "能够比跌倒的次数多一次站起来的次数，你就是强者!",

            "苦想没盼头，苦干有奔头。",

            "要想改变我们的人生，第一步就是要改变我们的心态，只要心态是正确的，我们的世界就会的光明的。",

            "记住，输掉的东西，一定可以再一点一点赢回来!",

            "走人生的路程就像爬山一样，看起来走了许多冤枉的路，崎岖的路，但终于到达山顶。",
    };

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.every_day_words_widget);
        views.setTextViewText(R.id.appwidget_text, strs[new Random().nextInt(strs.length)]);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

