package com.tdil.naturalesexamen1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tdil.examen.Messages;
import com.tdil.examen.model.MultipleWordQuestion;
import com.tdil.examen.model.Questionnaire;

public class MultipleWordActivity extends QuestionActivity {

	private MultipleWordQuestion question;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multiplewordquestion);
		Bundle extras = getIntent().getExtras();
		questionnaire = (Questionnaire) extras.getSerializable(QUESTIONNAIRE);
		question = (MultipleWordQuestion)questionnaire.getQuestion();
		((TextView)findViewById(R.id.questionText)).setText(question.getQuestionText());

		
		List<EditText> editTexts = getEditTexts();
		for (int i = 0; i < question.getAnswers().size(); i++) {
			editTexts.get(i).setVisibility(View.VISIBLE);
		}
		for (int i = question.getAnswers().size(); i < 6; i++) {
			editTexts.get(i).setVisibility(View.GONE);
		}
		
		findViewById(R.id.continueButton).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Set<String> result = getResult();
					if (!question.correctResult(result)) {
						Messages.errorAnswerMessage(MultipleWordActivity.this, new HandleNextListener(MultipleWordActivity.this, questionnaire));
					} else {
						questionnaire.addCorrect();
						HandleNextListener.advance(MultipleWordActivity.this, questionnaire);
					}
				}

				private Set<String> getResult() {
					Set<String> result = new HashSet<String>();
					List<EditText> editTexts = getEditTexts();
					for (int i = 0; i < question.getAnswers().size(); i++) {
						result.add(editTexts.get(i).getText().toString());
					}
					return result;
				}
			});
		
	}

	private List<EditText> getEditTexts() {
		List<EditText> result = new ArrayList<EditText>();
		result.add(((EditText)findViewById(R.id.answer1EditText)));
		result.add(((EditText)findViewById(R.id.answer2EditText)));
		result.add(((EditText)findViewById(R.id.answer3EditText)));
		result.add(((EditText)findViewById(R.id.answer4EditText)));
		result.add(((EditText)findViewById(R.id.answer5EditText)));
		result.add(((EditText)findViewById(R.id.answer6EditText)));
		return result;
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
