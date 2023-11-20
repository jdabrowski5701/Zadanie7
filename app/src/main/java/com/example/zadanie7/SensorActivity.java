package com.example.zadanie7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SensorAdapter adapter;
    private SensorManager sensorManager;
    private List<Sensor> sensorList;

    private class SensorHolder extends RecyclerView.ViewHolder {

        private TextView sensorNameTextView;
        private ImageView sensorIconImageView;
        private Sensor sensor;

        public SensorHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.sensor_list_item, parent, false));
            //itemView.setOnClickListener(this);

            sensorNameTextView = itemView.findViewById(R.id.sensor_item_name);
            sensorIconImageView = itemView.findViewById(R.id.sensor_item_icon);
        }

        public void bind(Sensor sensor) {
            this.sensor = sensor;
            sensorNameTextView.setText(sensor.getName());
            sensorIconImageView.setImageResource(R.drawable.ic_sensor);

        }
    }

    private class SensorAdapter extends RecyclerView.Adapter<SensorHolder> {
        private List<Sensor> sensors;

        public SensorAdapter(List<Sensor> sensors) {
            this.sensors = sensors;
        }

        @NonNull
        @Override
        public SensorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(SensorActivity.this);
            return new SensorHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SensorHolder holder, int position) {
            Sensor sensor = sensors.get(position);
            holder.bind(sensor);
        }

        @Override
        public int getItemCount() {
            return sensors.size();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_activity);

        recyclerView = findViewById(R.id.sensor_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        if(adapter == null){
            adapter = new SensorAdapter(sensorList);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}