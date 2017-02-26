package za.co.zapper.assessment.activity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import za.co.zapper.assessment.R;
import za.co.zapper.assessment.domain.Detail;

import java.util.List;

/**
 * Created by SaurabhB on 2017/02/25.
 */
public class DetailListAdapter extends ArrayAdapter<Detail> {
    private Context context;
    private List<Detail> detailList;
    private String lastFilterQuery = "";
    long masterId;
    Detail detail;

    public DetailListAdapter(Context context,  int adapterLayoutId, long masterId, List<Detail> detailList) {
        super(context, adapterLayoutId, detailList);
        this.context = context;
        this.masterId = masterId;
        this.detailList = detailList; //convenience list used to display values on the view.
        this.detail = detailList.get(0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String status;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_details, parent, false);

        } else {
        }


        TextView idDetailTextView = (TextView) convertView.findViewById(R.id.idDetailTextView);
        TextView firstNameDetailTextView = (TextView) convertView.findViewById(R.id.firstNameDetailTextView);
        TextView lastNameDetailTextView = (TextView) convertView.findViewById(R.id.lastNameDetailTextView);
        TextView ageDetailTextView = (TextView) convertView.findViewById(R.id.ageDetailTextView);
        TextView favouriteColourDetailTextView = (TextView) convertView.findViewById(R.id.favouriteColourDetailTextView);

        // Populate the data into the template view using the data object
        idDetailTextView.setText(this.detail.getId().toString());
        firstNameDetailTextView.setText(this.detail.getFirstName());
        lastNameDetailTextView.setText(this.detail.getLastName());
        ageDetailTextView.setText(this.detail.getAge().toString());
        favouriteColourDetailTextView.setText(this.detail.getFavouriteColour());

        Log.d("MasterFragment", "Detail Adapter finished...");

        return convertView;
    }

}