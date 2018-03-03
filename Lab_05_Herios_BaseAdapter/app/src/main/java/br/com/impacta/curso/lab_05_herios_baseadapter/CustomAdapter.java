package br.com.impacta.curso.lab_05_herios_baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
    private long selectedId = -1;

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
        Object item = data.get(position);
        long id = 0;
        try {
            id = Long.parseLong(item.getClass().getMethod("getId").invoke(item).toString());
        } catch (Exception ex) {

        }
        return id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        T item = data.get(i);

        if (view == null) {
            view = inflater.inflate(resource, viewGroup, false);
        }

        int viewIndex = 0;
        for (int viewId : to) {
            View v = view.findViewById(viewId);
            String value = "";
            try {
                value = item.getClass().getMethod("get" + from[viewIndex]).invoke(item).toString();
            } catch (Exception ex) {

            }
            if (v instanceof TextView) {
                ((TextView) v).setText(value);
            } else if (v instanceof ImageView) {
                ((ImageView) v).setImageResource(Integer.parseInt(value));
            }
            viewIndex++;
        }


        LinearLayout ll = view.findViewById(R.id.celula_ll);

        long id = 0;
        try
        {
            id = Long.parseLong(item.getClass().getMethod("getId").invoke(item).toString());
        } catch (Exception ex)
        {

        }

        if (id == selectedId)
        {
            ll.setBackgroundColor(context.getResources().getColor(R.color.selectedColor));
        } else {
            ll.setBackgroundColor(context.getResources().getColor(R.color.unselectedColor));
        }

        return view;
    }

    public void setSelectedId(long id) {
        this.selectedId = id;
        notifyDataSetChanged();
    }
}
