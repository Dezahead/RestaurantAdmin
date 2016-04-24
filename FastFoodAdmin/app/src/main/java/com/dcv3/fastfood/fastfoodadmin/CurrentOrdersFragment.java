package com.dcv3.fastfood.fastfoodadmin;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import com.parse.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dezereljones on 4/23/16.
 */
public class CurrentOrdersFragment extends Fragment {
    ListView listView;
    ParseQueryAdapter<ParseObject> mainAdapter;
    String restId;
    public CurrentOrdersFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_currentorders, container, false);
        //restId =getArguments().getString("id");


        // Initialize main ParseQueryAdapter
        mainAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
                // query to gets orders for selected restaurant
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Orders");
                query.whereEqualTo("restNo", "tdiv82Fxqx");
                query.whereEqualTo("isTracking", true);

                return query;
            }
        });

        mainAdapter.setTextKey("orderNo");


        listView = (ListView) v.findViewById(R.id.list);
        listView.setAdapter(mainAdapter);
        mainAdapter.loadObjects();

        //Toast.makeText(getActivity(), restId, Toast.LENGTH_LONG).show();
        return v;
    }
}
