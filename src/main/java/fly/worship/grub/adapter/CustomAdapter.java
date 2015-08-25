package fly.worship.grub.adapter;

import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.TreeSet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fly.worship.grub.R;

/**
 * Created by Ronan on 02/07/2014.
 */
public class CustomAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private ArrayList<String> mLabel = new ArrayList<>();
    private ArrayList<Integer> mIcon = new ArrayList<>();
    private TreeSet<Integer> sectionHeader = new TreeSet<>();

    private LayoutInflater mInflater;

    public CustomAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final String label, final int icon) {
        mLabel.add(label);
        mIcon.add(icon);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final String item) {
        mLabel.add(item);
        mIcon.add(0);
        sectionHeader.add(mLabel.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mLabel.size();
    }

    @Override
    public String getItem(int position) {
        return mLabel.get(position);
    }

    @Override
    public boolean isEnabled(int position) {
        int rowType = getItemViewType(position);
        switch (rowType) {
            case TYPE_ITEM:
                return true;
            case TYPE_SEPARATOR:
                return false;
            default:
                return false;
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.item_layout, parent, false);
                    holder.textView = (TextView) convertView.findViewById(R.id.label);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.item_header, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.textSeparator);
                    break;
            }
            if (convertView != null) {
                convertView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        switch (rowType) {
            case TYPE_ITEM:
                holder.textView.setText(mLabel.get(position));
                holder.imageView.setImageResource(mIcon.get(position));
                break;
            case TYPE_SEPARATOR:
                holder.textView.setText(mLabel.get(position));
                break;
        }
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

}