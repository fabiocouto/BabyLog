package view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import babylog.com.babylog.R;
import dao.ActivityLogDAO;
import model.ActivityLog;
import utils.StringConverter;

public class NewBreastFeedingActivity extends AppCompatActivity {


    private ImageButton imgBtnStart;
    private ImageButton imgBtnPause;
    private Button btnSave;
    private EditText edtTextdescription;
    private Chronometer chronometer;
    private Long timeWhenStopped;
    private Long timeToResume;
    private Long miliseconds;
    private Long seconds;
    private final Integer ONE = 1;
    private final Integer ZERO = 0;
    private final Long ONE_MINUTE_LONG = 1l;
    private final Integer SECONDS_IN_A_MINUTE = 60;
    private final Integer MINUS_ONE = -1;
    private final String PLATFORM = "ANDROID";
    private final String GUEST_USER = "GUEST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_brast_feeding);
        initFields();
    }


    protected void initFields(){
        seconds = Long.valueOf(MINUS_ONE);
        timeWhenStopped = Long.valueOf(ZERO);
        timeToResume = Long.valueOf(ZERO);
        miliseconds = Long.valueOf(ZERO);
        edtTextdescription = (EditText) findViewById(R.id.editTextDescriptionActivity);
        edtTextdescription.setImeOptions(EditorInfo.IME_ACTION_DONE);
        edtTextdescription.setRawInputType(InputType.TYPE_CLASS_TEXT);
        imgBtnStart = (ImageButton) findViewById(R.id.imageButtonStart);
        imgBtnPause = (ImageButton) findViewById(R.id.imageButtonPause);
        btnSave = (Button) findViewById(R.id.buttonSaveNewLog);
        chronometer = (Chronometer) findViewById(R.id.chronometer2);
        buttonSaveBrastFeedingActionListener();
        imgButtonStartActionListener();
        imgButtonPauseActionListener();

    }

    protected void imgButtonStartActionListener(){
        imgBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChronometer();
            }
        });
    }

    protected void imgButtonPauseActionListener(){
        imgBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChronometer();
                timeWhenStopped = miliseconds;
                seconds = (timeWhenStopped/1000 % SECONDS_IN_A_MINUTE);
                timeToResume = Long.valueOf(seconds);
            }
        });
    }

    protected void buttonSaveBrastFeedingActionListener(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isEditTextDescriptionFilled()) {

                    stopChronometer();
                    StringConverter strConverter = new StringConverter();
                    ActivityLogDAO dao = new ActivityLogDAO(getApplicationContext());
                    Integer activityId = dao.generateActivityLogId();
                    ActivityLog activity1 = new ActivityLog();
                    activity1.setIdActivity(activityId);
                    activity1.setTitle(strConverter.generateActivityLogTitle(activityId));
                    activity1.setDescription(edtTextdescription.getText().toString());
                    activity1.setTimeSpent(convertToMinuteFromSecond(seconds));
                    activity1.setLocalDate(strConverter.getLocalDateTimeFormatted());
                    activity1.setLocalHour(strConverter.getLocalTimeFormatted());
                    activity1.setUsername(GUEST_USER);
                    activity1.setPlatform(PLATFORM);
                    activity1.setCategory("M");
                    dao.insertActivityLog(activity1);
                    dao.closeDataBase();
                    Toast.makeText(getApplicationContext(), R.string.message_success_activity_new_brast_feeding, Toast.LENGTH_SHORT).show();
                    showMainActivity();

                }  else {
                    showMessageErrorDialog();
                }

            }
        });
    }

    public ActivityLog getObjectForTesting(Integer id){
        ActivityLog activity2 = new ActivityLog();
        activity2.setIdActivity(id);
        activity2.setTitle("#2 Mamada");
        activity2.setDescription("New Description test");
        activity2.setTimeSpent(23L);
        activity2.setLocalHour("6am");
        return activity2;
    }

    protected Long convertToMinuteFromSecond(Long seconds){

        Long minute = seconds / SECONDS_IN_A_MINUTE;
        if(minute < ONE){
            return ONE_MINUTE_LONG;
        }
        return minute;
    }

    protected  void clearChronometer(){
        chronometer.setFormat(null);
    }

    protected void startChronometer(){
        chronometer.setBase(SystemClock.elapsedRealtime() - miliseconds);
        chronometer.start();
    }
    protected void stopChronometer(){
        miliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
        chronometer.stop();
    }

    protected Boolean isEditTextDescriptionFilled(){

        if (edtTextdescription.getText().toString().equals("")) {
            return false;
        } else{
            return  true;
        }
    }

    protected void showMessageErrorDialog(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(NewBreastFeedingActivity.this);
        dialogo.setTitle(R.string.title_alert_warning);
        dialogo.setMessage(R.string.message_no_filled_field);
        dialogo.setNeutralButton(R.string.neutral_button_ok, null);
        dialogo.show();
    }

    protected void chronometerActionListener(){
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
               timeWhenStopped = timeToResume;
            }
        });
    }


    protected void showMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    protected void showHelpActivity(){
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        startActivity(intent);
    }


    @Override
    public Intent getParentActivityIntent() {
        return getParentActivityIntentImpl();
    }

    private Intent getParentActivityIntentImpl() {

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return i;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_help) {
            showHelpActivity();
            Toast.makeText(getApplicationContext(), R.string.title_menu_help, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
