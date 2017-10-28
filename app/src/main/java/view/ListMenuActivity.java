package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import babylog.com.babylog.R;
import dao.ActivityLogDAO;
import utils.MenuEnum;

public class ListMenuActivity extends AppCompatActivity {

    private ListView menuListView ;
    private ArrayAdapter<String> listAdapter ;
    private final String BREASTFEED = "BREASTFEED";
    private final String FORMULA = "BREASTFEED";
    private final Integer BREASTFEED_POSITION = 0;
    private final Integer FORMULA_POSITION = 1;
    private String strIntent = "";
    private final Integer ZERO = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        initFields();
        listViewMenuActionListener();
    }


    protected void initFields(){
        menuListView = (ListView) findViewById( R.id.menuListView );
        String[] options = new String[] {MenuEnum.MAMADA.getDescricao(), MenuEnum.COMPLEMENTO.getDescricao()};
        ArrayList<String> optionsList = new ArrayList<String>();
        optionsList.addAll( Arrays.asList(options) );
        listAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.simple_row_menu, R.id.rowTextView, optionsList);
        menuListView.setAdapter( listAdapter );
        Intent mIntent = getIntent();
        strIntent = mIntent.getStringExtra("FROM_MAIN");
        if(strIntent == null){
            strIntent = mIntent.getStringExtra("FROM_HISTORY");
        }

    }

    protected void listViewMenuActionListener(){

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityLogDAO dao = new ActivityLogDAO(getApplicationContext());
                if (strIntent.equals("FROM_MAIN")) {
                    if(position == BREASTFEED_POSITION){
                        showActivityNewBreastFeeding();
                    } else {
                        showActivityNewFormula();
                    }

                } else {

                    if(position == FORMULA_POSITION){

                        if(dao.getAllLocalFormulaFeedingActivityLogs().size() == ZERO){
                            showMessageErrorDialog();
                        } else {
                            showActivityFormulaHistory();
                        }

                    } else {

                        if(dao.getAllLocalBreastFeedingActivityLogs().size() == ZERO){
                            showMessageErrorDialog();
                        } else {
                            showActivityBreastFeedingHistory();
                        }
                    }
                }
            }
        });
    }

    protected void showActivityBreastFeedingHistory(){
        Intent intent = new Intent(getApplicationContext(), ListAllHistoryActivity.class);
        intent.putExtra(BREASTFEED, BREASTFEED);
        startActivity(intent);
        finish();
    }

    protected void showActivityFormulaHistory(){
        Intent intent = new Intent(getApplicationContext(), ListAllFormulaHistoryActivity.class);
        intent.putExtra(FORMULA, FORMULA);
        startActivity(intent);
        finish();
    }

    protected void showActivityNewBreastFeeding(){
        Intent intent = new Intent(getApplicationContext(), NewBreastFeedingActivity.class);
        intent.putExtra(BREASTFEED, BREASTFEED);
        startActivity(intent);
        finish();
    }

    protected void showActivityNewFormula(){
        Intent intent = new Intent(getApplicationContext(), NewFormulaActivity.class);
        intent.putExtra(BREASTFEED, BREASTFEED);
        startActivity(intent);
        finish();
    }

    protected void showMessageErrorDialog(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(ListMenuActivity.this);
        dialogo.setTitle(R.string.title_alert_warning);
        dialogo.setMessage(R.string.message_no_activities);
        dialogo.setNeutralButton(R.string.neutral_button_ok, null);
        dialogo.show();
    }
}
