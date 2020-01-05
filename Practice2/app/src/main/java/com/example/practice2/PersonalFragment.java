package com.example.practice2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;


public class PersonalFragment extends Fragment {




    public PersonalFragment() {

    }


    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        return fragment;
    }

    private EditText editText = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal, container, false);


        editText = v.findViewById(R.id.no2);
        Button button = v.findViewById(R.id.no16);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                editText.getText().toString();
                JSONObject jsonObject = createJSON();
                new CachePref().put("name",jsonObject.toString());

            }
        });

        return v;
    }

    private JSONObject createJSON(){
       JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",editText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}