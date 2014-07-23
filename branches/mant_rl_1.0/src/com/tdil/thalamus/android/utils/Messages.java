package com.tdil.thalamus.android.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.tdil.lojack.rl.R;

public class Messages {

	public static void connectionErrorMessage(Context activity) {
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
