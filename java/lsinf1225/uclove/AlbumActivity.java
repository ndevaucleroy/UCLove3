package lsinf1225.uclove;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        GridView gridview=(GridView)this.findViewById(R.id.gridview);

        Integer[] mThumbIds = {
                R.drawable.beericon, R.drawable.beericon, R.drawable.beericon,
                R.drawable.beericon, R.drawable.beericon, R.drawable.beericon,
                R.drawable.beericon, R.drawable.beericon, R.drawable.beericon,
                R.drawable.beericon, R.drawable.beericon, R.drawable.beericon
        };

        ImageAdapter myAdapter = new ImageAdapter(this);
        myAdapter.SetImages(mThumbIds);
        gridview.setAdapter(myAdapter);
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private Integer[] pics;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return pics.length;
        }

        public Object getItem(int position) {return null;}

        public long getItemId(int position) {return 0;}

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                //You can set some params here
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(pics[position]);
            return imageView;
        }

        public void SetImages(Integer[] id){
            pics = id.clone();
        }
    }

}
