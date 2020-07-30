package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.retrofit.Interface.PruebaInterface;
import com.example.retrofit.Model.Datos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.pruebaText);
    }

    public void getAnswer(View view){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://magicservis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PruebaInterface pruebaInterface = retrofit.create(PruebaInterface.class);

        Call<List<Datos>> call = pruebaInterface.getAnswer();


        call.enqueue(new Callback<List<Datos>>() {
            @Override
            public void onResponse(Call<List<Datos>> call, Response<List<Datos>> response) {


                if (!response.isSuccessful()){
                    textView.setText("Codigo: "+response.code());
                    return;
                }

                textView.append("Actividad: "+response.body().get(0).getActividad());

            }

            @Override
            public void onFailure(Call<List<Datos>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}