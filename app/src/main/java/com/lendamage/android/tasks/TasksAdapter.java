package com.lendamage.android.tasks;

import android.content.Context;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends BaseAdapter {

    private final Context context;
    List<String> list = new ArrayList<String>();

    public TasksAdapter(Context context, String taskPath) {
        this.context = context;
        try {
            Reader jsonFile = new InputStreamReader(this.context.getAssets().open("tasks.json"));
            JsonReader json = new JsonReader(jsonFile);
            readTaskList(json, taskPath, "");
        } catch (IOException e) {
            //ignore error
        }
    }

    void readTaskList(JsonReader json, String taskPath, String curPath) throws IOException {
        json.beginArray();
        while (json.hasNext()) {
            json.beginObject();
            while (json.hasNext()) {
                String name = json.nextName();
                if ("name".equals(name)) {
                    String taskName = json.nextString();
                    if (curPath.equals(taskPath)) {
                        this.list.add(taskName);
                    }
                    curPath = curPath + "/" + taskName;
                } else if ("sub".equals(name)) {
                    readTaskList(json, taskPath, curPath);
                }
            }
            json.endObject();
        }
        json.endArray();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(
                    android.R.layout.simple_list_item_1, viewGroup, false);
        }
        TextView text = (TextView) view.findViewById(android.R.id.text1);
        text.setText(this.list.get(i));
        return view;
    }

}
