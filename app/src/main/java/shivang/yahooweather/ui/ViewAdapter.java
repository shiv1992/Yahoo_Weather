package shivang.yahooweather.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import shivang.yahooweather.R;
import shivang.yahooweather.data.ForecastObject;

/**
 * Created by SHIVVVV on 10/10/2017.
 */

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.RecyclerViewHolder>{

    private List<ForecastObject> testObj;
    private Context context;

    @Override
    public ViewAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_view,parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewAdapter.RecyclerViewHolder holder, int position) {

        holder.date.setText(testObj.get(position).getDate());
        holder.day.setText(testObj.get(position).getDay());
        holder.high.setText(testObj.get(position).getHigh()+" F");
        holder.low.setText(testObj.get(position).getLow()+ " F");
        holder.text.setText(testObj.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return testObj.size();
    }

    public ViewAdapter(Context conn, List<ForecastObject> list) {
        this.testObj = list;
        this.context = conn;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private TextView date;
        private TextView day;
        private TextView high;
        private TextView low;
        private TextView text;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textDate);
            day = itemView.findViewById(R.id.textDay);
            high = itemView.findViewById(R.id.textHigh);
            low = itemView.findViewById(R.id.textLow);
            text = itemView.findViewById(R.id.textText);
        }

    }
}
