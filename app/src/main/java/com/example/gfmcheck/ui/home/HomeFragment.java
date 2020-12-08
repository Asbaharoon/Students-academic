package com.example.gfmcheck.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gfmcheck.R;
import com.example.gfmcheck.dataact;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    dataact dataactobj;
    public String prev2,st1,st2,st3;
    private EditText l;
    private Button send1;
    private int att,tot,grand,s1,s2,s3;
    public DatabaseReference myRef;
    public ValueEventListener eventListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

                /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myref12 = database.getReference();
                myref12.child("Academics").child(prev2).child("Attendence").setValue();
                myref12.child("Academics").child(prev2).child("total").setValue();*/
                dataactobj = (dataact)getActivity();
                prev2=dataactobj.usernameAct;
                l=(EditText)root.findViewById(R.id.comment);
                send1=root.findViewById(R.id.send);

                myRef = FirebaseDatabase.getInstance().getReference("Academics").child(prev2);

                eventListener = myRef.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {

                        st1= dataSnapshot.child("attendence").getValue().toString();
                        att=Integer.valueOf(st1);
                        st2= dataSnapshot.child("total").getValue().toString();
                        tot=Integer.valueOf(st2);
                        st3= dataSnapshot.child("grandtotal").getValue().toString();
                        grand=Integer.valueOf(st3);

                        s1=(( att*100) /tot);

                        s2=(((grand*75)/100)-att);

                        s3=(grand-tot);


                        TextView txt1=root.findViewById(R.id.tttt1);
                        txt1.setText(String.valueOf(s1));
                        TextView txt2=root.findViewById(R.id.t2);
                        txt2.setText(String.valueOf(s2));
                        TextView txt3=root.findViewById(R.id.t3);
                        txt3.setText(String.valueOf(s3));



                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });



                send1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myref1 = database.getReference();
                        myref1.child("Academics").child(prev2).child("comment").setValue(l.getText().toString());

                        // String zprn1=.getText().toString().trim();

                        //myRef.child(prev).setValue(add2);



                    }
                });




            }
        });


        return root;
    }
}