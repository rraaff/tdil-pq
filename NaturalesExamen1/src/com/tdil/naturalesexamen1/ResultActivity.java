package com.tdil.naturalesexamen1;

import java.text.DecimalFormat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tdil.examen.NaturalesMateriales;
import com.tdil.examen.model.Questionnaire;

public class ResultActivity extends ActionBarActivity {
	
	private Questionnaire questionnaire;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Bundle extras = getIntent().getExtras();
		Questionnaire questionnaire = (Questionnaire) extras.getSerializable(QuestionActivity.QUESTIONNAIRE);
		
		((TextView)findViewById(R.id.resultText)).setText("Resultado " + questionnaire.getCorrect() + "/" + questionnaire.getQuestions().size());
		
		float correct = questionnaire.getCorrect();
		float total = questionnaire.getQuestions().size();
		float result = correct / total * 10;
		
		((TextView)findViewById(R.id.finalNumber)).setText(new DecimalFormat("##.##").format(result));
		
		findViewById(R.id.redoButton).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(ResultActivity.this.getBaseContext(), MainActivity.class);
					ResultActivity.this.startActivity(intent);
					ResultActivity.this.finish();
				}
			});
		
		findViewById(R.id.exitButton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						ResultActivity.this.finish();
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

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

}
