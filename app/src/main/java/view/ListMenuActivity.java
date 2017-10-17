package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import babylog.com.babylog.R;
import utils.MenuEnum;

public class ListMenuActivity extends AppCompatActivity {

    private ListView menuListView ;
    private ArrayAdapter<String> listAdapter ;
    private final String BREASTFEED = "BREASTFEED";
    private final String FORMULA = "BREASTFEED";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        menuListView = (ListView) findViewById( R.id.menuListView );
        String[] options = new String[] {MenuEnum.MAMADA.getDescricao(), MenuEnum.COMPLEMENTO.getDescricao()};
        ArrayList<String> optionsList = new ArrayList<String>();
        optionsList.addAll( Arrays.asList(options) );
        listAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_row_menu, R.id.rowTextView, optionsList);
        menuListView.setAdapter( listAdapter );
        listViewMenuActionListener();

    }


    protected void listViewMenuActionListener(){

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){

                    Toast.makeText(getApplicationContext(), "Pos ZERO = " + String.valueOf(position), Toast.LENGTH_LONG).show();
                    //showActivityBreastFeedingHistory();
                    showActivityNewFormula();

                }

                if(position == 1){
                    showActivityFormulaHistory();
                    Toast.makeText(getApplicationContext(), "Pos ONE = " + String.valueOf(position), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    protected void showActivityBreastFeedingHistory(){
        Intent intent = new Intent(getApplicationContext(), ListAllHistoryActivity.class);
        intent.putExtra(BREASTFEED, BREASTFEED);
        startActivity(intent);
    }

    protected void showActivityFormulaHistory(){
        Intent intent = new Intent(getApplicationContext(), ListAllHistoryActivity.class);
        intent.putExtra(FORMULA, FORMULA);
        startActivity(intent);
    }

    protected void showActivityNewBreastFeeding(){
        Intent intent = new Intent(getApplicationContext(), NewBreastFeedingActivity.class);
        intent.putExtra(BREASTFEED, BREASTFEED);
        startActivity(intent);
    }

    protected void showActivityNewFormula(){
        Intent intent = new Intent(getApplicationContext(), NewFormulaActivity.class);
        intent.putExtra(BREASTFEED, BREASTFEED);
        startActivity(intent);
    }

}
