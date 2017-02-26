package za.co.zapper.assessment.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by SaurabhB on 2017/02/24.
 */
// This is a secondary activity, to show what the user has selected when the
// screen is not large enough to show it all in one activity.

public class DetailActivity extends FragmentActivity {
    long detailId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(this, "DetailActivity", Toast.LENGTH_SHORT).show();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // If the screen is now in landscape mode, we can show the
                // dialog in-line with the list so we don't need this activity.
                finish();
                return;
            }

            if (savedInstanceState == null) {
                // During initial setup, plug in the details fragment.
                Long id =  getIntent().getExtras().getLong("id");

                Fragment detailFragment = new DetailsFragment(id,this);
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.content, detailFragment);
                fragmentTransaction.commit();

            }
        }
    }




