    package com.example.lab4main2;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);

            Button bai1va2 = findViewById(R.id.bai1va2);
            Button bai4 = findViewById(R.id.bai4);

            bai1va2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Bai1va2Activity.class);
                    startActivity(intent);
                }
            });

            bai4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Bai4Activity.class);
                    startActivity(intent);
                }
            });
        }
    }