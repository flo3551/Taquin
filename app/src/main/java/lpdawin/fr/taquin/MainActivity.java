package lpdawin.fr.taquin;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class
MainActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void checkImage(View view){
        final Button button = (Button) findViewById(view.getId());
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        Log.d("Test", button.getText() + "");

        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            Toast toast = Toast.makeText(getApplicationContext(), "Veuillez sélectionner une image", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            // one of the radio buttons is checked
            RadioButton rb = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            Log.d("Test", rb.getText() + "");


        }

    }


}

