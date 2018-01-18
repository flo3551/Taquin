package lpdawin.fr.taquin;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
        decouper(this.image);
        this.mThumbIds = new Bitmap[taille * taille];
    }

    public void decouper(Bitmap image){
        int tailleDecoupe = image.getWidth() / taille;
        Log.d("test", tailleDecoupe +"");
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
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(mThumbIds[position]);
        return imageView;
    }
}