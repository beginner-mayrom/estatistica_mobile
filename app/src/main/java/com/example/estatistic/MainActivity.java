package com.example.estatistic;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText addNum = findViewById(R.id.textInputEditText);
        Button addBtn = findViewById(R.id.button);
        Button mediaBtn = findViewById(R.id.button1);
        Button modaBtn = findViewById(R.id.button2);
        Button medianaBtn = findViewById(R.id.button3);
        Button varBtn = findViewById(R.id.button4);
        Button desPadBtn = findViewById(R.id.button5);
        Button limparBtn = findViewById(R.id.button6);
        TextView vis = findViewById(R.id.textView6);
        TextView resMedia = findViewById(R.id.textView1);
        TextView resModa = findViewById(R.id.textView2);
        TextView resMediana = findViewById(R.id.textView3);
        TextView resVar = findViewById(R.id.textView4);
        TextView resDesPad = findViewById(R.id.textView5);

        ArrayList<Float> numbers = new ArrayList<>();

        addBtn.setOnClickListener(view -> {

            String input = addNum.getText().toString();
            if(input.isEmpty()){
                Toast.makeText(this, "O campo está vazio.", Toast.LENGTH_SHORT).show();
            }
            else{
                try {
                    numbers.add(Float.parseFloat(input));
                    Toast.makeText(this, "Valor adicionado.", Toast.LENGTH_SHORT).show();

                    Collections.sort(numbers);
                    StringBuilder resText = new StringBuilder();
                    for (Float res : numbers) {
                        resText.append(res).append(", ");
                    }
                    // Remove a última vírgula e espaço
                    resText.setLength(resText.length() - 2);

                    vis.setText(resText);
                    addNum.setText("");
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Por favor, insira um número válido.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mediaBtn.setOnClickListener(view -> {

            float sum = 0.0F;
            float res;
            for(float value : numbers){
                sum+=value;
            }
            res = sum / numbers.toArray().length;
            resMedia.setText(String.valueOf(res));
        });

        modaBtn.setOnClickListener(view -> {

            // Map para armazenar a frequência de cada valor
            HashMap<Float, Integer> frequencyMap = new HashMap<>();

            // Conta a frequência de cada valor no array
            for (Float value : numbers) {
                frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
            }

            // Determina a frequência máxima
            int maxFrequency = 0;
            for (int freq : frequencyMap.values()) {
                if (freq > maxFrequency) {
                    maxFrequency = freq;
                }
            }

            // Coleta todos os valores com a frequência máxima
            ArrayList<Float> modas = new ArrayList<>();
            for (Map.Entry<Float, Integer> entry : frequencyMap.entrySet()) {
                if (entry.getValue() == maxFrequency) {
                    modas.add(entry.getKey());
                }
            }

            // Converte o ArrayList em uma String para exibição
            StringBuilder modaText = new StringBuilder();

            for (Float moda : modas) {
                modaText.append(moda).append(", ");
            }
            // Remove a última vírgula e espaço
            modaText.setLength(modaText.length() - 2);

            resModa.setText(modaText);
        });

        medianaBtn.setOnClickListener(view -> {

            // Ordena os valores
            Collections.sort(numbers);

            int size = numbers.size();
            if (size % 2 == 1) {
                // Caso o tamanho seja ímpar, retorna o elemento do meio
                float mediana = numbers.get(size / 2);
                resMediana.setText(String.valueOf(mediana));
            } else {
                // Caso o tamanho seja par, calcula a média dos dois valores centrais
                float middle1 = numbers.get((size / 2) - 1);
                float middle2 = numbers.get(size / 2);
                resMediana.setText(String.valueOf((middle1 + middle2) / 2));
            }
        });

        varBtn.setOnClickListener(view -> {

        });

        desPadBtn.setOnClickListener(view -> {

        });

        limparBtn.setOnClickListener(view -> {

            // Limpar todos os valores do array
            numbers.clear();

            vis.setText("");
            resMedia.setText("");
            resModa.setText("");
            resMediana.setText("");
            resVar.setText("");
            resDesPad.setText("");
        });



    }
}