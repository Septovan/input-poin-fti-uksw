package com.example.inputpointfti.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inputpointfti.R;
import com.example.inputpointfti.databinding.FragmentInputPoinBinding;
import com.example.inputpointfti.models.PublicApiResponse;
import com.example.inputpointfti.services.ApiClientService;
import com.example.inputpointfti.services.PublicApiServiceInterface;

public class InputPoinFragment extends Fragment {

    private FragmentInputPoinBinding binding;
    private TextView txtPublicApiResult;

    public InputPoinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInputPoinBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txtPublicApiResult = binding.txtPublicApiResult;
        Button btnPublicApiReq_Start = binding.btnPublicApiStartRequest;

        btnPublicApiReq_Start.setOnClickListener(view -> {
            startRequestingPublicApi();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void startRequestingPublicApi() {
        PublicApiServiceInterface publicApiService = ApiClientService.getPublicApiClient().create(PublicApiServiceInterface.class);
        Call<PublicApiResponse> getAllEntriesCall = publicApiService.getAllEntries();

        getAllEntriesCall.enqueue(new Callback<PublicApiResponse>() {
            @Override
            public void onResponse(Call<PublicApiResponse> call, Response<PublicApiResponse> response) {
                if (response.isSuccessful()) {
                    PublicApiResponse data = response.body();
                    txtPublicApiResult.setText(data.getEntries().get(0).getApiName());
                }
                else {
                    Toast.makeText(
                            getActivity().getApplicationContext(),
                            "Request to PublicAPI server is unsuccessful!",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<PublicApiResponse> call, Throwable t) {
                Toast.makeText(
                    getActivity().getApplicationContext(),
                    "Failed to send a request to PublicAPI server!",
                    Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}