package com.tdil.peugeotservice.android.utils;

import com.tdil.peugeotservice.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Messages {

	public static void connectionErrorMessage(Context activity) {
		new AlertDialog.Builder(activity)
		   .setIcon(R.drawable.ic_launcher)
		   .setTitle("Atenci�n")
		   .setMessage("No se pudo establecer correctamente la conexi�n")
		   .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int whichButton) {
		           }
		   }).show();
	}

}
