package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import babylog.com.babylog.R;
import dao.ActivityLogDAO;
import model.ActivityLog;
import utils.StringConverter;

public class NewFormulaActivity extends AppCompatActivity {

    private Button btnSave;
    private EditText edtTextdescription;
    private EditText edtTextMls;
    private TextView txtViewMls;
    private Long mls;
    private final String PLATFORM = "ANDROID";
    private final String GUEST_USER = "GUEST";
    private final Long ONE = 1l;
    private final Integer ZERO = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_formula);
        initFields();
    }


    protected void initFields(){
        mls = ONE;
        edtTextMls = (EditText) findViewById(R.id.editTextMls);
        edtTextdescription = (EditText) findViewById(R.id.editTextDescriptionActivity);
        edtTextdescription.setImeOptions(EditorInfo.IME_ACTION_DONE);
        edtTextdescription.setRawInputType(InputType.TYPE_CLASS_TEXT);
        txtViewMls = (TextView) findViewById(R.id.textViewMls);
        txtViewMls.setText(R.string.text_activity_formula_feeding);
        btnSave = (Button) findViewById(R.id.buttonSaveNewLog);
        buttonSaveFormulaFeedingActionListener();

    }


    protected void buttonSaveFormulaFeedingActionListener(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isEditTextDescriptionFilled()) {

                    StringConverter strConverter = new StringConverter();
                    ActivityLogDAO dao = new ActivityLogDAO(getApplicationContext());
                    Integer activityId = dao.generateFormulaActivityLogId();
                    ActivityLog activity1 = new ActivityLog();
                    activity1.setIdActivity(activityId);
                    activity1.setTitle(strConverter.generateActivityLogTitleForFormula(activityId));
                    activity1.setDescription(edtTextdescription.getText().toString());
                    activity1.setTimeSpent(mls);
                    activity1.setLocalDate(strConverter.getLocalDateTimeFormatted());
                    activity1.setLocalHour(strConverter.getLocalTimeFormatted());
                    activity1.setUsername(GUEST_USER);
                    activity1.setPlatform(PLATFORM);
                    activity1.setCategory("F");
                    dao.insertActivityLog(activity1);
                    dao.closeDataBase();
                    Toast.makeText(getApplicationContext(), R.string.message_success_activity_new_breast_feeding, Toast.LENGTH_SHORT).show();
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
        activity2.setTitle("#2 Formula");
        activity2.setDescription("New Description Formula");
        activity2.setTimeSpent(34L);
        activity2.setLocalHour("6am");
        activity2.setCategory("F");
        return activity2;
    }


    protected Boolean isEditTextDescriptionFilled(){
        if(edtTextMls.getText().toString().length() > ZERO) {
            mls = Long.parseLong(String.valueOf(edtTextMls.getText()));
        }
        if (edtTextdescription.getText().toString().equals("") || edtTextMls.getText().toString().equals("")) {
            return false;
        } if (mls <= ZERO){
            return false;
        } else{
            return  true;
        }
    }


    protected void showMessageErrorDialog(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(NewFormulaActivity.this);
        dialogo.setTitle(R.string.title_alert_warning);
        dialogo.setMessage(R.string.message_no_filled_field);
        dialogo.setNeutralButton(R.string.neutral_button_ok, null);
        dialogo.show();
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
