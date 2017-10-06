package view;

import android.content.DialogInterface;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import babylog.com.babylog.R;
import dao.ActivityLogDAO;
import model.ActivityLog;

public class LogDetailActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private EditText edtTextDescription;
    private EditText edtTextTimeSpent;
    private ImageButton imgButtonEdit;
    private ImageButton imgButtonDelete;
    private Button btnUpdateSave;
    private Integer idActivityTmp;
    protected ActivityLog activityLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_detail);
        initFields();
        Intent mIntent = getIntent();
        activityLog = mIntent.getParcelableExtra("LogDetailKey");
        textViewTitle.setText(activityLog.getTitle());
        edtTextDescription.setText(activityLog.getDescription());
        edtTextTimeSpent.setText(String.valueOf(activityLog.getTimeSpent()));
        idActivityTmp = activityLog.getIdActivity();
    }


    protected void initFields(){
        textViewTitle = (TextView) findViewById(R.id.textViewLogDetailTitle);
        edtTextDescription = (EditText) findViewById(R.id.editTextDescription);
        edtTextTimeSpent = (EditText) findViewById(R.id.editTextTimeSpent);
        imgButtonEdit = (ImageButton) findViewById(R.id.imageButtonEdit);;
        imgButtonDelete = (ImageButton) findViewById(R.id.imageButtonDelete);
        btnUpdateSave = (Button) findViewById(R.id.buttonUpdateSave);
        edtTextDescription.setEnabled(false);
        edtTextDescription.setImeOptions(EditorInfo.IME_ACTION_DONE);
        edtTextDescription.setRawInputType(InputType.TYPE_CLASS_TEXT);
        edtTextTimeSpent.setEnabled(false);
        setImgButtonEditActionListener();
        setImgButtonDeleteActionListener();
        buttonUpdateBrastFeedingActionListener();

    }

    protected void setImgButtonEditActionListener(){
        imgButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTextDescription.setEnabled(true);
                edtTextTimeSpent.setEnabled(true);

                if(edtTextDescription.getText().toString().length() == 0 || edtTextTimeSpent.getText().toString().length() == 0){
                    showMessageErrorDialog();
                } else {
                    activityLog.setDescription(edtTextDescription.getText().toString());
                    activityLog.setTimeSpent(Long.parseLong(edtTextTimeSpent.getText().toString()));
                }
            }
        });
    }

    protected void setImgButtonDeleteActionListener(){
        imgButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(LogDetailActivity.this);
                builder.setTitle(R.string.title_alert_atention);
                builder.setMessage(R.string.message_alert_activity_delete_brast_feeding);
                builder.setPositiveButton(R.string.positive_alert, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ActivityLogDAO activityLogDAO = new ActivityLogDAO(getApplicationContext());
                        activityLogDAO.deleteActivityLog(activityLog);
                        Toast.makeText(getApplicationContext(), R.string.message_success_activity_delete_brast_feeding, Toast.LENGTH_LONG).show();
                        showMainActivity();
                    }
                });
                builder.setNegativeButton(R.string.negative_alert, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });
    }


    protected void buttonUpdateBrastFeedingActionListener(){
        btnUpdateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO update in DAO;
                ActivityLogDAO dao = new ActivityLogDAO(getApplicationContext());
                dao.updateActivityLogWithParams(idActivityTmp, edtTextDescription.getText().toString(), edtTextTimeSpent.getText().toString());
                Toast.makeText(getApplicationContext(), R.string.message_success_activity_update_brast_feeding, Toast.LENGTH_SHORT).show();
                edtTextDescription.setEnabled(false);
                edtTextTimeSpent.setEnabled(false);
                showMainActivity();
            }
        });
    }

    public ActivityLog getObjectForUpdate(ActivityLog activityLog){
        ActivityLog activity2 = new ActivityLog();
        activity2.setIdActivity(activityLog.getIdActivity());
        activity2.setDescription(activityLog.getDescription());
        activity2.setTimeSpent(activityLog.getTimeSpent());
        activity2.setLocalHour(activityLog.getLocalHour());
        return activity2;
    }

    protected void showMessageErrorDialog(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(LogDetailActivity.this);
        dialogo.setTitle(R.string.title_alert_warning);
        dialogo.setMessage(R.string.message_no_filled_fields);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_help) {
            Toast.makeText(getApplicationContext(), R.string.title_menu_help, Toast.LENGTH_SHORT).show();
            showHelpActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
