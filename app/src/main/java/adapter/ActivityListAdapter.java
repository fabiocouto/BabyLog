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

/**
 * Created by fabiodocoutooliveira on 19/07/15.
 */
public class ActivityListAdapter extends ArrayAdapter<ActivityLog> {


    Context context;
    List<ActivityLog> lista;
    Integer layoutResourceID;

    public ActivityListAdapter(Context context, Integer layoutResourceID, List<ActivityLog> lista) {
        super(context, layoutResourceID, lista);
        this.context = context;
        this.lista = lista;
        this.layoutResourceID = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ActivityHolder holder = null;

        if (rowView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            rowView = inflater.inflate(layoutResourceID, parent, false);
            holder = new ActivityHolder();
            holder.txtTitle = (TextView) rowView.findViewById(R.id.txtTitleActivityLogItem);
            holder.txtDescription = (TextView) rowView.findViewById(R.id.txtDescriptionActivityLogItem);
            holder.txtTimeSpent = (TextView) rowView.findViewById(R.id.txtTimeSpentItem);
            holder.txtDate = (TextView) rowView.findViewById(R.id.txtDateActivityItem);
            rowView.setTag(holder);

        }
        else{
            holder = (ActivityHolder) rowView.getTag();
        }

        for(int i = 0; i < lista.size(); i++){
            ActivityLog activityE = lista.get(position);
            holder.txtTitle.setText(activityE.getTitle());
            holder.txtDescription.setText(activityE.getDescription());
            holder.txtTimeSpent.setText(String.valueOf(activityE.getTimeSpent()));
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
