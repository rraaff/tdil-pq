package com.tdil.naturalesexamen1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.tdil.examen.model.Questionnaire;

public abstract class QuestionActivity extends ActionBarActivity {

	public static String QUESTIONNAIRE = "QUESTIONNAIRE";
	
	protected Questionnaire questionnaire;

	protected void customizeActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(String.valueOf(questionnaire.getCurrentQuestion() + 1) + " / " + questionnaire.getQuestions().size());
	}
	
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}
	
}
