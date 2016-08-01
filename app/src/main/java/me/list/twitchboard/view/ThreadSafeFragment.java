package me.list.twitchboard.view;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by masterjefferson on 7/31/2016.
 */
public class ThreadSafeFragment extends Fragment {

    protected void threadSafeTextViewUpdate(final TextView tv, final String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(text);
            }
        });
    }

    protected void threadSafeImageViewUpdate(final ImageView view, final Drawable img) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setImageDrawable(img);
            }
        });
    }

    protected void threadSafeImageViewUpdate(final ImageView view, final String imageURL) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Picasso.with(getContext()).load(imageURL).into(view);
            }
        });
    }

}
