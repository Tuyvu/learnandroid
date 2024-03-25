package com.example.lap1;

//import com.squareup.picasso.Picasso;
import android.app.Activity;
import android.view.LayoutInflater;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adaptercustom extends BaseAdapter implements Filterable {
    private Activity context;
    int Idlayout;
    private ArrayList<Person> data;
    private ArrayList<Person> databackup;

    public void setData(ArrayList<Person> data) {
        this.data=data;
    }


    public Adaptercustom(Activity context, int idlayout, ArrayList<Person> data) {
        this.context = context;
        Idlayout = idlayout;
        this.data = data;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.layout_list, null);



        ImageView img = convertView.findViewById((R.id.imgavt));
        TextView HoTen = convertView.findViewById((R.id.HoTen));
        TextView SDT = convertView.findViewById((R.id.SDT));



        Person person = data.get(position);

        ImageView ming = person.getImages();
        Bitmap bm= ((BitmapDrawable)ming.getDrawable()).getBitmap();

        HoTen.setText(person.getHoTen());
        SDT.setText(person.getSDT());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter f = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults fr = new FilterResults();
                // backup du lieu: luu tam du lieu vao databackup
                if(databackup == null) {
                    databackup = new ArrayList<>(data);
                }
                // neu chuoi de filter la rong thi khoi phuc du lieu
                if(constraint == null || constraint.length()==0){
                    fr.count = databackup.size();
                    fr.values = databackup;
                }
                //
                else {
                    ArrayList<Person> newdata = new ArrayList<>();
                    for(Person i : databackup)
                        if(i.getHoTen().toLowerCase().contains(
                                constraint.toString().toLowerCase()
                        ))
                            newdata.add(i);
                    fr.count = databackup.size();
                    fr.values = databackup;
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = new ArrayList<Person>();
                ArrayList<Person> tmp = (ArrayList<Person>)results.values;
                for (Person i: tmp)
                    data.add(i);
                notifyDataSetChanged();
            }
        };
        return f;
    }
}
