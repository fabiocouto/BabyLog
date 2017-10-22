package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import babylog.com.babylog.R;
import model.ActivityLog;
import utils.StringConverter;

/**
 * Created by fabiodocoutooliveira on 19/07/15.
 */
public class ActivityListAdapterFormula extends ArrayAdapter<ActivityLog> {


    Context context;
    List<ActivityLog> lista;
    Integer layoutResourceID;

    public ActivityListAdapterFormula(Context context, Integer layoutResourceID, List<ActivityLog> lista) {
        super(context, layoutResourceID, lista);
        this.context = context;
        this.lista = lista;
        this.layoutResourceID = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ActivityHolder holder = null;
        StringConverter stringConverter = new StringConverter();

        if (rowView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            rowView = inflater.inflate(layoutResourceID, parent, false);
            holder = new ActivityHolder();
            holder.txtTitle = (TextView) rowView.findViewById(R.id.txtTitleFormulaActivityLogItem);
            holder.txtDescription = (TextView) rowView.findViewById(R.id.txtDescriptionFormulaActivityLogItem);
            holder.txtTimeSpent = (TextView) rowView.findViewById(R.id.txtFormulaSpentItem);
            holder.txtDate = (TextView) rowView.findViewById(R.id.txtDateFormulaActivityItem);
            rowView.setTag(holder);

        }
        else{
            holder = (ActivityHolder) rowView.getTag();
        }

        for(int i = 0; i < lista.size(); i++){
            ActivityLog activityE = lista.get(position);
            holder.txtTitle.setText(activityE.getTitle());
            holder.txtDescription.setText(activityE.getDescription());
            holder.txtTimeSpent.setText(stringConverter.converteFromMilliliters(activityE.getTimeSpent()));
            holder.txtDate.setText(activityE.getLocalDate());
        }

        return rowView;

    }

    static class ActivityHolder{
        TextView txtTitle;
        TextView txtDescription;
        TextView txtDate;
        TextView txtTimeSpent;
    }

}
