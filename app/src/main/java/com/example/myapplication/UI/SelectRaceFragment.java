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
import com.example.myapplication.RaceItemRecyclerViewAdapter;
import com.example.myapplication.SharedViewModel;
import com.example.myapplication.TypeClasses.Race;
import com.example.myapplication.TypeClasses.RaceEvent;
import com.example.myapplication.TypeClasses.RaceSeries;
import com.example.myapplication.Utilities.HelperFunctions;
import com.example.myapplication.Utilities.PreferencesManager;
//import com.example.myapplication.databinding.FragmentSelectRaceListBinding;
import com.example.myapplication.Utilities.TestValues;
import com.example.myapplication.placeholder.MyApplication;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class SelectRaceFragment extends Fragment implements RaceItemRecyclerViewAdapter.OnItemClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    List<RaceSeries> raceLibrary ;
    private List<Race> itemList;
    private SharedViewModel sharedViewModel;
    PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SelectRaceFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SelectRaceFragment newInstance(int columnCount) {
        SelectRaceFragment fragment = new SelectRaceFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        //System.out.println(new String("Value of RaceSeriesList in select Race Fragment: \n\n\n" + preferences.getStored_preference_race_Series() + "\n\n\n"));
        if (sharedViewModel.getPreferedRaceSeries()==null){
            System.out.println("Stored race series is currently empty");
        }else{
            System.out.println("Contents of stored race series: " + sharedViewModel.getPreferedRaceSeries().getName());
            //raceLibrary = SharedViewModel.parseJsonRaceSeriesArray("[" + preferences.getStored_preference_race_Series() + "]");
            raceLibrary = sharedViewModel.getGlobalRaceSeries();
            for (RaceSeries raceSeries:raceLibrary){
                List<Race> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(raceSeries.getRaceArray()));
                raceSeries.setRaceArray(listWithoutDuplicates);
            }
        }


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //com.example.myapplication.databinding.FragmentSelectRaceListBinding binding = FragmentSelectRaceListBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_select_race_list, container, false);
        //RecyclerView listView = view.findViewById(R.id.list_race);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            itemList = new ArrayList<>();
            //itemList.add(TestValues.defaultRaceValue);

            //recyclerView.setAdapter(new RaceItemRecyclerViewAdapter(PlaceholderContent.ITEMS));

            //System.out.println(raceLibrary);

            List<Race> raceList = sharedViewModel.getPreferedRaceSeries().getRaceArray();
            List<Race> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(raceList));
            raceList = listWithoutDuplicates;

            //System.out.println("Racelist size = ");
            //System.out.println(raceList.size());
            //System.out.println(race.getRaceName());
            //addItem(race);
            //itemList.addAll(raceList);
            int raceIndex = 0;
            for(Race race:raceList){
                boolean duplicate = false;
                int itemIndex = 0;

                for(Race item:itemList){
                    if (race.getRaceName().equalsIgnoreCase(item.getRaceName())){
                        //System.out.println("Racelist item(" +raceIndex+") " +race.getRaceName()+" equals ItemList item("+itemIndex+") "+item.getRaceName() );
                        duplicate = true;
                    }else {
                        //System.out.println("Racelist item(" +raceIndex+") " +race.getRaceName()+" does not equal ItemList item("+itemIndex+") "+item.getRaceName() );
                    }

                    itemIndex++;
                }
                if (duplicate == false){
                    List <RaceEvent> raceEventListlocal= race.getRaceEvents();
                    /*if (raceEventListlocal.size()>0) {
                        RaceEvent raceEvent = raceEventListlocal.get(0);
                        raceEvent.setEventDate("2024-12-15T20:00:00Z");
                        raceEventListlocal.add(raceEvent);
                        race.setRaceEvents(raceEventListlocal);
                        //itemList.add(race);
                    }else{*/

                        //raceEventListlocal.add(TestValues.defaultRaceEventValue);
                        //raceEventListlocal.add(TestValues.defaultPracticeEventValue);
                        //race.setRaceEvents(raceEventListlocal);
                        itemList.add(race);
                    //}
                }
                raceIndex++;
            }
            System.out.println("Racelist size = " + raceList.size());

            System.out.println("Itemlist size = " + itemList.size());


            //ModelEnumSingleton.INSTANCE.getInfo();
            RaceItemRecyclerViewAdapter itemAdapter = new RaceItemRecyclerViewAdapter(itemList, this);
            recyclerView.setAdapter(itemAdapter);
           // System.out.println(("Printing list of items:"));
          //  System.out.println(itemList.get(0).getCircuitName().toString());
        }
        return view;

    }







    public void onItemClick(int position) {
        System.out.println( "Clicked item at position: ");
        System.out.println( position);
        Race race = itemList.get(position);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        System.out.println("Number of race events: " + race.getRaceEvents().size());
        String eventDateString = null;
        eventDateString = HelperFunctions.getNextRaceEvent2(race).getEventDate();
        LocalDateTime eventDate = LocalDateTime.now();
        if (eventDateString.length() > 20){
            eventDateString = eventDateString.substring(0, eventDateString.indexOf('+')) + "Z";
            eventDate = LocalDateTime.parse( eventDateString.substring(0,eventDateString.length()-1),formatter);
        }else{
            eventDate = LocalDateTime.parse( eventDateString.substring(0,eventDateString.length()-1),formatter);
        }

        if(eventDate.isBefore(LocalDateTime.now())){
            System.out.println("Item is in the past:" + eventDate.toString());
        }else{
            System.out.println("Item is in the future");
            sharedViewModel.selectRace(race);
            preferences.setRace(race);
            NavHostFragment.findNavController(SelectRaceFragment.this)
                    .navigate(R.id.action_Select_Race_List_to_Race_View);
        }
    }
}