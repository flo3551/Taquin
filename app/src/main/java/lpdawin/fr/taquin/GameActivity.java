package lpdawin.fr.taquin;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;

public class GameActivity extends Activity {

    private String pathImage;
    private String tailleGrille;
    private Bitmap image;
    private int taille;
    private ImageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        pathImage = this.getIntent().getStringExtra("pathImage");
        tailleGrille = this.getIntent().getStringExtra("tailleGrille");
        taille = Integer.parseInt(tailleGrille);

        Bitmap image = BitmapFactory.decodeResource(this.getResources(), R.drawable.jonsnow);

        switch (pathImage){
            case "1":
                image = BitmapFactory.decodeResource(this.getResources(), R.drawable.jonsnow);
                break;
            case "2":
                image = BitmapFactory.decodeResource(this.getResources(), R.drawable.mrbean);
                break;
            case "3":
                image = BitmapFactory.decodeResource(this.getResources(), R.drawable.rick);
                break;
        }
        final GridView leGV = (GridView) findViewById(R.id.leGridView);
        adapter = new ImageAdapter(getBaseContext(), image, taille);
        leGV.setAdapter(adapter);
        leGV.setNumColumns(taille);

        leGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                adapter.deplacerCase(position);
                //Log.d("test", position+"");
                leGV.setAdapter(adapter);
            }
        });
    }

}
