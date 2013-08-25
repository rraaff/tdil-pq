package com.tdil.thalamus.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.tdil.lojack.rl.R;
import com.tdil.lojack.rl.R.drawable;

public class Messages {

	public static void connectionErrorMessage(Activity activity) {
		new AlertDialog.Builder(activity)
		   .setIcon(R.drawable.ic_launcher)
		   .setTitle("Atención")
		   .setMessage("No se pudo establecer correctamente la conexión")
		   .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int whichButton) {
		           }
		   }).show();
	}

}
