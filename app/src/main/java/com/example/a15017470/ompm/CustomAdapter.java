package com.example.a15017470.ompm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by 15017470 on 20/7/2017.
 */

public class CustomAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Data> dataList;
    TextView rowName, rowAmount, rowDate, rowNumber;

    public CustomAdapter(Context context, int resource, ArrayList<Data> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.dataList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(resource, parent, false);

        rowName=(TextView)rowView.findViewById(R.id.rowName);
        rowAmount=(TextView)rowView.findViewById(R.id.rowAmount);
        rowDate=(TextView)rowView.findViewById(R.id.rowDate);
        rowNumber=(TextView)rowView.findViewById(R.id.rowNumber);

        Data data = (Data) this.dataList.get(position);
        rowName.setText("Name: " + data.getName());
        rowAmount.setText("Amount: " + data.getAmount());
        rowDate.setText("Date: " + data.getDate());
        rowNumber.setText(data.getNumber());
        return rowView;
    }
}
