package lpdawin.fr.taquin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;

public class GameActivity extends Activity {

    private String pathImage;
    private String tailleGrille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pathImage = this.getIntent().getStringExtra("pathImage");
        tailleGrille = this.getIntent().getStringExtra("tailleGrille");

        Log.d("Test taille", tailleGrille);
        Log.d("Test path", pathImage);

    }

}
