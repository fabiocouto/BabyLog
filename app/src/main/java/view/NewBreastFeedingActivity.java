package view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;

import babylog.com.babylog.R;
import dao.LocalStorageDAO;

public class NewBreastFeedingActivity extends AppCompatActivity {


    private ImageButton imgBtnStart;
    private ImageButton imgBtnStop;
    private Button btnSave;
    private Chronometer chronometer;
    private Long timeWhenStopped;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_brast_feeding);
        initFields();
    }


    protected void initFields(){
        imgBtnStart = (ImageButton) findViewById(R.id.imageButtonStart);
        imgBtnStop = (ImageButton) findViewById(R.id.imageButtonStop);
        btnSave = (Button) findViewById(R.id.buttonSaveNewLog);
        chronometer = (Chronometer) findViewById(R.id.chronometer2);
        chronometerActionListener();
        buttonSaveBrastFeedingActionListener();
        imgButtonStartActionListener();
        imgButtonStopActionListener();

    }

    protected void buttonSaveBrastFeedingActionListener(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO save in DAO;
                LocalStorageDAO dao = new LocalStorageDAO();

                Toast.makeText(getApplicationContext(), R.string.message_success_activity_new_brast_feeding, Toast.LENGTH_SHORT).show();
                showMainActivity();
            }
        });
    }

    protected void imgButtonStartActionListener(){
        imgBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chronometer.start();
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            }
        });
    }

    protected void imgButtonStopActionListener(){
        imgBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();
                timeWhenStopped = chronometer.getBase();
                Log.d("Time when Stopped", timeWhenStopped.toString());
            }
        });
    }

    protected void chronometerActionListener(){
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {

               // chronometer.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = chronometer.getBase();

            }

        });
    }


    protected void showMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public Intent getParentActivityIntent() {
        return getParentActivityIntentImpl();
    }

    private Intent getParentActivityIntentImpl() {

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        // i.putExtra("bikeSpotDTO", bikeSpotDTO);
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

        if (id == R.id.action_home) {
            Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
            //TODO
            return true;
        }
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Configurações!", Toast.LENGTH_SHORT).show();
            //TODO showConfigActivity();
            return true;
        }
        if (id == R.id.action_help) {
            Toast.makeText(getApplicationContext(), "Ajuda", Toast.LENGTH_SHORT).show();
            //TODOshowHelpActivity();
            return true;
        }
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Sair", Toast.LENGTH_SHORT).show();
            //TODO logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
