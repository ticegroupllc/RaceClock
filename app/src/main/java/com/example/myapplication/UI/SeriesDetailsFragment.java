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
import com.example.myapplication.Utilities.HelperFunctions;
import com.example.myapplication.Utilities.PreferencesManager;
import com.example.myapplication.placeholder.MyApplication;

public class SeriesDetailsFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());

    public SeriesDetailsFragment() {
    }

    public static SelectSeriesFragment newInstance(int columnCount) {
        SelectSeriesFragment fragment = new SelectSeriesFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //System.out.println("onCreate has executed for SeriesDetailsFragment");

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activity = getActivity();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        View view = inflater.inflate(R.layout.fragment_series_view, container, false);
        TextView textViewSeriesTitle = view.findViewById(R.id.textViewTitleSeriesName);
        TextView textViewSeriesDescription = view.findViewById(R.id.textViewSeriesDescription);
        ImageView imageViewTrackImage = view.findViewById(R.id.imageViewSeriesImage);
        /*private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;*/
        Button setSeriesButton = view.findViewById(R.id.buttonSetSeries);
        textViewSeriesTitle.setText(sharedViewModel.getPreferedRaceSeries().getName());
        textViewSeriesDescription.setText(sharedViewModel.getPreferedRaceSeries().getDescription());

        //Get the imageID for the particular race
        if (!sharedViewModel.getPreferedRaceSeries().getAbbreviation().toLowerCase().isEmpty()) {
            int imageResourceIdTrack = HelperFunctions.getResourceIdFromString(MyApplication.getAppContext(), sharedViewModel.getPreferedRaceSeries().getAbbreviation().toLowerCase());

            //Update the image in fragment_select_race_item
            if (imageResourceIdTrack != 0) {
                Drawable drawable = MyApplication.getAppContext().getResources().getDrawable(imageResourceIdTrack, null);
                imageViewTrackImage.setImageDrawable(drawable);
                System.out.println("Resource ID");
                System.out.println(imageResourceIdTrack);
            }
        }


        setSeriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.setRaceSeries(sharedViewModel.getPreferedRaceSeries());
                if(activity != null) {
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
                    navController.popBackStack(R.id.profileSettingsFragment, false);
                }
            }


        });
        return view;
    }
}
