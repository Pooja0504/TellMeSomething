package pooja.jadhav.tellmesomething;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView voiceInput, speakButton,abc;
    ToggleButton toggleButton;
    final int REQ_SPEAK_CODE=100;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        voiceInput=findViewById(R.id.voiceInput);
        speakButton=findViewById(R.id.btnSpeak);
        abc=findViewById(R.id.textView22);
        toggleButton=findViewById(R.id.toggleButton);
        constraintLayout=findViewById(R.id.layout);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    speakButton.setBackground(getResources().getDrawable(android.R.drawable.alert_dark_frame));
                    speakButton.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
                    constraintLayout.setBackground(getResources().getDrawable(android.R.drawable.dialog_frame));
                   abc.setText("Toggle Button is On");

                } else {
                    speakButton.setBackground(getResources().getDrawable(android.R.drawable.alert_light_frame));
                    speakButton.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    constraintLayout.setBackground(getResources().getDrawable(android.R.drawable.btn_star));

                  abc.setText("Toggle Button is Off");

                }
            }
        });
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bolhalkehalke();
            }
        });

    }

    private void bolhalkehalke() {
        Intent intent_mic =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent_mic.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent_mic.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent_mic.putExtra(RecognizerIntent.EXTRA_PROMPT,"kuch tum kaho...");
        try {
            startActivityForResult(intent_mic, REQ_SPEAK_CODE);
        }catch (ActivityNotFoundException e)
        {
            Toast.makeText(this, "Check Your Mic", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQ_SPEAK_CODE:{
                if(resultCode==RESULT_OK && data!=null)
                {
                    ArrayList<String> stringArrayList=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    voiceInput.setText(stringArrayList.get(0));
                }

            }
            break;
        }
    }

    public void go(View view) {
    }
}