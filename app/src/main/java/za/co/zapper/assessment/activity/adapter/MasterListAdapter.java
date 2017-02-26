package za.co.zapper.assessment.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import za.co.zapper.assessment.R;
import za.co.zapper.assessment.activity.DetailActivity;
import za.co.zapper.assessment.domain.Master;

import java.util.List;

public class MasterListAdapter extends ArrayAdapter<Master> {
	private final Context context;
	private final List<Master> masterList;
	private String lastFilterQuery = "";


	public MasterListAdapter(Context context,int adapterLayoutId, List<Master> values) {
		super(context,adapterLayoutId, values);
		this.context = context;
		masterList = values; //convenience list used to display values on the view.
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		String status;
		if(convertView==null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.adapter_master, parent, false);

			viewHolder = new ViewHolder();

			viewHolder.idTextView = (TextView) convertView.findViewById(R.id.idTextView);
			viewHolder.firstNameTextView = (TextView) convertView.findViewById(R.id.firstNameTextView);
			viewHolder.lastNameTextView = (TextView) convertView.findViewById(R.id.lastNameTextView);
			convertView.setTag(viewHolder);

		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Master master = this.getItem(position); // get position of the item whose data we want within the adapter's data set

		viewHolder.idTextView.setText(master.getId().toString());
		viewHolder.firstNameTextView.setText(master.getFirstName());
		viewHolder.lastNameTextView.setText(master.getLastName());
		Log.d("MasterFragment","Master Adapter finished...");

		convertView.setOnClickListener(new RowOnClickListener(master));

		return convertView;
	}

	private static class ViewHolder {
		TextView idTextView;
		TextView firstNameTextView;
		TextView lastNameTextView;
	}

 	public void updateAdapter(List<Master> masterList){
		//update adapter list
		this.clear();
		this.addAll(masterList);

		notifyDataSetChanged();
	}



private class RowOnClickListener implements View.OnClickListener {
	Master master;

	public RowOnClickListener(Master master){
		this.master = master;
	}

	public void onClick(View v) {
		v.setSelected(true); //required so that the selector is triggered for the elements on the view.
		startDetailActivity(master);

	}
}

	private void startDetailActivity(Master master) {
		Intent intent = new Intent(context, DetailActivity.class);
		intent.putExtra("id", master.getId());
		context.startActivity(intent);

	}

}
