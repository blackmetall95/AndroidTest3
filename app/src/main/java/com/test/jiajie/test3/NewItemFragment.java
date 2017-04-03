package com.test.jiajie.test3;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ArrayAdapter;

public class NewItemFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.new_fragment1, container, false);

        final EditText myEditText = (EditText)view.findViewById(R.id.myEditText);

        final NewItemListener nil = new NewItemListener() {
            @Override
            public void onItemAdded(String newItem) {

            }
        };
        myEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if((keyCode == KeyEvent.KEYCODE_ENTER)) {
                        String newItem = myEditText.getText().toString();
                        nil.onItemAdded(newItem);
                        myEditText.setText("");
                        return true;
                    }
                return false;
            }
        });
        return view;
    }

    public interface NewItemListener {
        void onItemAdded(String newItem);
    }

    private NewItemListener newItemListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            newItemListener = (NewItemListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    "must implement onItemAddedListener");
        }
    }
}
