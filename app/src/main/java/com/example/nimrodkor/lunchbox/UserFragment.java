package com.example.nimrodkor.lunchbox;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserFragment extends Fragment {
    @BindView(R.id.welcome_user) TextView mWelcome;
    @BindView(R.id.profile_pic) ImageView mProfilePic;
    private String mId;
    private String mName;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setUser(String name, String id) {
        mId = id;
        mName = name;
        mWelcome.setText(String.format("%s", mName));
    }

    public String getName() {
        return mName;
    }

    public String getUserId() {
        return mId;
    }
}
