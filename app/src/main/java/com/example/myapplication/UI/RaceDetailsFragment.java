package com.example.myapplication.UI;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.SharedViewModel;
import com.example.myapplication.TypeClasses.RaceEvent;
import com.example.myapplication.Utilities.HelperFunctions;
import com.example.myapplication.Utilities.PreferencesManager;
import com.example.myapplication.placeholder.MyApplication;

public class RaceDetailsFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    private TextView textViewRaceTitle;
    private TextView textViewTrackName;
    private TextView textViewEventNameList;
    private TextView textViewEventDateList;
    private TextView textViewEventTimeLIst;
    private Button setRaceButton;
    private ImageView imageViewCountryFlag;
    private ImageView imageViewTrackImage;
    PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());
    HelperFunctions helperFunctions = new HelperFunctions();
    public RaceDetailsFragment() {
    }





    /*public static SelectSeriesFragment newInstance(int columnCount) {
        SelectSeriesFragment fragment = new SelectSeriesFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            //   mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        // raceLibrary = ModelEnumSingleton.INSTANCE.getInfo();



        //System.out.println("onCreate has executed for SeriesDetailsFragment");

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activity = getActivity();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        View view = inflater.inflate(R.layout.fragment_race_view, container, false);
        textViewRaceTitle = view.findViewById(R.id.textViewTitleSeriesName);

        setRaceButton = view.findViewById(R.id.buttonSetRace);
        textViewRaceTitle.setText(sharedViewModel.getSelectedRace().getRaceName());
        imageViewCountryFlag = view.findViewById(R.id.imageViewCountryFlag);
        textViewTrackName = view.findViewById(R.id.textViewTrackName);
        textViewTrackName.setText(sharedViewModel.getSelectedRace().getRaceTrack());
        //Get the imageID for the particular race
        int imageResourceId = helperFunctions.getResourceIdFromString(MyApplication.getAppContext(), sharedViewModel.getSelectedRace().getCountry());
        //Update the image in fragment_select_race_item
        if (imageResourceId != 0) {
            Drawable drawable = MyApplication.getAppContext().getResources().getDrawable(imageResourceId, null);
            imageViewCountryFlag.setImageDrawable(drawable);
        }
        //Get the imageID for the particular race
        if (sharedViewModel.getSelectedRace().getTrackLayoutImage()!=null) {
            int imageResourceIdTrack = helperFunctions.getResourceIdFromString(MyApplication.getAppContext(), sharedViewModel.getSelectedRace().getTrackLayoutImage());

            //Update the image in fragment_select_race_item
            if (imageResourceIdTrack != 0) {
                Drawable drawable = MyApplication.getAppContext().getResources().getDrawable(imageResourceIdTrack, null);
                imageViewCountryFlag.setImageDrawable(drawable);
                System.out.println("Resource ID");
                System.out.println(imageResourceIdTrack);
            }
        }

        textViewEventNameList = view.findViewById(R.id.textViewEventNameList);
        textViewEventDateList = view.findViewById(R.id.textViewEventDateList);
        textViewEventTimeLIst= view.findViewById(R.id.textViewEventTimeLIst);

        String raceEventNameList = "";
        String raceEventDateList = "";
        String raceEventTimeList = "";

        //System.out.println(("Number of events in race:"));
        //System.out.println(sharedViewModel.getSelectedRace().getRaceEvents().size());
        //SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for( RaceEvent event:sharedViewModel.getSelectedRace().getRaceEvents()){
            raceEventDateList = new StringBuilder().append(raceEventDateList).append(event.getEventDate().substring(0,10)).append("\n").toString();
            raceEventTimeList = new StringBuilder().append(raceEventTimeList).append(event.getEventDate().substring(11)).append("\n").toString();
            raceEventNameList = new StringBuilder().append(raceEventNameList).append(event.getEventName()).append("\n").toString();
            //raceEventTimeList = raceEventTimeList

            //System.out.println(event.getEventName());
            //System.out.println(event.getEventDate());
        }
        textViewEventNameList.setText(raceEventNameList);
        textViewEventDateList.setText(raceEventDateList);
        textViewEventTimeLIst.setText(raceEventTimeList);
        setRaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.setRace(sharedViewModel.getSelectedRace());
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
                navController.popBackStack(R.id.profileSettingsFragment,false);
            }


        });
        return view;
    }
}
