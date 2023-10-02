package com.example.api;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button btn_e_cuadrado;
    Button btn_e_cubo;
    Button btn_r_cuadrada;
    Button btn_r_cubica;
    TextView resultado;
    EditText Numero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = findViewById(R.id.result);
        Numero = findViewById(R.id.number);

        btn_e_cuadrado = findViewById(R.id.btn_e_cuadrado);
        btn_e_cubo = findViewById(R.id.btn_e_cubo);
        btn_r_cuadrada = findViewById(R.id.btn_r_cuadrada);
        btn_r_cubica = findViewById(R.id.btn_r_cubica);


        btn_e_cuadrado.setOnClickListener(w -> {
        getPost("cuadrado");
        });

        btn_e_cubo.setOnClickListener(w -> {
            getPost("cubo");
        });

        btn_r_cuadrada.setOnClickListener(w -> {
            getPost("raiz_cuadrada");
        });


        btn_r_cubica.setOnClickListener(w -> {
            getPost("raiz_cubica");
        });
    }

    void getPost(String type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Route Ruta = retrofit.create(Route.class);

        Call<JsonObject> call = Ruta.getPosts(Integer.parseInt(Numero.getText().toString()), type);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    resultado.setText("ERROR EN LA API");
                }
                JsonObject jsonObject = response.body();
                if (jsonObject != null) {
                    JsonElement cuadradoElement = jsonObject.get(type);
                    resultado.setText( cuadradoElement.toString());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println("onFailure"+ t.getMessage());
            }
        });


}
}