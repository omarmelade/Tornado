package com.example.tornado.Util;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtil {

    // Permet de @override supprimer le nom de l'appli dans un toast
        public static void showToast(Context context, String message, int duration) {
            Toast toast = Toast.makeText(context, message, duration);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView tvMessage = (TextView) group.getChildAt(0);
            tvMessage.setText(message);
            tvMessage.setGravity(Gravity.CENTER);
            toast.show();
        }

}
