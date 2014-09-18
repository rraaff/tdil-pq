package com.tdil.naturalesexamen1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tdil.examen.Messages;
import com.tdil.examen.model.Questionnaire;
import com.tdil.examen.model.SimpleWordQuestion;

public class SimpleWordActivity extends QuestionActivity {

	private SimpleWordQuestion question;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplewordquestion);
		
		
		Bundle extras = getIntent().getExtras();
		questionnaire = (Questionnaire) extras.getSerializable(QUESTIONNAIRE);
		question = (SimpleWordQuestion)questionnaire.getQuestion();
		customizeActionBar();
		((TextView)findViewById(R.id.questionText)).setText(question.getQuestionText());
		
		findViewById(R.id.continueButton).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					String result = getResult();
					if (!question.correctResult(result)) {
						Messages.errorAnswerMessage(SimpleWordActivity.this, new HandleNextListener(SimpleWordActivity.this, questionnaire));
					} else {
						questionnaire.addCorrect();
						HandleNextListener.advance(SimpleWordActivity.this, questionnaire);
					}
				}

				private String getResult() {
					return ((EditText)SimpleWordActivity.this.findViewById(R.id.answerEditText)).getText().toString();
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
