package edu.mit.mitmobile2.shuttles;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import edu.mit.mitmobile2.R;
import edu.mit.mitmobile2.SoloMapActivity;
import edu.mit.mitmobile2.shuttles.adapters.ShuttleStopsAdapter;
import edu.mit.mitmobile2.shuttles.model.MITShuttleRouteWrapper;

public class ShuttleStopsActivity extends SoloMapActivity {

    ShuttleStopsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MITShuttleRouteWrapper routeWrapper = getIntent().getParcelableExtra("A");
        adapter = new ShuttleStopsAdapter(this, R.layout.stops_list_row, routeWrapper.getStops());

        setMapItems((ArrayList) routeWrapper.getStops());
        displayMapItems();


    }

    @Override
    protected ArrayAdapter getMapItemAdapter() {
        return adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shuttle_stops, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
