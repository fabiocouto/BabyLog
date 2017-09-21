package view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import babylog.com.babylog.R;

/**
 * Created by fabiodocoutooliveira on 9/14/17.
 */

public class MainActivity extends AppCompatActivity {

    private Button btnNewBreastFeeding;
    private Button btnViewAllHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFields();
    }

    protected void initFields(){
        btnNewBreastFeeding = (Button) findViewById(R.id.buttonNewLog);
        btnViewAllHistory = (Button) findViewById(R.id.buttonViewLogs);
        buttonNewBrastFeedingActionListener();
        buttonViewAllHistoryActionListener();

    }

    protected void buttonNewBrastFeedingActionListener(){
        btnNewBreastFeeding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewActivityBreastFeeding();
            }
        });
    }

    protected void buttonViewAllHistoryActionListener(){
        btnViewAllHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showActivityAllHistory();
            }
        });
    }

    protected void showNewActivityBreastFeeding(){
        Toast.makeText(getApplicationContext(), "It worked!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), NewBreastFeedingActivity.class);
        startActivity(intent);
    }

    protected void showActivityAllHistory(){

        Toast.makeText(getApplicationContext(), "It worked too!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ListAllHistoryActivity.class);
        startActivity(intent);
    }


}
