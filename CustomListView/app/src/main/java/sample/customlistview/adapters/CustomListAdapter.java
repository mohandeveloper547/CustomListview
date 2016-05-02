package sample.customlistview.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

import sample.customlistview.dto.SimpleDTO;
import sample.customlistview.main.R;

/**
 * Created by Shyam Yammanuru on 4/21/2016.
 */
public class CustomListAdapter extends BaseAdapter {
    ArrayList<SimpleDTO> list;

    LayoutInflater inflater = null;
    ImageLoader imageLoader;

    public CustomListAdapter( LayoutInflater inflater,ImageLoader imageLoader, ArrayList<SimpleDTO> list)
    {
        this.list = list;
        this.inflater = inflater;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        if(list != null)
            return list.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        final Holder holder;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.custom_video_list_item, null);
            holder = new Holder();
            holder.title = (TextView) rowView.findViewById(R.id.title);
            holder.description = (TextView) rowView.findViewById(R.id.description);
            holder.img = (ImageView) rowView.findViewById(R.id.imgPreview);

            holder.pBar = (ProgressBar) rowView.findViewById(R.id.pBar);
            rowView.setTag(holder);
        } else {
            holder = (Holder) rowView.getTag();
        }
        SimpleDTO dto = (SimpleDTO)getItem(position);
        if(dto != null) {
            if(dto.getTitle() != null)
                holder.title.setText(dto.getTitle());
            else
                holder.title.setText("");
            if(dto.getDescription() != null)
                holder.description.setText(dto.getDescription());
            else
                holder.description.setText("");



            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)//<----very important for recycling views
                    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                    .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                    .displayer(new SimpleBitmapDisplayer()) // default
                    .build();


            if (!holder.isSameView(position)) {  // If image is loaded correctly skip else reload and set new image
                holder.img.setTag(position);
                if(dto.getImageUrl() != null && !dto.getImageUrl().equalsIgnoreCase("")) {
                    holder.pBar.setVisibility(View.VISIBLE);
                    holder.img.setVisibility(View.GONE);
                    imageLoader.loadImage(dto.getImageUrl(), options, new SimpleImageLoadingListener() {
                                @Override
                                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                    // Do whatever you want with Bitmap
                                    holder.pBar.setVisibility(View.GONE);
                                    holder.img.setVisibility(View.VISIBLE);
                                    holder.img.setImageBitmap(loadedImage);
                                }

                                @Override
                                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                                    if (failReason != null && view != null)
                                        view.setTag(failReason.getType().name()); // You can handle errors and avoid repeating requests that return error codes
                                }
                            }
//            imageLoader.displayImage(video.getThumbNailId(), holder.img, new SimpleImageLoadingListener() {
//                @Override
//                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                view.setTag(failReason.getType().name()); // You can handle errors and avoid repeating requests that return error codes
//            }
//            }
                    );
                }
            }
        }
        return rowView;
    }
    public class Holder {
        TextView title, description;
        ImageView img;
        ProgressBar pBar;

        public boolean isSameView(Integer tag) {
            if (tag == null) {
                return img.getTag() == null;
            }
            return tag.equals(img.getTag());
        }
    }
    public void updateList(final ArrayList<SimpleDTO> newList) {

        CustomListAdapter.this.list = newList;
        CustomListAdapter.this.notifyDataSetChanged();
        CustomListAdapter.this.notifyDataSetInvalidated();

    }
}
