package com.example.rakshit.bechde;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link addad.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link addad#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addad extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button discard,publish;
    EditText title,desc;
    String titleString,descString;
    Button Addimages;
    DatabaseReference databaseReference,databaseReference1;
    String picturePath="p";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public addad() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addad.
     */
    // TODO: Rename and change types and number of parameters
    public static addad newInstance(String param1, String param2) {
        addad fragment = new addad();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_addad, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        MainActivity.fb.setVisibility(View.GONE);
        discard = (Button)v.findViewById(R.id.Discard);
        publish = (Button)v.findViewById(R.id.Publish);
        Addimages = (Button) v.findViewById(R.id.AddImages);
        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().show();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new home());
                ft.commit();
            }
        });


        Toast.makeText(getActivity(),""+home.postsList.size(),Toast.LENGTH_LONG).show();

        title = (EditText)v.findViewById(R.id.title);
        desc = (EditText)v.findViewById(R.id.discription);
        titleString = title.getText().toString();
        descString = desc.getText().toString();
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("All Posts");
        databaseReference = FirebaseDatabase.getInstance().getReference(login.auth.getCurrentUser().getUid().toString()).child("Post");
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(title.getText().toString())){

                    PrettyTime p = new PrettyTime();
                    String date = p.format(new Date(System.currentTimeMillis())).toString();

                    String id = databaseReference.push().getKey();
                    PostAd pad = new PostAd(id,title.getText().toString(),desc.getText().toString(),picturePath.toString(), login.auth.getCurrentUser().getEmail().toString(),date);
                    databaseReference.child(id).setValue(pad);
                    databaseReference1.child(id).setValue(pad);
                    Toast.makeText(getContext(),"Successfully Posted",Toast.LENGTH_LONG).show();
                    ((AppCompatActivity) getActivity()).getSupportActionBar().show();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame, new home());
                    ft.commit();
                }
                else{
                    Toast.makeText(getContext(),"Please Enter Title",Toast.LENGTH_LONG).show();
                }
            }
        });

        Addimages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
