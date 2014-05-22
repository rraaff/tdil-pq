package com.tdil.naturalesexamen1;

import com.tdil.examen.model.Questionnaire;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

public class HandleNextListener implements DialogInterface.OnClickListener {

	private ActionBarActivity activity;
	private Questionnaire questionnaire;

	public HandleNextListener(ActionBarActivity activity,
			Questionnaire questionnaire) {
		super();
		this.activity = activity;
		this.questionnaire = questionnaire;
	}

	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		advance(activity, questionnaire);
	}


	public static void advance(ActionBarActivity activity, Questionnaire questionnaire) {
		questionnaire.advance();
		if (questionnaire.isFinished()) {
			Intent intent = new Intent(activity.getBaseContext(), ResultActivity.class);
			intent.putExtra(QuestionActivity.QUESTIONNAIRE, questionnaire);
			activity.startActivity(intent);
			activity.finish();
		} else {
			if (questionnaire.nextQuestionIsTrueFalse()) {
				Intent intent = new Intent(activity.getBaseContext(), TrueFalseActivity.class);
				intent.putExtra(QuestionActivity.QUESTIONNAIRE, questionnaire);
				activity.startActivity(intent);
				activity.finish();
			} else {
				Intent intent = new Intent(activity.getBaseContext(), OptionActivity.class);
				intent.putExtra(QuestionActivity.QUESTIONNAIRE, questionnaire);
				activity.startActivity(intent);
				activity.finish();
			}
		}
	}
}
