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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import adapter.ActivityListAdapter;
import babylog.com.babylog.R;
import model.ActivityLog;

public class ListAllHistoryActivity extends AppCompatActivity {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy - hh:mm a");
    private final static String ACTION = "NotifyServiceAction";
    private final String MESSAGE_ERROR_NOFILLED ="Campos obrigatórios. Favor verificar os dados e tentar novamente!";
    private final String MESSAGE_ERROR_AFTER_GET ="Opa! Ocorreu um erro ao realizar a busca. Tente novamente mais tarde!";
    private final String MESSAGE_OF_SUCCESS = "Comentário cadastrado com sucesso!";
    ListView lstVwActivityLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_history);
        initFields();
        setBikeSpotAdapter(getAllHistory());
        //configToolBar();
    }

    protected void initFields(){
        lstVwActivityLog = (ListView) findViewById(R.id.listViewActivityLog);
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



    public List<ActivityLog> getAllHistory(){
        List<ActivityLog> activityList = new ArrayList<>();
        try{

          //TODO get from DAO
            ActivityLog activity1 = new ActivityLog();
            activity1.setTitle("#1 Mamada");
            activity1.setDescription("Description test");
            activity1.setIdActivity(1);
            activity1.setTimeSpent(23);
            activity1.setLocalDate("20/09/2017");
            activity1.setLocalHour("6am");
            activity1.setUsername("Fabio");
            activity1.setPlatform("Android");
            activity1.setCategory("M");

            ActivityLog activity2 = new ActivityLog();
            activity2.setTitle("#2 Mamada");
            activity2.setDescription("Description test");
            activity2.setIdActivity(1);
            activity2.setTimeSpent(23);
            activity2.setLocalDate("20/09/2017");
            activity2.setLocalHour("6am");
            activity2.setUsername("Fabio");
            activity2.setPlatform("Android");
            activity2.setCategory("M");

            activityList.add(activity1);
            activityList.add(activity2);

        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), MESSAGE_ERROR_AFTER_GET, Toast.LENGTH_LONG).show();
        }

        return activityList;
    }


    public void setBikeSpotAdapter(final List<ActivityLog> activityLogList){

        ActivityListAdapter adapter = new ActivityListAdapter(this, R.layout.activity_list_all_history_itens, activityLogList);

        lstVwActivityLog.setAdapter(adapter);

        lstVwActivityLog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //TextView name = (TextView) findViewById(R.id.txtTitleBikeSpotItem);
                ActivityLog activityLog = activityLogList.get(position);
                //setBikeSpotDTOFinal(bikeSpotDTO);
                Intent logDetailsIntent = new Intent(ListAllHistoryActivity.this, LogDetailActivity.class);
                logDetailsIntent.putExtra("LogDetailKey", activityLog);


                //bikeSpotDetails.putExtra("bikeSpotDTO", bikeSpotDTO);
                startActivity(logDetailsIntent);

            }
        });

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



/*
    public void configToolBar(){
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarHistoryList);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ListAllHistoryActivity.this.finish();
            }
        });

    }

*/
}
