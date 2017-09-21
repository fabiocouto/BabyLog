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
            holder.txtTitle = (TextView) rowView.findViewById(R.id.txtTitleBikeSpotCommentItem);
            holder.txtUsername = (TextView) rowView.findViewById(R.id.txtUserBikeSpotCommentItem);
            holder.txtDate = (TextView) rowView.findViewById(R.id.txtDateBikeSpotCommentItem);
            rowView.setTag(holder);

        }
        else{
            holder = (ActivityHolder) rowView.getTag();
        }

        for(int i = 0; i < lista.size(); i++){
            ActivityLog activityE = lista.get(position);
            //TODO
            holder.txtTitle.setText(activityE.getTitle());
            holder.txtUsername.setText("Fabio");
            holder.txtDate.setText("20/09/2017");
        }

        return rowView;

    }

    static class ActivityHolder{
        TextView txtTitle;
        TextView txtDate;
        TextView txtUsername;
    }

}