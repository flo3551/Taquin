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

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Bitmap image;
    private int taille;
    // references to our images
    private Bitmap mThumbIds[];

    public ImageAdapter(Context c, Bitmap image, int taille) {
        mContext = c;
        this.image = image;
        this.taille = taille;
        this.mThumbIds = new Bitmap[taille * taille];
        decouper(this.image);
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