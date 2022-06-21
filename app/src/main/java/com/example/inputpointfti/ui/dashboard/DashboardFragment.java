package com.example.inputpointfti.ui.dashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.inputpointfti.databinding.FragmentDashboardBinding;

import static android.app.Activity.RESULT_OK;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private ImageView imgResult;

    ActivityResultLauncher<Intent> getPhoto = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Bundle extras = result.getData().getExtras();
                        Bitmap imageCapturedBitmap = (Bitmap) extras.get("data");

                        imgResult.setImageBitmap(imageCapturedBitmap);
                    }
                }
            }
        );

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        ImageButton btnAddPhoto = binding.btnAddPhoto;
        btnAddPhoto.setOnClickListener((view) -> {
            Intent intentOpenCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            getPhoto.launch(intentOpenCamera);
        });

        imgResult = binding.imgResult;

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}