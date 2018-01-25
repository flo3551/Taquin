package lpdawin.fr.taquin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Bitmap image;
    private int taille;
    private Bitmap caseVide;
    // references to our images
    private Bitmap mThumbIds[];

    public ImageAdapter(Context c, Bitmap image, int taille) {
        mContext = c;
        this.image = image;
        this.taille = taille;
        this.mThumbIds = new Bitmap[taille * taille];
        decouper(this.image);
        melangeCase(mThumbIds);
    }

    public Bitmap getTabImage(){
        return null;
    }

    public void decouper(Bitmap image){
        int tailleDecoupe = image.getWidth() / taille;
        Log.d("test", tailleDecoupe + "");
        int indice=0;
        for (int y=0;y < taille ;y++){
            for( int x =0;x<taille; x++){
                mThumbIds[indice]=Bitmap.createBitmap(image, x*tailleDecoupe, y*tailleDecoupe,tailleDecoupe,tailleDecoupe);
                indice++;
            }
        }
        mThumbIds[mThumbIds.length-1]=Bitmap.createBitmap(tailleDecoupe,tailleDecoupe , Bitmap.Config.ALPHA_8);
        caseVide = mThumbIds[mThumbIds.length-1];
        Log.d("test", mThumbIds[mThumbIds.length-1] + "");
    }

    public void melangeCase(Bitmap[] lesImages){
        Bitmap img =  mThumbIds[mThumbIds.length-1];
        for(int i=0;i<mThumbIds.length * 200; i++)
        {
            //Nombre aléatoire entre 0 et le nombre d'image présent dans le tableau
            Random rand = new Random();
            int n = rand.nextInt(mThumbIds.length);
            Bitmap stockImage =  mThumbIds[n];

            //Mélange
            if(n == mThumbIds.length-1) {
                mThumbIds[n] = mThumbIds[n - 1];
                mThumbIds[n - 1] = stockImage;
            }
            else{
                mThumbIds[n] = mThumbIds[n+1];
                mThumbIds[n+1]= stockImage;
            }

        }
    }

    public void deplacerCase(int position){
        Bitmap img =  mThumbIds[position];
        System.out.println("ImageAdapter.deplacerCase");
        //Si la case vide est à droite
        if((position != mThumbIds.length-1) && mThumbIds[position+1] == caseVide ){
            mThumbIds[position] = mThumbIds[position+1];
            mThumbIds[position+1]=img;
            Log.d("test", "position + 1 ");
        }
        //si la case est vide a gauche
        else if((position != 0) && mThumbIds[position-1] == caseVide){
            mThumbIds[position] = mThumbIds[position-1];
            mThumbIds[position-1]=img;
            Log.d("test", "position - 1 ");

        }
        //si la case est vide en dessous
        else if((position <= (taille*taille) - taille ) && mThumbIds[position+taille] == caseVide){
            mThumbIds[position] = mThumbIds[position+taille];
            mThumbIds[position+taille]=img;
            Log.d("test", "position + taille");
        }
        //si la case est vide au dessus
        else if((position >= taille ) && mThumbIds[position-taille] == caseVide){
            mThumbIds[position] = mThumbIds[position-taille];
            mThumbIds[position-taille]=img;
            Log.d("test", "position - taille");
        }
        else{
            Toast toast = Toast.makeText(mContext, "Mouvement impossible ! ", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);

            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = width;
            width = width -10;
            switch(taille){
                case 3:
                    imageView.setLayoutParams(new GridView.LayoutParams(width/3, height/3));
                    break;
                case 4:
                    imageView.setLayoutParams(new GridView.LayoutParams(width/4, height/4));
                    break;
                case 5:
                    imageView.setLayoutParams(new GridView.LayoutParams(width/5, height/5));
                    break;

            }
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2,2,2,2);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(mThumbIds[position]);
        return imageView;
    }
}