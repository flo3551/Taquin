package lpdawin.fr.taquin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.R.attr.path;

public class
MainActivity extends Activity {

    private String pathImage;
    private String tailleGrille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkImage(View view){
        final Button button = (Button) findViewById(view.getId());
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        tailleGrille = button.getText().charAt(0) + "";


        if (radioGroup.getCheckedRadioButtonId() == -1) {
            // no radio buttons are checked
            Toast toast = Toast.makeText(getApplicationContext(), "Veuillez sélectionner une image", Toast.LENGTH_LONG);
            toast.show();
        } else {
            // one of the radio buttons is checked
            RadioButton rb = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            pathImage = rb.getText().charAt(rb.getText().length()-1) + "";
            // Start activity
            // Put extra
            // 1 = tailleGrille
            // 2 = Path image

            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("tailleGrille", tailleGrille);
            intent.putExtra("pathImage", pathImage);
            startActivity(intent);
        }
    }
}

