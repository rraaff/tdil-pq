package com.tdil.examen;

import com.tdil.naturalesexamen1.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Messages {

	public static void errorAnswerMessage(Context activity, DialogInterface.OnClickListener listener) {
		new AlertDialog.Builder(activity)
		   .setIcon(R.drawable.ic_launcher)
		   .setTitle("Atención")
		   .setMessage("La respuesta es incorrecta")
		   .setPositiveButton("Ok", listener).show();
	}

}
