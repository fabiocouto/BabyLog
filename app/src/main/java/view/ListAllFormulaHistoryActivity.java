package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.ActivityListAdapterFormula;
import babylog.com.babylog.R;
import dao.ActivityLogDAO;
import model.ActivityLog;

public class ListAllFormulaHistoryActivity extends AppCompatActivity {

    private final String MESSAGE_ERROR_AFTER_GET ="Opa! Ocorreu um erro ao realizar a busca. Tente novamente mais tarde!";
    private ListView lstVwActivityLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_formula_history);
        initFields();
        setFormulaHistoryAdapter(getAllHistory());
    }


    protected void initFields(){
        lstVwActivityLog = (ListView) findViewById(R.id.listViewFormulaActivityLog);
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



    public List<ActivityLog> getAllHistory(){
        List<ActivityLog> activityList = new ArrayList<>();
        try{
            ActivityLogDAO dao = new ActivityLogDAO(getApplicationContext());
            activityList = dao.getAllLocalFormulaFeedingActivityLogs();
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), MESSAGE_ERROR_AFTER_GET, Toast.LENGTH_LONG).show();
        }
        return activityList;
    }


    public void setFormulaHistoryAdapter(final List<ActivityLog> activityLogList){

        ActivityListAdapterFormula adapter = new ActivityListAdapterFormula(this, R.layout.activity_list_all_formula_history_itens, activityLogList);
        lstVwActivityLog.setAdapter(adapter);
        lstVwActivityLog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ActivityLog activityLog = activityLogList.get(position);
                Intent logDetailsIntent = new Intent(ListAllFormulaHistoryActivity.this, LogDetailActivity.class);
                logDetailsIntent.putExtra("LogDetailKeyFormula", activityLog);
                startActivity(logDetailsIntent);
                //finish();

            }
        });

    }
    protected void showHelpActivity(){
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        startActivity(intent);
        finish();
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
