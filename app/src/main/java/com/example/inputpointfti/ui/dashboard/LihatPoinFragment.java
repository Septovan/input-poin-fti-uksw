package com.example.inputpointfti.ui.dashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.inputpointfti.R;
import com.example.inputpointfti.databinding.FragmentLihatPoinBinding;

import static android.app.Activity.RESULT_OK;

public class LihatPoinFragment extends Fragment {

    private FragmentLihatPoinBinding binding;
    private ImageView imgResult;

    public LihatPoinFragment() {
        // Required empty public constructor
    }

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLihatPoinBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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