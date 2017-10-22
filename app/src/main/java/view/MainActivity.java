package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import babylog.com.babylog.R;
import dao.ActivityLogDAO;
import helper.DatebaseInit;

/**
 * Created by fabiodocoutooliveira on 9/14/17.
 */

public class MainActivity extends AppCompatActivity {

    private Button btnNewBreastFeeding;
    private Button btnViewAllHistory;
    private final Integer ZERO = 0;
    private  String FROM_MAIN = "FROM_MAIN";
    private  String FROM_HISTORY = "FROM_HISTORY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatebaseInit db = new DatebaseInit(getApplicationContext());
        initFields();
    }

    protected void initFields(){
        btnNewBreastFeeding = (Button) findViewById(R.id.buttonNewLog);
        btnViewAllHistory = (Button) findViewById(R.id.buttonViewLogs);
        buttonNewBreastFeedingActionListener();
        buttonViewAllHistoryActionListener();

    }

    protected void buttonNewBreastFeedingActionListener(){
        btnNewBreastFeeding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListMenuActivityFromMain();
            }
        });
    }

    protected void buttonViewAllHistoryActionListener(){
        btnViewAllHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityLogDAO dao = new ActivityLogDAO(getApplicationContext());

                if(dao.getAllLocalActivityLogs().size() == ZERO){
                    showMessageErrorDialog();
                } else {
                    showListMenuActivityFromHistory();
                }

            }
        });
    }

    protected void showListMenuActivityFromMain(){
        Intent intent = new Intent(getApplicationContext(), ListMenuActivity.class);
        intent.putExtra(FROM_MAIN, FROM_MAIN);
        startActivity(intent);
    }
    protected void showListMenuActivityFromHistory(){
        Intent intent = new Intent(getApplicationContext(), ListMenuActivity.class);
        intent.putExtra(FROM_HISTORY, FROM_HISTORY);
        startActivity(intent);
    }

    protected void showNewActivityBreastFeeding(){
        Intent intent = new Intent(getApplicationContext(), NewBreastFeedingActivity.class);
        startActivity(intent);
    }

    protected void showActivityAllHistory(){
        Intent intent = new Intent(getApplicationContext(), ListAllHistoryActivity.class);
        startActivity(intent);
    }

    protected void showMessageErrorDialog(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle(R.string.title_alert_warning);
        dialogo.setMessage(R.string.message_no_activities);
        dialogo.setNeutralButton(R.string.neutral_button_ok, null);
        dialogo.show();
    }


}
