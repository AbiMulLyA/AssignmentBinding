package com.belajar.assignmentdatabinding.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.belajar.assignmentdatabinding.R;
import com.belajar.assignmentdatabinding.adapters.UserAdapter;
import com.belajar.assignmentdatabinding.models.users.DataItem;
import com.belajar.assignmentdatabinding.models.users.UserModel;
import com.belajar.assignmentdatabinding.repositories.UserRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    ProgressBar pbLoading;
    EditText etSearch;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<UserModel> usersModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pbLoading = findViewById(R.id.pbLoading);
        recyclerView = findViewById(R.id.rvUsers);
        etSearch = findViewById(R.id.etSearch);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                userAdapter.getFilter().filter(editable);
            }
        });

        showLoading(true);
        UserRepository userRepository = new UserRepository();
        userRepository.service.getUsers().enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DataItem> users = response.body().getData();
                    Log.d("Retrofit Get", "Jumlah data : " +
                            String.valueOf(users.size()));

                    userAdapter = new UserAdapter();
                    userAdapter.setUsersList(getApplicationContext(), users);
                    userAdapter.setOnItemClickCallback(this::showSelectedUser);
                    recyclerView.setAdapter(userAdapter);
                }
                showLoading(false);
            }
            private void showSelectedUser(DataItem data) {
                Bundle bundle = new Bundle();
                bundle.putString("email", data.getEmail());
                bundle.putString("avatar", data.getAvatar());
                bundle.putString("firstName", data.getFirstName());
                bundle.putString("lastName", data.getLastName());
                Intent i = new Intent(getApplicationContext(), UserActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                t.printStackTrace();
                showLoading(false);
            }
        });
    }

    private void showLoading(boolean isLoading) {
        if (isLoading) {
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            pbLoading.setVisibility(View.GONE);
        }
}

}