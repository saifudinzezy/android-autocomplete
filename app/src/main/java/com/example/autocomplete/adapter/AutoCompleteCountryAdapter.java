package com.example.autocomplete.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.autocomplete.R;
import com.example.autocomplete.model.CountryItem;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteCountryAdapter extends ArrayAdapter<CountryItem> {
    private List<CountryItem> countryItemListFull;

    public AutoCompleteCountryAdapter(@NonNull Context context, @NonNull List<CountryItem> countryItemList) {
        super(context, 0, countryItemList);
        countryItemListFull = new ArrayList<>(countryItemList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return countryFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.country_autocomplete_row, parent, false
            );
        }
        TextView textViewName = convertView.findViewById(R.id.text_view_name);
        ImageView imageViewFlag = convertView.findViewById(R.id.image_view_flag);
        CountryItem countryItem = getItem(position);

        if (countryItem != null) {
            textViewName.setText(countryItem.getCountryName());
            imageViewFlag.setImageResource(countryItem.getFlagImage());
        }

        return convertView;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<CountryItem> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                suggestions.addAll(countryItemListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                //search from list
                for (CountryItem item : countryItemListFull){
                    if (item.getCountryName().toLowerCase().contains(filterPattern)){
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((CountryItem) resultValue).getCountryName();
        }
    };
}
