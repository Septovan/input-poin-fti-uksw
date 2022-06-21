package com.example.inputpointfti.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import com.example.inputpointfti.R;
import com.example.inputpointfti.databinding.FragmentDashboardBinding;
import com.example.inputpointfti.ui.notifications.NotificationsViewModel;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}