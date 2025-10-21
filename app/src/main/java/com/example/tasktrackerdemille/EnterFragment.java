package com.example.tasktrackerdemille;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tasktrackerdemille.model.Task;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EnterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinkedList<Task> tasks;
    EditText taskET;
    EditText ownerET;
    View.OnClickListener listener = new View.OnClickListener(){
        public void onClick(View view){
            String t = taskET.getText().toString();
            String o = ownerET.getText().toString();
            ContentValues values = new ContentValues();
            values.put(TaskContentProvider.COLUMN_TASK, t);
            values.put(TaskContentProvider.COLUMN_OWNER, o);
            getActivity().getContentResolver().insert(TaskContentProvider.CONTENT_URI, values);

            Cursor c = getActivity().getContentResolver().query(TaskContentProvider.CONTENT_URI,
                    null, null, null, null);
            if(c != null){
                c.moveToFirst();
                if(c.getCount() > 0){
                    while(c.isAfterLast() != false){
                        String ta = c.getString(1);
                        String oa = c.getString(2);
                        String message = ta + "--" + oa;
                        Log.i("TAG", message);
                        c.moveToNext();
                    }
                }
            }
        }
    };

    public EnterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EnterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EnterFragment newInstance(String param1, String param2) {
        EnterFragment fragment = new EnterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        tasks = new LinkedList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_enter, container, false);
        Button b = root.findViewById(R.id.button);
        taskET = root.findViewById(R.id.taskName);
        ownerET = root.findViewById(R.id.personName);
        b.setOnClickListener(listener);
        return root;

    }
}