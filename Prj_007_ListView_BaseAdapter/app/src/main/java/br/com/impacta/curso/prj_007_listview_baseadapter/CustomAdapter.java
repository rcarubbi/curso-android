package br.com.impacta.curso.prj_007_listview_baseadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rcaru on 03/03/2018.
 */

public class CustomAdapter<T> extends BaseAdapter {

    private Context context;
    private int resource;
    private List<T> data;
    private LayoutInflater inflater;
    private String[] from;
    private int[] to;

    private int contador = 0;
    private long selectedId = -1;

    public void setSelectedId(long id) {
        this.selectedId = id;
        notifyDataSetChanged();
    }



    public CustomAdapter(Context context, int resource, List<T> data, String[] from, int[] to) {
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.from = from;
        this.to = to;
        // this.inflater = LayoutInflater.from(context);
    }

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
        Object instance = data.get(position);
        try {
            Object value = instance.getClass().getMethod("getId").invoke(instance, null);
            return Long.parseLong(value.toString());
        }
        catch(Exception ex) {
            return 0;
        }
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        boolean novaCelula = false;

        T item = data.get(position);


        if (convertView == null) {
            convertView = inflater.inflate(
                    resource,
                    parent,
                    false);
            novaCelula = true;
            contador++;
        }


        int viewIndex = 0;
        for (int viewId: to) {
            View v = convertView.findViewById(viewId);
            if (v instanceof TextView)
            {
                try {
                    String value = item.getClass().getMethod("get" + from[viewIndex]).invoke(item).toString();
                    ((TextView) v).setText(value);
                }
                catch(Exception ex)
                {

                }
            }
            viewIndex++;
        }

        LinearLayout ll = convertView.findViewById(R.id.celula_ll);
        long id = 0;
        try {
            id = Long.parseLong(item.getClass().getMethod("getId").invoke(item).toString());
        }
        catch(Exception ex) {

        }

        if (id == selectedId) {
            ll.setBackgroundColor(context.getResources().getColor(R.color.selectedItem));
        } else {
            ll.setBackgroundColor(context.getResources().getColor(R.color.unselectedItem));
        }


        TextView tv_contador = convertView.findViewById(R.id.celula_tv_contador);
        if (novaCelula) {
            tv_contador.setText(String.valueOf(contador));
        }

        return convertView;

    }
}
