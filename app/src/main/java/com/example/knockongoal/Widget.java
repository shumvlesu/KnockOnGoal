package com.example.knockongoal;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;


public class Widget extends AppWidgetProvider {

    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Создаем новый RemoteViews
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

        //Подготавливаем Intent для Broadcast
        Intent active = new Intent(context, Widget.class);
        active.setAction(ACTION_WIDGET_RECEIVER);
        active.putExtra("msg", "Это кнопка 1");

        //создаем наше событие
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);

        //Подготавливаем Intent для Broadcast
        Intent active2 = new Intent(context, Widget.class);
        active2.setAction(ACTION_WIDGET_RECEIVER);
        active2.putExtra("msg", "Это кнопка 2");

        //создаем наше событие
        PendingIntent actionPendingIntent2 = PendingIntent.getBroadcast(context, 0, active2, 0);

        //регистрируем наше событие
        remoteViews.setOnClickPendingIntent(R.id.button1, actionPendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.button2, actionPendingIntent2);

        //обновляем виджет
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //Ловим наш Broadcast, проверяем и выводим сообщение
        final String action = intent.getAction();
        if (ACTION_WIDGET_RECEIVER.equals(action)) {
            String msg = "null";
            try {
                msg = intent.getStringExtra("msg");
            } catch (NullPointerException e) {
                Log.e("Error", "msg = null");
            }
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }

}
