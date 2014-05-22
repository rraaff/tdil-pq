package com.tdil.naturalesexamen1;

import android.support.v7.app.ActionBarActivity;

import com.tdil.examen.model.Questionnaire;

public abstract class QuestionActivity extends ActionBarActivity {

	public static String QUESTIONNAIRE = "QUESTIONNAIRE";
	
	protected Questionnaire questionnaire;

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}
	
}
