package com.example.myapplication.UI;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.myapplication.LiveDataCustomAdapter;
import com.example.myapplication.R;
import com.example.myapplication.SharedViewModel;
import com.example.myapplication.TypeClasses.*;
import com.example.myapplication.Utilities.*;
import com.example.myapplication.databinding.FragmentStartPageBinding;
import com.example.myapplication.placeholder.MyApplication;

import org.json.JSONException;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartPageFragment extends Fragment {

    private final Handler handler = new Handler();
    private Runnable runnable;
    Context context ;

    public Uri imageURI;
    private static final int SELECT_PICTURE = 200;
    private FragmentStartPageBinding binding;
    private long timeLeftInMillis;
    SharedViewModel sharedViewModel;
    LocalDateTime eventDate = LocalDateTime.now();
    PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());


    public StartPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment DisplayProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public StartPageFragment newInstance() {
        StartPageFragment fragment = new StartPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStartPageBinding.inflate(inflater, container, false);

            viewUpdater();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Update the TextView when the fragment becomes active textView.setText("Updated Text");

        /*if(preferences != null) {
            System.out.println("\nPreferences is not null on startpage in On resume\n");
            System.out.println(preferences.getRace());
            if(preferences.getRace() != null && !preferences.getRace().isEmpty()) {
                Race race = HelperFunctions.convertJsonRaceToRace(preferences.getRace());
                //startCountdown();
                System.out.println("Value of racename = "+ race.getRaceName());
                binding.textViewRaceName.setText(race.getRaceName());
                RaceEvent raceEvent = HelperFunctions.getNextRaceEvent(race);
                binding.textViewEventName.setText(raceEvent.getEventName());
            }else {
                System.out.println("\nSelectedRace is still null on startpage Even in On resume\n");
            }
        }*/

            viewUpdater();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Activity activity = getActivity();
        sharedViewModel.getLiveTimingData();


        binding.buttonPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( activity != null) {
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
                    navController.navigate(R.id.action_startPageFragment_to_privacyPolicyFragment);
                }

            }
        });

       binding.buttonUserAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondFragment
                if( activity != null) {
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
                    navController.navigate(R.id.action_startPageFragment_to_userAgreementFragment);
                }
            }
        });

        binding.buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondFragment

                if( activity != null) {
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
                    navController.navigate(R.id.action_startPageFragment_to_profileSettingsFragment);
                }
            }
        });



        runnable = new Runnable() {
            @Override
            public void run() {
                //counter++;
                LocalDateTime now = LocalDateTime.now();
                timeLeftInMillis = DateTimeUtils.getNumberOfMillisecondsBetween(now,eventDate);
                updateCountdownText();
                handler.postDelayed(this, 1000); // Update every second*/
            }
        };
        System.out.println("executing handler.post");
        handler.post(runnable);

    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); // Stop the runnable when fragment is not visible
        System.out.println("terminated runnable");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable); // Stop the runnable when activity is destroyed
        System.out.println("terminated runnable");
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            if (imageURI != null) {
                binding.imageViewFlag.setImageURI(imageURI);
            }
        }
    }

    /*private void startCountdown() {
        CountDownTimer countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }*/


    private void updateCountdownText() {
        int days = (int) ((timeLeftInMillis / 1000) / (3600*24));
        int hours = (int) (((timeLeftInMillis / 1000) / 3600) - (days*24));
        int minutes = (int) (((timeLeftInMillis / 1000) / 60)%60);
        int seconds = (int) (timeLeftInMillis / 1000) % 60;


        binding.textViewRaceDay.setText(String.valueOf(days));
        binding.textViewRaceHour.setText(String.valueOf(hours));
        binding.textViewRaceMin.setText(String.valueOf(minutes));
        binding.textViewRaceSec.setText(String.valueOf(seconds));


    }

    private void viewUpdater() {
        boolean showRaceTimes = preferences.getBoolean("showRaceTimes", true);
        boolean showPracticeTimes = preferences.getBoolean("showPracticeTimes",true);
        boolean showQualifyingTimes = preferences.getBoolean("showQualifyingTimes",true);
        boolean showSprintTimes = preferences.getBoolean("showSprintRaceTimes",true);
        List<String> itemList = new ArrayList<>();
        LiveDataCustomAdapter adapter = new LiveDataCustomAdapter(getContext(), itemList);
        binding.listView.setAdapter(adapter);
        RaceSeries raceSeries = sharedViewModel.parseJsonRaceSeries(preferences.getStored_preference_race_Series());
        Race race = TestValues.emptyRace();
        if(preferences != null) {
            System.out.println("\nPreferences is not null on startpage in On resume\n");
            System.out.println(preferences.getRace());
            if(preferences.getRace() != null && !preferences.getRace().isEmpty()) {
                race = SharedViewModel.parseRace(preferences.getRace());
                RaceEvent raceEvent = null;
                raceEvent = HelperFunctions.getNextRaceEvent2(race);

                binding.textViewEventName.setText(raceEvent.getEventName());
                binding.textViewCountdownTitle.setText(race.getRaceName());


                System.out.println("Race was not null at startpage. ");
                String eventDateString;

                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                try {
                    eventDateString = raceEvent.getEventDate();
                    eventDate = LocalDateTime.parse(eventDateString.substring(0, eventDateString.length() - 1), formatter);
                    LocalDateTime now = LocalDateTime.now();

                    if (eventDate.isBefore(now)) {
                        if (now.isBefore(eventDate.plusHours(2))) {
                            //boolean countDownMode = false;
                            List<Session> sessionList = new ArrayList<>();
                            sessionList = SharedViewModel.getSessionList(1141);
                            for (Session session : sessionList) {
                                itemList.add(session.getCircuitShortName());
                            }
                            adapter.notifyDataSetChanged();
                            //binding.listView.setVisibility(View.VISIBLE);
                            //System.out.println("\n\nSetting view to visible. event time is now livedata\n\n");
                        }
                    } else {
                        binding.textViewRaceName.setText(race.getRaceName());
                        binding.textViewEventName.setText(raceEvent.getEventName());

                    }
                } catch (Exception e) {
                    System.out.println("Failed to get race information");
                    System.out.println(e);

                }
            }

        }else{
            System.out.println("\nSelectedRace is still null on startpage\n");
        }

        // To load checkered flag image
        if (ImageSaver.loadImageFromInternalStorage(context, "myImage") != null) {
            Bitmap loadedBitmap = ImageSaver.loadImageFromInternalStorage(context, "myImage");
            binding.imageViewFlag.setImageBitmap(loadedBitmap);

            RenderEffect blurEffect = RenderEffect.createBlurEffect(25f, 25f, Shader.TileMode.CLAMP);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                binding.imageViewFlag.setRenderEffect(blurEffect);
            }
        }
    }

}