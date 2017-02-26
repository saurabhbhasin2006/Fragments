package za.co.zapper.assessment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import za.co.zapper.assessment.R;
import za.co.zapper.assessment.Util.SendId;
import za.co.zapper.assessment.activity.adapter.DetailListAdapter;
import za.co.zapper.assessment.activity.adapter.MasterListAdapter;
import za.co.zapper.assessment.domain.Detail;
import za.co.zapper.assessment.domain.Master;
import za.co.zapper.assessment.processor.MasterProcessor;

import java.util.List;

/**
 * Created by SaurabhB on 2017/02/23.
 */
// This is the "top-level" fragment, showing a list of items that the user
// can pick. Upon picking an item, it takes care of displaying the data to
// the user as appropriate based on the current UI layout.

// Displays a list of items that are managed by an adapter similar to
// ListActivity. It provides several methods for managing a list view, such
// as the onListItemClick() callback to handle click events.

public class MasterFragment extends ListFragment  {
    boolean mDualPane;
    int mCurCheckPosition = 0;
    ListView masterListView;
    MasterListAdapter masterListAdapter;

    public MasterFragment(){}
    // onActivityCreated() is called when the activity's onCreate() method
    // has returned.


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        setHasOptionsMenu(true); //NOTE: this call is required so that the onCreateOptionsMenu() is executed.

        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Master> masterList = MasterProcessor.getInstance(getActivity()).getListOfMaster();
        if(masterListAdapter == null){
            masterListAdapter = new MasterListAdapter(getActivity(), R.layout.adapter_master, masterList);
            masterListView = getListView();
            setListAdapter(masterListAdapter);
        }
        else{
            masterListAdapter.updateAdapter(masterList);
        }


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(getActivity(), "onSaveInstanceState",
                Toast.LENGTH_LONG).show();

        outState.putInt("curChoice", mCurCheckPosition);
    }

    // If the user clicks on an item in the list (e.g., Henry V then the
    // onListItemClick() method is called. It calls a helper function in
    // this case.

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

      //  showDetails(position);
    }

    // Helper function to show the details of the selected item, either by
    // displaying a fragment in-place in the current UI, or starting a whole
    // new activity in which it is displayed.

    void showDetails(int index) {
        mCurCheckPosition = index;

        Master master = masterListAdapter.getItem(index);
        Toast.makeText(getActivity(),
                "master.getId()" + master.getId(), Toast.LENGTH_LONG)
                .show();

        // The design is mutli-pane (landscape on the phone) allows us
        // to display both fragments (titles and details) with in the same
        // activity; that is MainActivity -- one activity with two
        // fragments.
         if (mDualPane) {
            // We can display everything in-place with fragment
            getListView().setItemChecked(index, true);

            // Check what fragment is currently shown, replace if needed.
            DetailsFragment detailsfragment = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details);
            if (detailsfragment == null){// || details.get != master.getId()) {
                // Make new fragment to show this selection.

                Toast.makeText(getActivity(), "showDetails dual-pane: create and relplace fragment", Toast.LENGTH_LONG).show();

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.details, detailsfragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            // Otherwise we need to launch a new activity to display
            // the details fragment
            // Create an intent for starting the DetailsActivity
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailActivity.class);

            // pass the current position
            intent.putExtra("index", index);

            startActivity(intent);
        }
    }


}