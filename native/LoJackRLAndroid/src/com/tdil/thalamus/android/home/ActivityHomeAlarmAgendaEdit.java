package com.tdil.thalamus.android.home;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.components.MyTimePickerDialog;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.header.logic.HomeHeaderLogic;
import com.tdil.thalamus.android.rest.model.AlarmAgenda;

/**
 * Esta pagina maneja el listado de alarmas
 * 
 * @author mgodoy
 * 
 */
public class ActivityHomeAlarmAgendaEdit extends HomeActivity implements ValidationListener {

	public static final String AGENDA = "AGENDA";
	
	private AlarmAgenda alarmAgenda = null;
	
	@TextRule(order = 1, minLength = 1, maxLength = 100, message = "Ingrese la descripcion.")
	private EditText agendaDescription;
	
	private TextView dateFrom;
	private Calendar dateFromObj;

	private TextView dateTo;
	private Calendar dateToObj;

	private TextView agendaHourFrom;
	private int hourFrom = -1;
	private int minuteFrom = -1;
	private int secondFrom = -1;

	private TextView agendaHourTo;
	private int hourTo = -1;
	private int minuteTo = -1;
	private int secondTo = -1;
	
	private CheckBox agendaRepMonday;
	private CheckBox agendaRepTuesday;
	private CheckBox agendaRepWednesday;
	private CheckBox agendaRepThursday;
	private CheckBox agendaRepFriday;
	private CheckBox agendaRepSaturday;
	private CheckBox agendaRepSunday;
	
	private Validator validator;

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_lohome_alarm_agenda_edit);
		customizeActionBar(true);
		HeaderLogic.installTabLogic(this);
		HomeHeaderLogic.installHomeMenuLogic(this);
		Resources res = getResources();
		Bundle extras = getIntent().getExtras();
		
		// alarmListAdapter = new
		// AlarmAgendaListAdapter(ActivityHomeAlarmAgendaEdit.this,
		// alarms, res);
		// alarmsList = (ListView) findViewById(R.id.agendaList);
		// alarmsList.setAdapter(alarmListAdapter);
		
		agendaDescription = (EditText)findViewById(R.id.agendaName);
		
		dateFrom = (TextView) findViewById(R.id.agendaFrom);
		dateFrom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogFragment newFragment = new DateFromPickerFragment(ActivityHomeAlarmAgendaEdit.this);
				newFragment.show(getSupportFragmentManager(), "datePicker");

			}
		});

		dateTo = (TextView) findViewById(R.id.agendaTo);
		dateTo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogFragment newFragment = new DateToPickerFragment(ActivityHomeAlarmAgendaEdit.this);
				newFragment.show(getSupportFragmentManager(), "datePicker");

			}
		});

		agendaHourFrom = (TextView) findViewById(R.id.agendaHourFrom);
		agendaHourFrom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				final Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				int second = c.get(Calendar.SECOND);
				if (ActivityHomeAlarmAgendaEdit.this.hourFrom != -1 && ActivityHomeAlarmAgendaEdit.this.minuteFrom != -1 && ActivityHomeAlarmAgendaEdit.this.secondFrom != -1) {
					hour = ActivityHomeAlarmAgendaEdit.this.hourFrom;
					minute = ActivityHomeAlarmAgendaEdit.this.minuteFrom;
					second = ActivityHomeAlarmAgendaEdit.this.secondFrom;
				} 
				MyTimePickerDialog mTimePicker = new MyTimePickerDialog(ActivityHomeAlarmAgendaEdit.this, new MyTimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(com.tdil.thalamus.android.gui.components.TimePicker view, int hourOfDay, int minute, int seconds) {
						ActivityHomeAlarmAgendaEdit.this.agendaHourFrom.setText(String.valueOf(hourOfDay) + ":" + minute + ":" + seconds);
						ActivityHomeAlarmAgendaEdit.this.hourFrom = hourOfDay;
						ActivityHomeAlarmAgendaEdit.this.minuteFrom = minute;
						ActivityHomeAlarmAgendaEdit.this.secondFrom = seconds;
					}
				}, hour, minute, second, true);
				mTimePicker.show();

			}
		});
		
		agendaHourTo = (TextView) findViewById(R.id.agendaHourTo);
		agendaHourTo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				final Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				int second = c.get(Calendar.SECOND);
				if (ActivityHomeAlarmAgendaEdit.this.hourTo != -1 && ActivityHomeAlarmAgendaEdit.this.minuteTo != -1 && ActivityHomeAlarmAgendaEdit.this.secondTo != -1) {
					hour = ActivityHomeAlarmAgendaEdit.this.hourTo;
					minute = ActivityHomeAlarmAgendaEdit.this.minuteTo;
					second = ActivityHomeAlarmAgendaEdit.this.secondTo;
				} 
				MyTimePickerDialog mTimePicker = new MyTimePickerDialog(ActivityHomeAlarmAgendaEdit.this, new MyTimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(com.tdil.thalamus.android.gui.components.TimePicker view, int hourOfDay, int minute, int seconds) {
						ActivityHomeAlarmAgendaEdit.this.agendaHourTo.setText(String.valueOf(hourOfDay) + ":" + minute + ":" + seconds);
						ActivityHomeAlarmAgendaEdit.this.hourTo = hourOfDay;
						ActivityHomeAlarmAgendaEdit.this.minuteTo = minute;
						ActivityHomeAlarmAgendaEdit.this.secondTo = seconds;
					}
				}, hour, minute, second, true);
				mTimePicker.show();

			}
		});
		agendaRepMonday = (CheckBox)findViewById(R.id.agendaRepMonday);
		agendaRepTuesday = (CheckBox)findViewById(R.id.agendaRepTuesday);
		agendaRepWednesday = (CheckBox)findViewById(R.id.agendaRepWednesday);
		agendaRepThursday = (CheckBox)findViewById(R.id.agendaRepThursday);
		agendaRepFriday = (CheckBox)findViewById(R.id.agendaRepFriday);
		agendaRepSaturday = (CheckBox)findViewById(R.id.agendaRepSaturday);
		agendaRepSunday = (CheckBox)findViewById(R.id.agendaRepSunday);
		
		if (extras != null && extras.containsKey(AGENDA)) {
			alarmAgenda = (AlarmAgenda)extras.getSerializable(AGENDA);
		}
		if (alarmAgenda != null) {
			try {
				agendaDescription.setText(alarmAgenda.getDescription());
				dateFrom.setText(alarmAgenda.getFrom());
				dateFromObj = Calendar.getInstance();
				dateFromObj.setTime(SIMPLE_DATE_FORMAT.parse(alarmAgenda.getFrom()));
				dateTo.setText(alarmAgenda.getTo());
				dateToObj = Calendar.getInstance();
				dateToObj.setTime(SIMPLE_DATE_FORMAT.parse(alarmAgenda.getTo()));
				agendaHourFrom.setText(alarmAgenda.getActivateTime());
				String arr[] = alarmAgenda.getActivateTime().split(":");
				hourFrom = Integer.parseInt(arr[0]);
				minuteFrom = Integer.parseInt(arr[1]);
				secondFrom = Integer.parseInt(arr[2]);
				agendaHourTo.setText(alarmAgenda.getDeactivateTime());
				String arr1[] = alarmAgenda.getDeactivateTime().split(":");
				hourTo = Integer.parseInt(arr1[0]);
				minuteTo = Integer.parseInt(arr1[1]);
				secondTo = Integer.parseInt(arr1[2]);
				agendaRepMonday.setChecked(alarmAgenda.monday());
				agendaRepTuesday.setChecked(alarmAgenda.tuesday());
				agendaRepWednesday.setChecked(alarmAgenda.wednesday());
				agendaRepThursday.setChecked(alarmAgenda.thursday());
				agendaRepFriday.setChecked(alarmAgenda.friday());
				agendaRepSaturday.setChecked(alarmAgenda.saturday());
				agendaRepSunday.setChecked(alarmAgenda.sunday());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		validator = new Validator(this);
	    validator.setValidationListener(this);
		setupValidations();
		findViewById(R.id.agendaSave).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					validator.validate();
				}
			});
	}
	
	private void setupValidations() {
//		validator.put(agendaDescription, new Rule<EditText>("Ingrese la descripcion (hasta 100 caracteres)") {
//			public boolean isValid(EditText view) {
//				String text = view.getText().toString();
//				if (text.length() == 0) {
//					return false;
//				}
//				if (text.length() > 100) {
//					return false;
//				}
//				return true;
//			};
//		});
		validator.put(dateFrom, new Rule<TextView>("Seleccione la fecha de inicio") {
			public boolean isValid(TextView view) {
				return ActivityHomeAlarmAgendaEdit.this.dateFromObj != null;
			};
		});
		validator.put(dateFrom, new Rule<TextView>("La fecha de inicio debe ser anterior a la de fin") {
			public boolean isValid(TextView view) {
				if (ActivityHomeAlarmAgendaEdit.this.dateFromObj == null) {
					return true;
				}
				if (ActivityHomeAlarmAgendaEdit.this.dateToObj == null) {
					return true;
				}
				int compare = ActivityHomeAlarmAgendaEdit.this.dateFromObj.compareTo(ActivityHomeAlarmAgendaEdit.this.dateToObj);
				if (compare > 0) {
					return false;
				} else {
					return true;
				}
			};
		});
		validator.put(dateTo, new Rule<TextView>("Seleccione la fecha de fin") {
			public boolean isValid(TextView view) {
				return ActivityHomeAlarmAgendaEdit.this.dateToObj != null;
			};
		});
		validator.put(agendaHourFrom, new Rule<TextView>("Seleccione la hora de inicio") {
			public boolean isValid(TextView view) {
				return ActivityHomeAlarmAgendaEdit.this.hourFrom != -1 && ActivityHomeAlarmAgendaEdit.this.minuteFrom != -1;
			};
		});
		validator.put(agendaHourTo, new Rule<TextView>("Seleccione la hora de fin") {
			public boolean isValid(TextView view) {
				return ActivityHomeAlarmAgendaEdit.this.hourTo != -1 && ActivityHomeAlarmAgendaEdit.this.minuteTo != -1;
			};
		});
		
		validator.put(agendaRepMonday, new Rule<CheckBox>("Seleccione al menos un dia") {
			public boolean isValid(CheckBox view) {
				if (agendaRepMonday.isChecked()) {
					return true;
				}
				if (agendaRepTuesday.isChecked()) {
					return true;
				}
				if (agendaRepWednesday.isChecked()) {
					return true;
				}
				if (agendaRepThursday.isChecked()) {
					return true;
				}
				if (agendaRepFriday.isChecked()) {
					return true;
				}
				if (agendaRepSaturday.isChecked()) {
					return true;
				}
				if (agendaRepSunday.isChecked()) {
					return true;
				}
				return false;
			};
		});
		
	}

	@Override
    public void preValidation() {
    }
    @Override
    public void onFailure(View failedView, Rule<?> failedRule) {
    	 String message = failedRule.getFailureMessage();
         if (failedView instanceof EditText) {
             failedView.requestFocus();
             ((EditText) failedView).setError(message);
         } else {
             Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
         }
    }
    @Override
    public void onSuccess() {
    	//updatePerson();
    	Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onValidationCancelled() {
    }

	@Override
	public boolean isAlarmsTab() {
		return true;
	}

	@Override
	public boolean isCamerasTab() {
		return false;
	}

	@Override
	public boolean isLightsTab() {
		return false;
	}

	public static class DateFromPickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
		private ActivityHomeAlarmAgendaEdit activity;

		public DateFromPickerFragment(ActivityHomeAlarmAgendaEdit activity) {
			super();
			this.activity = activity;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			Calendar c = Calendar.getInstance();
			if (this.activity.dateFromObj != null) {
				c = this.activity.dateFromObj;
			}
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, day);
			this.activity.dateFromObj = cal;
			this.activity.dateFrom.setText(SIMPLE_DATE_FORMAT.format(cal.getTime()));
		}
	}

	public static class DateToPickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
		private ActivityHomeAlarmAgendaEdit activity;

		public DateToPickerFragment(ActivityHomeAlarmAgendaEdit activity) {
			super();
			this.activity = activity;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			Calendar c = Calendar.getInstance();
			if (this.activity.dateToObj != null) {
				c = this.activity.dateToObj;
			}
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, day);
			this.activity.dateToObj = cal;
			this.activity.dateTo.setText(SIMPLE_DATE_FORMAT.format(cal.getTime()));
		}
	}

//	public static class TimeFromPickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
//		private ActivityHomeAlarmAgendaEdit activity;
//		
//		public TimeFromPickerFragment(ActivityHomeAlarmAgendaEdit activity) {
//			super();
//			this.activity = activity;
//		}
//
//		@Override
//		public Dialog onCreateDialog(Bundle savedInstanceState) {
//			// TODO ver si se abrio o no o si fue edicion o no
//			final Calendar c = Calendar.getInstance();
//			int hour = c.get(Calendar.HOUR_OF_DAY);
//			int minute = c.get(Calendar.MINUTE);
//			if (this.activity.hourFrom != -1 && this.activity.minuteFrom != -1) {
//				hour = this.activity.hourFrom;
//				minute = this.activity.minuteFrom;
//			} 
//
//			// Create a new instance of TimePickerDialog and return it
//			return new TimePickerDialog(getActivity(), this, hour, minute, true);
//		}
//
//		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//			this.activity.hourFrom = hourOfDay;
//			this.activity.minuteFrom = minute;
//			this.activity.agendaHourFrom.setText(String.valueOf(hourOfDay) + ":" + minute);
//			
//		}
//	}
	
	public static class TimeToPickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
		private ActivityHomeAlarmAgendaEdit activity;
		
		public TimeToPickerFragment(ActivityHomeAlarmAgendaEdit activity) {
			super();
			this.activity = activity;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// TODO ver si se abrio o no o si fue edicion o no
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			if (this.activity.hourTo != -1 && this.activity.minuteTo != -1) {
				hour = this.activity.hourTo;
				minute = this.activity.minuteTo;
			} 

			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute, true);
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			this.activity.hourTo = hourOfDay;
			this.activity.minuteTo = minute;
			this.activity.agendaHourTo.setText(String.valueOf(hourOfDay) + ":" + minute);
			
		}
	}
}