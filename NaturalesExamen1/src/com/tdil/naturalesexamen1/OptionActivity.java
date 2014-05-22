package com.tdil.naturalesexamen1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tdil.examen.Messages;
import com.tdil.examen.model.OptionQuestion;
import com.tdil.examen.model.Questionnaire;
import com.tdil.examen.model.TrueFalseQuestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class OptionActivity extends QuestionActivity {

	private OptionQuestion question;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.optionquestion);
		Bundle extras = getIntent().getExtras();
		questionnaire = (Questionnaire) extras.getSerializable(QUESTIONNAIRE);
		question = (OptionQuestion)questionnaire.getQuestion();
		((TextView)findViewById(R.id.questionText)).setText(question.getQuestionText());
		
		((CheckBox)findViewById(R.id.opt1)).setText(question.getOpt1());
		((CheckBox)findViewById(R.id.opt2)).setText(question.getOpt2());
		((CheckBox)findViewById(R.id.opt3)).setText(question.getOpt3());
		((CheckBox)findViewById(R.id.opt4)).setText(question.getOpt4());
		((CheckBox)findViewById(R.id.opt5)).setText(question.getOpt5());
		
		hideShowOption(R.id.opt1, question.getOpt1());
		hideShowOption(R.id.opt2, question.getOpt2());
		hideShowOption(R.id.opt3, question.getOpt3());
		hideShowOption(R.id.opt4, question.getOpt4());
		hideShowOption(R.id.opt5, question.getOpt5());
		
		findViewById(R.id.continueButton).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Set<String> result = getResult();
					if (!question.correctResult(result)) {
						Messages.errorAnswerMessage(OptionActivity.this, new HandleNextListener(OptionActivity.this, questionnaire));
					} else {
						questionnaire.addCorrect();
						HandleNextListener.advance(OptionActivity.this, questionnaire);
					}
				}

				private Set<String> getResult() {
					Set<String> result = new HashSet<String>();
					if (((CheckBox)findViewById(R.id.opt1)).isChecked()) {
						result.add(question.getOpt1());
					}
					if (((CheckBox)findViewById(R.id.opt2)).isChecked()) {
						result.add(question.getOpt2());
					}
					if (((CheckBox)findViewById(R.id.opt3)).isChecked()) {
						result.add(question.getOpt3());
					}
					if (((CheckBox)findViewById(R.id.opt4)).isChecked()) {
						result.add(question.getOpt4());
					}
					if (((CheckBox)findViewById(R.id.opt5)).isChecked()) {
						result.add(question.getOpt5());
					}
					return result;
				}
			});
		
	}

	private void hideShowOption(int id, String opt) {
		if (opt == null || opt.length() == 0) {
			((CheckBox)findViewById(id)).setVisibility(View.GONE);
		} else {
			((CheckBox)findViewById(id)).setVisibility(View.VISIBLE);
		}
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
