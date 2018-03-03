package br.com.impacta.curso.prj_007_listview_interface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rcaru on 03/03/2018.
 */

public class CustomAdapter<T> extends BaseAdapter {

    private Context context;
    private List<T> data;
    private int resource;
    private int[] to;
    private String[] from;


    public CustomAdapter(Context context, List<T> data, int resource, String[] from, int[] to) {
        this.context = context;
        this.data = data;
        this.resource = resource;
        this.to = to;
        this.from = from;
        inflater = LayoutInflater.from(context);
    }

    private LayoutInflater inflater;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        T item = (T)data.get(position);
        long id = Long.parseLong(invokeMethod("getId", item));
        return id;
    }


    public interface ICheckBoxChanged {
        void checkboxChanged(CompoundButton cb, boolean value, long id);
    }

    private ICheckBoxChanged checkboxChangedHandler;

    public void setOnCheckBoxChangedListener(ICheckBoxChanged handler) {
        checkboxChangedHandler = handler;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        T item = data.get(i);
        long itemId = Long.parseLong(invokeMethod("getId", item));

        if (view == null) {
            view = inflater.inflate(resource, viewGroup, false);
        }

        int viewIndex = 0;
        for (int viewId : to) {
            View v = view.findViewById(viewId);
            String value = invokeMethod("get" + from[viewIndex], item);

            if (v instanceof ImageView) {
                ((ImageView) v).setImageResource(Integer.parseInt(value));
            } else if (v instanceof CheckBox) {
                CheckBox cb = ((CheckBox) v);
                cb.setOnCheckedChangeListener(null);
                cb.setChecked(Boolean.parseBoolean(value));
                cb.setTag(itemId);
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        long id = (long) buttonView.getTag();
                        if (checkboxChangedHandler != null) {
                            checkboxChangedHandler.checkboxChanged(buttonView, isChecked, id);
                        }
                    }
                });
            } else if (v instanceof TextView) {
                ((TextView) v).setText(value);
            }
            viewIndex++;
        }


        return view;
    }

    private String invokeMethod(String methodName, T instance) {
        String value = "";
        try {
            value = instance.getClass().getMethod(methodName).invoke(instance).toString();
        } catch (Exception ex) {

        }

        return value;
    }

}
