package lpdawin.fr.taquin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class
MainActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void checkImage(View view){
        final Button button = (Button) findViewById(view.getId());

        Log.d("Test", button.getText() + "");
    }


}

