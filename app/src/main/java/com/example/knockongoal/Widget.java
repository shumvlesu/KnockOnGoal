package com.example.knockongoal;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import static android.Manifest.permission.CALL_PHONE;
import static androidx.core.app.ActivityCompat.getPermissionCompatDelegate;
import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.content.ContextCompat.startActivity;


public class Widget extends AppWidgetProvider {

    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";
    public static String ACTION_WIDGET_RECEIVER2 = "ActionReceiverWidget2";
    public static String ACTION_WIDGET_RECEIVER3 = "ActionReceiverWidget3";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Создаем новый RemoteViews
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

        //Подготавливаем Intent для Broadcast
        Intent active = new Intent(context, Widget.class);
        active.setAction(ACTION_WIDGET_RECEIVER);
        active.putExtra("msg", "Калитка на рахманинова");

        //создаем наше событие
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);

        //Подготавливаем Intent для Broadcast
        Intent active2 = new Intent(context, Widget.class);
        active2.setAction(ACTION_WIDGET_RECEIVER2);
        active2.putExtra("msg", "Калитка на Есенина");

        //создаем наше событие
        PendingIntent actionPendingIntent2 = PendingIntent.getBroadcast(context, 0, active2, 0);

        //Подготавливаем Intent для Broadcast
        Intent active3 = new Intent(context, Widget.class);
        active3.setAction(ACTION_WIDGET_RECEIVER3);
        active3.putExtra("msg", "Ворота на рахманинова");

        //создаем наше событие
        PendingIntent actionPendingIntent3 = PendingIntent.getBroadcast(context, 0, active3, 0);

        //регистрируем наше событие
        remoteViews.setOnClickPendingIntent(R.id.button1, actionPendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.button2, actionPendingIntent2);
        remoteViews.setOnClickPendingIntent(R.id.button3, actionPendingIntent3);

        //обновляем виджет
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //Ловим наш Broadcast, проверяем и выводим сообщение
        final String action = intent.getAction();
        if (ACTION_WIDGET_RECEIVER.equals(action) || ACTION_WIDGET_RECEIVER2.equals(action) || ACTION_WIDGET_RECEIVER3.equals(action)){
            String msg = "null";
            try {
                msg = intent.getStringExtra("msg");

            } catch (NullPointerException e) {
                Log.e("Error", "msg = null");
            }

            //Этот интент собственно звонилка телефона.
            Intent i = new Intent(Intent.ACTION_CALL);

            //вот без этого флага задачи интент звонилки точно не запустится
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if (msg.equals("Калитка на рахманинова")) {
                i.setData(Uri.parse("tel:+79385162364"));
            }
            else if (msg.equals("Калитка на Есенина")) {
                i.setData(Uri.parse("tel:+79385162365"));
            }
            else if (msg.equals("Ворота на рахманинова")){
                i.setData(Uri.parse("tel:+79385159516"));
            }

            if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                //Здесь собственно интент и вызывает звонилку.
                context.startActivity(i);
            } else {
                //requestPermissions(new String[]{CALL_PHONE},1 );
            }
            //Говорим пользователю что он собственно открыл.
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }



}
