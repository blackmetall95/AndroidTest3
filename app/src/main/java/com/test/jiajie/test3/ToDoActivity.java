package com.test.jiajie.test3;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ToDoActivity extends FragmentActivity implements NewItemFragment.NewItemAddedListener {

    //Create an Array List of to do items
    private ArrayList<String> todoItems;
    //Create an Array Adapter
    private ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflating a view
        setContentView(R.layout.activity_to_do);

        //Fragment Manager
        FragmentManager fm = getFragmentManager();
        ToDoFragment toDoFragment = (ToDoFragment) fm.findFragmentById(R.id.ToDoFragment);

        //Declare an Array List
        todoItems = new ArrayList<>();
        //Declaring an Array Adapter to bind array to List View
        aa = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, todoItems);
        //Bind the Array Adapter to the List View
        toDoFragment.setListAdapter(aa);
    }

    @Override
    //Implement the NewItemAddedListener interface
    public void newItemAdded(String newItem) {
        todoItems.add(newItem);
        aa.notifyDataSetChanged();
    }

}