package com.test.jiajie.test3;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class NewItemFragment extends Fragment {

    private NewItemAddedListener newItemAddedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Inflating a view
        View view = inflater.inflate(R.layout.new_fragment1, container, false);

        final EditText myEditText = (EditText)view.findViewById(R.id.myEditText);
        myEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if((keyCode == KeyEvent.KEYCODE_ENTER)) {
                        String newItem = myEditText.getText().toString();
                        newItemAddedListener.newItemAdded(newItem);
                        myEditText.setText("");
                        return true;
                    }
                return false;
            }
        });

        return view;
    }

    public interface NewItemAddedListener {
        void newItemAdded(String newItem);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            //Initialize the interface
            newItemAddedListener = (NewItemAddedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement onItemAddedListener");
        }
    }
}
