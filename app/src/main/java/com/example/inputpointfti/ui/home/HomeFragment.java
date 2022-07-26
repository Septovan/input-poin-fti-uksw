package com.example.inputpointfti.ui.home;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.inputpointfti.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private EditText editTextNim, editTextFullName, editTextPhoneNumber;
    private Button btnSaveData, btnGetData, btnUpdateData;
    private FirebaseFirestore db;
    private String previousId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        editTextNim = binding.editTextNim;
        editTextFullName = binding.editTextFullName;
        editTextPhoneNumber = binding.editTextPhoneNumber;
        btnSaveData = binding.buttonSaveData;
        btnGetData = binding.buttonGetData;
        btnUpdateData = binding.buttonUpdateData;

        btnSaveData.setOnClickListener((view) -> {
            saveData();
        });

        btnGetData.setOnClickListener((view) -> {
            getData(previousId);
        });

        btnUpdateData.setOnClickListener((view) -> {
            updateData(previousId);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void saveData() {
        // #1 Create object
        Map<String, Object> mahasiswa = new HashMap<>();
        mahasiswa.put("nim", editTextNim.getText().toString());
        mahasiswa.put("nama", editTextFullName.getText().toString());
        mahasiswa.put("phoneNumber", editTextPhoneNumber.getText().toString());

        // #2 Insert
        db.collection("mahasiswa")
            .add(mahasiswa)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    previousId = documentReference.getId();

                    editTextNim.setText("");
                    editTextFullName.setText("");
                    editTextPhoneNumber.setText("");

                    Toast.makeText(getActivity().getApplicationContext(),
                            "Berhasil memasukkan data mahasiswa dengan ID = " + documentReference.getId(),
                            Toast.LENGTH_SHORT
                        ).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Gagal memasukkan data mahasiswa dengan exception = " + e.getMessage(),
                            Toast.LENGTH_SHORT
                    ).show();
                }
            });
    }

    private void getData(String id) {
        db.collection("mahasiswa")
                .document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Map<String, Object> mahasiswa = task.getResult().getData();
                            editTextNim.setText(mahasiswa.get("nim").toString());
                            editTextFullName.setText(mahasiswa.get("nama").toString());
                            editTextPhoneNumber.setText(mahasiswa.get("phoneNumber").toString());
                        }
                        else {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Data mahasiswa tidak ditemukan!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Error | " + e.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }

    private void updateData(String id) {
        // #1 create object
        Map<String, Object> mahasiswa = new HashMap<>();
        mahasiswa.put("nim", editTextNim.getText().toString());
        mahasiswa.put("nama", editTextFullName.getText().toString());
        mahasiswa.put("phoneNumber", editTextPhoneNumber.getText().toString());

        // #2 update data
        db.collection("mahasiswa").document(id).set(mahasiswa);
    }
}