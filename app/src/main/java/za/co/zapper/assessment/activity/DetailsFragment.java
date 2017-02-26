package za.co.zapper.assessment.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import za.co.zapper.assessment.R;
import za.co.zapper.assessment.Util.ActivityUtil;
import za.co.zapper.assessment.activity.adapter.DetailListAdapter;
import za.co.zapper.assessment.activity.adapter.MasterListAdapter;
import za.co.zapper.assessment.domain.Detail;
import za.co.zapper.assessment.domain.Master;
import za.co.zapper.assessment.processor.DetailProcessor;
import za.co.zapper.assessment.processor.MasterProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SaurabhB on 2017/02/23.
 */
// This is the secondary fragment, displaying the details of a particular
// item.

public class DetailsFragment extends ListFragment {
    ListView detailListView;
    boolean mDualPane;
    int mCurCheckPosition = 1;
    long masterId;
    private Context context;

    // Create a new instance of DetailsFragment, initialized to show the
    // text at 'index'.

    public DetailsFragment(){}

    public DetailsFragment(long masterId,Context context){
        this.masterId = masterId;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Toast.makeText(getActivity(), "DetailsFragment:onCreateView", Toast.LENGTH_LONG).show();
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        setHasOptionsMenu(true); //NOTE: this call is required so that the onCreateOptionsMenu() is executed.

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Detail detail = DetailProcessor.getInstance(getActivity()).getDetail(masterId);
        if(detail == null) {
            ActivityUtil.showInformationAlertDialog(this.context, "Error", "Data Not Found", null);
        }else {
            List<Detail> detailList = new ArrayList<>();
            detailList.add(detail);
            DetailListAdapter detailListAdapter = new DetailListAdapter(getActivity(), R.layout.adapter_details, masterId, detailList);
             detailListView = getListView();

            setListAdapter(detailListAdapter);
        }
    }
}