package com.example.kopapirollo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView choosepersonImg;
    private ImageView choosecompImg;
    private TextView resultText;
    private Button rockButton;
    private Button paperButton;
    private Button scissorsButton;
    private int comp;
    private int person;
    private int draw;
    private Random random;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(0);
            }
        });
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(1);
            }
        });
        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(2);
            }
        });

    }

    public void check(int chosen) {
        int result = random.nextInt(3);
        if (person <= 2 && comp <= 2) {
            if (chosen == 0){
                choosepersonImg.setImageResource(R.drawable.rock);
            } else if (chosen == 1) {
                choosepersonImg.setImageResource(R.drawable.paper);
            } else {
                choosepersonImg.setImageResource(R.drawable.scissors);
            }

            if (result == 0){
                choosecompImg.setImageResource(R.drawable.rock);
            } else if (result == 1) {
                choosecompImg.setImageResource(R.drawable.paper);
            } else {
                choosecompImg.setImageResource(R.drawable.scissors);
            }

            if (chosen == 0) {
                if (result == 1) {
                    comp++;
                    Toast.makeText(this, "A gep nyert", Toast.LENGTH_SHORT).show();
                } else if (result == 2) {
                    person++;
                    Toast.makeText(this, "Te nyertel", Toast.LENGTH_SHORT).show();
                } else {
                    draw++;
                    Toast.makeText(this, "Dontetlen", Toast.LENGTH_SHORT).show();
                }
            } else if (chosen == 1) {
                if (result == 0) {
                    person++;
                    Toast.makeText(this, "Te nyertel", Toast.LENGTH_SHORT).show();
                } else if (result == 2) {
                    comp++;
                    Toast.makeText(this, "A gep nyert", Toast.LENGTH_SHORT).show();
                } else {
                    draw++;
                    Toast.makeText(this, "Dontetlen", Toast.LENGTH_SHORT).show();
                }
            } else { //chosen == 2
                if (result == 0) {
                    comp++;
                    Toast.makeText(this, "A gep nyert", Toast.LENGTH_SHORT).show();
                } else if (result == 1) {
                    person++;
                    Toast.makeText(this, "Te nyertel", Toast.LENGTH_SHORT).show();
                } else {
                    draw++;
                    Toast.makeText(this, "Dontetlen", Toast.LENGTH_SHORT).show();
                }
            }
            resultText.setText(String.format("Eredmeny: Ember: %d Gep: %d Dontetlen: %d", person, comp, draw));
        } else {
            alert(person > comp);
        }
    }

    public void alert(boolean win) {
        if (win) {
            dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Gyozelem")
                    .setMessage("Szeretne uj jatekot jatszani?")
                    .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            comp = 0;
                            person = 0;
                            draw = 0;
                            choosepersonImg.setImageResource(R.drawable.rock);
                            choosecompImg.setImageResource(R.drawable.rock);
                            resultText.setText(String.format("Eredmeny: Ember: %d Gep: %d Dontetlen: %d", person, comp, draw));
                        }
                    })
                    .setCancelable(false).create();
        } else {
            dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Vereseg")
                    .setMessage("Szeretne uj jatekot jatszani?")
                    .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            comp = 0;
                            person = 0;
                            draw = 0;
                            choosepersonImg.setImageResource(R.drawable.rock);
                            choosecompImg.setImageResource(R.drawable.rock);
                            resultText.setText(String.format("Eredmeny: Ember: %d Gep: %d Dontetlen: %d", person, comp, draw));
                        }
                    })
                    .setCancelable(false).create();
        }
        dialog.show();
    }

    public void init() {
        choosepersonImg = findViewById(R.id.choosepersonImg);
        choosecompImg = findViewById(R.id.choosecompImg);
        resultText = findViewById(R.id.resultText);
        rockButton = findViewById(R.id.rockButton);
        paperButton = findViewById(R.id.paperButton);
        scissorsButton = findViewById(R.id.scissorsButton);
        comp = 0;
        person = 0;
        draw = 0;
        random = new Random();
    }
}