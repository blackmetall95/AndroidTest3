package com.test.jiajie.test3;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoActivity extends Activity {

    //Create an Array List of to do items
    private ArrayList<String> todoItems;
    //Create an Array Adapter
    private ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do); //inflating a view
        //Get reference to UI widgets
        //ListView myListView = (ListView) findViewById(R.id.myListView);

        //Fragment Manager
        FragmentManager fm = getFragmentManager();
        ToDoFragment toDoFragment = (ToDoFragment) fm.findFragmentById(R.id.ToDoFragment);
        //Declare an Array List
        todoItems = new ArrayList<String>();
        //Declaring an Array Adapter to bind array to List View
        aa = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, todoItems);
        //Bind the Array Adapter to the List View
        toDoFragment.setListAdapter(aa);
    }

    public class ToDoList extends Activity implements NewItemFragment.NewItemListener {
        public void onItemAdded(String newItem) {
            todoItems.add(newItem);
            aa.notifyDataSetChanged();
        }
    }
}