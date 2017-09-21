package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import babylog.com.babylog.R;
import model.ActivityLog;

public class LogDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_detail);
        Intent mIntent = getIntent();
        ActivityLog activityLog = (ActivityLog) mIntent.getParcelableExtra("LogDetailKey");
        Log.d("DEBUG", "-" + activityLog.toString());
        TextView textView = (TextView) findViewById(R.id.textViewLogDetailTitle);
        textView.setText("Test: " + activityLog.getLocalDate().toString());
    }
}
