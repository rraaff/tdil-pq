package com.tdil.naturalesexamen1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tdil.examen.Messages;
import com.tdil.examen.model.Questionnaire;
import com.tdil.examen.model.TrueFalseQuestion;

public class TrueFalseActivity extends QuestionActivity {

	private TrueFalseQuestion question;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.truefalsequestion);
		
		
		Bundle extras = getIntent().getExtras();
		questionnaire = (Questionnaire) extras.getSerializable(QUESTIONNAIRE);
		question = (TrueFalseQuestion)questionnaire.getQuestion();
		customizeActionBar();
		((TextView)findViewById(R.id.questionText)).setText(question.getQuestionText());
		
		findViewById(R.id.trueButton).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (!question.correctResult()) {
						Messages.errorAnswerMessage(TrueFalseActivity.this, new HandleNextListener(TrueFalseActivity.this, questionnaire));
					} else {
						questionnaire.addCorrect();
						HandleNextListener.advance(TrueFalseActivity.this, questionnaire);
					}
				}
			});
		
		findViewById(R.id.falseButton).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (question.correctResult()) {
						Messages.errorAnswerMessage(TrueFalseActivity.this,new HandleNextListener(TrueFalseActivity.this, questionnaire));
					} else {
						questionnaire.addCorrect();
						HandleNextListener.advance(TrueFalseActivity.this, questionnaire);
					}
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			Intent intent = new Intent(this.getBaseContext(), AboutActivity.class);
			this.startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
