package com.example.myapplication.UI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.SharedViewModel;
import com.example.myapplication.Singleton;
import com.example.myapplication.TypeClasses.RaceSeries;
import com.example.myapplication.Utilities.DatabaseHelper;
import com.example.myapplication.Utilities.HelperFunctions;
import com.example.myapplication.Utilities.PreferencesManager;
//import com.example.myapplication.databinding.FragmentSelectSeriesListBinding;
import com.example.myapplication.placeholder.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class SelectSeriesFragment extends Fragment implements SelectSeriesItemAdapter.OnItemClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    //List<RaceSeries> raceLibrary ;
    private SelectSeriesItemAdapter itemAdapter;
    private List<RaceSeries> itemList;
    private SharedViewModel sharedViewModel;
    Singleton singletonInstance;
    PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SelectSeriesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SelectSeriesFragment newInstance(int columnCount) {
        SelectSeriesFragment fragment = new SelectSeriesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        //System.out.println("\nonCreate has executed for SelectSeriesFragment\n");

    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<RaceSeries> raceSeriesList = new ArrayList<>();
        //System.out.println("onCreateView has started for SelectSeriesFragment");
        //com.example.myapplication.databinding.FragmentSelectSeriesListBinding binding = FragmentSelectSeriesListBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_select_series_list, container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // Set the adapter
        if (view instanceof RecyclerView) {
            //System.out.println("view is an instance of recylerView");
            //Context context = view.getContext();
            Context context = MyApplication.getAppContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            itemList = new ArrayList<>();
            //itemList.add(TestValues.raceSeriesTest);

            System.out.println("\n\n\nThe contents of sharedViewModel raceserieslist" + sharedViewModel.getGlobalRaceSeries().get(0).getName());


            //if (preferences.getRaceSeriesList() != null) {
                //System.out.println(new String("Value of RaceSeriesList in preferences: " + preferences.getStored_preference_race_Series()));
               // raceSeriesList = SharedViewModel.parseJsonRaceSeriesArray(preferences.getRaceSeriesList());
            raceSeriesList =   sharedViewModel.getGlobalRaceSeries();

                /*for (RaceSeries raceSeries : raceSeriesList) {
                    //System.out.println(new String("Value of RaceSeries in list : " + raceSeries.getName()));
                    if(Objects.equals(raceSeries.getAbbreviation(), "F1")){
                        if (!preferences.getMeetingList().isEmpty()){
                            if(!preferences.getSessionList().isEmpty()) {
                                List<Race> raceList = new ArrayList<>();
                                for (Meeting meeting : SharedViewModel.parseJsonMeetingArray(preferences.getMeetingList())) {

                                    //System.out.println(new String("Value of Meeting in list : " + meeting.getMeetingName()));
                                    raceList.add(LiveDataConverter.convertMeetingToRace(meeting));
                                }
                                for (Race race : raceList) {
                                    List<Session> sessionList = SharedViewModel.parseJsonSessionArray(preferences.getSessionList());
                                    List<RaceEvent> raceEvents = new ArrayList<>();
                                    for (Session session : sessionList) {
                                        if (session.getMeetingKey() == race.getMeeting_key()) {
                                            raceEvents.add(LiveDataConverter.convertSessionToRaceEvent(session));
                                        }
                                    }
                                    race.setRaceEvents(raceEvents);
                                }
                                raceList.get(raceList.size()-1).setRaceDate(new Date(2024, 12, 7, 19, 30, 0));
                                raceSeries.setRaceArray(raceList);
                            }
                        }
                    }
                }*/
            //}


            // Add items to the list

            //itemList.addAll(raceSeriesList);
            //itemList.add(TestValues.raceSeriesTest);
                for (RaceSeries raceSeries:raceSeriesList){
                    itemList.add(raceSeries);
                }
            itemAdapter = new SelectSeriesItemAdapter(itemList,this);
            recyclerView.setAdapter(itemAdapter);



            itemAdapter.setOnItemClickListener(new SelectSeriesItemAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    for(RaceSeries item:itemList){
                        System.out.println(item.getName());
                    }
                    System.out.println("Item Clicked" + position);

                    DatabaseHelper dbHelper = new DatabaseHelper(MyApplication.getAppContext());
                    RaceSeries raceSeries = itemList.get(position);
                    dbHelper.addRaceSeries(raceSeries);
                    sharedViewModel.setPreferedRaceSeries(raceSeries);
                    preferences.setStored_preference_race_Series(HelperFunctions.convertRaceSeriestoJSONRaceSeries(raceSeries).toString());
                    NavHostFragment.findNavController(SelectSeriesFragment.this)
                            .navigate(R.id.action_Select_Series_List_to_Series_View);
                }
            });


        }

        return view;

    }

    private void addItem(RaceSeries item) {
        itemAdapter.addItem(item);
    }

    public void onItemClick(int position) {
        System.out.println( "Clicked item at position: ");
        System.out.println( position);

    }



}