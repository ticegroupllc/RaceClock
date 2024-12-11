package com.example.myapplication.UI;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.SharedViewModel;
import com.example.myapplication.Singleton;
import com.example.myapplication.TypeClasses.Race;
import com.example.myapplication.TypeClasses.RaceSeries;
import com.example.myapplication.Utilities.HelperFunctions;
import com.example.myapplication.Utilities.PreferencesManager;
import com.example.myapplication.placeholder.MyApplication;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileSettingsFragment extends Fragment {

    //private static final int CONTENT_VIEW_ID = 10101010;
    //private EditText editName , editAge, editLocation;
    //private RadioGroup radioGroupGender ;
    //private RadioButton radioButtonSelectGender;// = findViewById(R.id.radioButtonSelectedGender);
    //private CheckBox checkBoxInterest1 , checkBoxInterest2; //= findViewById(R.id.checkBoxInterest1);
    //private Button buttonSubmit ;
    //private TextView textViewProfileInfo;// = findViewById(R.id.textViewProfileInfo);
    Context context;//getActivity();
    PreferencesManager sharedpreferences;
    SharedViewModel sharedViewModel;


    //private static final int SELECT_PICTURE = 200;
    //private Button BSelectImage;
    //private ImageView IVPreviewImage;
    //PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());
    /*public static boolean isStringInteger(String number ){
        try{
            Integer.parseInt(number);
        }catch(Exception e ){
            return false;
        }
        return true;
    }*/

    public ProfileSettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    /*public static ProfileSettingsFragment newInstance(String param1, String param2) {
        ProfileSettingsFragment fragment = new ProfileSettingsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        if (context != null) {
            sharedpreferences = new PreferencesManager(context);
        }
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Switch toggleButtonPracticeTimes;
        Switch toggleButtonQualifyingTimes;
        Switch toggleButtonSprintRaceTimes;
        Switch toggleButtonRaceTimes;
        Activity activity = getActivity();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile_settings, container, false);
        View view = inflater.inflate(R.layout.fragment_profile_settings, container, false);


        //if (preferences.getStored_preference_race_Series() != null) {
        //System.out.println(new String("Value of RaceSeriesList in preferences: " + preferences.getStored_preference_race_Series()));
            /*for (RaceSeries raceSeries : preferences.getStored_preference_race_Series()) {
                System.out.println(new String("Value of RaceSeries in list : " + raceSeries.getName()));
            }*/
        //}


        toggleButtonPracticeTimes = view.findViewById(R.id.switchPracticeTimes);
        toggleButtonQualifyingTimes = view.findViewById(R.id.switchQualifyingTimes);
        toggleButtonSprintRaceTimes = view.findViewById(R.id.switchSprintRaceTimes);
        toggleButtonRaceTimes = view.findViewById(R.id.switchRaceTimes);
        TextView textViewSelectedRaceSeriesName = view.findViewById(R.id.textSelectedRaceSeriesName);
        TextView textViewSelectedRaceName = view.findViewById(R.id.textViewSelectedRaceName);

        if (sharedpreferences != null) {
            toggleButtonPracticeTimes.setChecked(sharedpreferences.getBoolean("showPracticeTimes", true));
            toggleButtonQualifyingTimes.setChecked(sharedpreferences.getBoolean("showQualifyingTimes", true));
            toggleButtonSprintRaceTimes.setChecked(sharedpreferences.getBoolean("showSprintRaceTimes", true));
            toggleButtonRaceTimes.setChecked(sharedpreferences.getBoolean("showRaceTimes", true));
            if (!sharedpreferences.getStored_preference_race_Series().isEmpty() && !sharedpreferences.getStored_preference_race_Series().equalsIgnoreCase("")) {
                //List<RaceSeries> raceSeries = SharedViewModel.parseJsonRaceSeriesArray(new String("[" + sharedpreferences.getStored_preference_race_Series() + "]"));
                //textViewSelectedRaceSeriesName.setText(raceSeries.get(0).getName());
            }
            if (!sharedpreferences.getRace().isEmpty() && !sharedpreferences.getRace().equalsIgnoreCase("")) {
                //Race race = HelperFunctions.convertJsonRaceToRace(sharedpreferences.getRace());
                //textViewSelectedRaceName.setText(race.getRaceName());
            }
        }

        toggleButtonPracticeTimes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedpreferences.setBoolean("showPracticeTimes", isChecked
                );
            }
        });

        toggleButtonQualifyingTimes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedpreferences.setBoolean("showQualifyingTimes", isChecked
                );
            }
        });

        toggleButtonSprintRaceTimes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedpreferences.setBoolean("showSprintRaceTimes", isChecked);
            }
        });

        toggleButtonRaceTimes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedpreferences.setBoolean("showRaceTimes", isChecked);
            }
        });

       Button button_select_series =  view.findViewById(R.id.button_select_series);
        button_select_series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activity != null) {

                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
                    navController.navigate(R.id.action_profileSettingsFragment_to_Select_Series_List);
                }
            }
        });

        Button button_select_race =  view.findViewById(R.id.button_select_race);
        button_select_race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activity != null && sharedViewModel.getPreferedRaceSeries() != null) {

                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
                    navController.navigate(R.id.action_profileSettingsFragment_to_Select_Race_List);
                }
            }
        });




        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Activity activity = getActivity();
        //sharedViewModel.setGlobalRaceSeries(sharedpreferences.getRaceSeriesList());
    }


    /*private void imageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                IVPreviewImage.setImageURI(selectedImageUri);
                sharedpreferences.setImage(selectedImageUri);
            }
        }
    }*/




}
