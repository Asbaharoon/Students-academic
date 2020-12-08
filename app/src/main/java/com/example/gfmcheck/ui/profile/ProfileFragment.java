package com.example.gfmcheck.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gfmcheck.MainActivity;
import com.example.gfmcheck.R;
import com.example.gfmcheck.add;
import com.example.gfmcheck.dataact;
import com.example.gfmcheck.loginact;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.util.EnumMap;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public DatabaseReference myRef;
    public ValueEventListener eventListener;
    private Button editForm;
    public String zprn,first,last,middle,email,mom,bd,ms,mp,blood,cgpa,blogs,prev;
    private Button submit1;
    private EditText f;
    private EditText m;
    private EditText l;
    private EditText ei;
    private EditText zprns;
    private EditText mother;
    private EditText birth;
    private EditText mobs;
    private EditText mobp;
    private EditText blood1;
    private EditText cgpa1;
    private EditText blog;

    //private EditText conpwd;
    dataact dataactobj;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        final View root1 = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root1.findViewById(R.id.text_profile);
        profileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

        dataactobj = (dataact)getActivity();
        prev=dataactobj.usernameAct;



                f=(EditText)root1.findViewById(R.id.fname);
                m=(EditText)root1.findViewById(R.id.mname);
                l=(EditText)root1.findViewById(R.id.lname);
                ei=(EditText)root1.findViewById(R.id.emailad);
                zprns=(EditText)root1.findViewById(R.id.zprns);
                submit1=root1.findViewById(R.id.submit);
                editForm=root1.findViewById(R.id.edit);
                mother=root1.findViewById(R.id.momname);
                birth=root1.findViewById(R.id.dob);
                mobs=root1.findViewById(R.id.smno);
                mobp=root1.findViewById(R.id.pmno);
                blood1=root1.findViewById(R.id.blood);
                cgpa1=root1.findViewById(R.id.cgpa);
                blog=root1.findViewById(R.id.backlogs);
//************************************************************************************************
                myRef = FirebaseDatabase.getInstance().getReference("User").child(prev);

                eventListener = myRef.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {

                        zprn = dataSnapshot.child("zprn").getValue().toString();
                        first = dataSnapshot.child("first").getValue().toString().trim();
                        middle = dataSnapshot.child("middle").getValue().toString().trim();
                        last = dataSnapshot.child("last").getValue().toString().trim();
                        email = dataSnapshot.child("email").getValue().toString().trim();
                        mom = dataSnapshot.child("mother").getValue().toString().trim();
                        bd = dataSnapshot.child("date_of_birth").getValue().toString().trim();
                        ms= dataSnapshot.child("mobstudent").getValue().toString().trim();
                        mp = dataSnapshot.child("mobparent").getValue().toString().trim();
                        blood = dataSnapshot.child("blood").getValue().toString().trim();
                        cgpa = dataSnapshot.child("cgpa").getValue().toString().trim();
                        blogs = dataSnapshot.child("backlogs").getValue().toString().trim();

                        zprns.setText(zprn);
                        f.setText(first);
                        m.setText(middle);
                        l.setText(last);
                        ei.setText(email);
                        mother.setText(mom);
                        birth.setText(bd);
                        mobs.setText(ms);
                        mobp.setText(mp);
                        blood1.setText(blood);
                        cgpa1.setText(cgpa);
                        blog.setText(blogs);


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });
// ***********************************************************************************************

               editForm.setOnClickListener(  new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Read from the database




                        myRef = FirebaseDatabase.getInstance().getReference("User").child(prev);

                        eventListener = myRef.addValueEventListener(new ValueEventListener() {
                            @Override

                            public void onDataChange(DataSnapshot dataSnapshot) {

                                zprn = dataSnapshot.child("zprn").getValue().toString();
                                first = dataSnapshot.child("first").getValue().toString().trim();
                                middle = dataSnapshot.child("middle").getValue().toString().trim();
                                last = dataSnapshot.child("last").getValue().toString().trim();
                                email = dataSnapshot.child("email").getValue().toString().trim();
                                mom = dataSnapshot.child("mother").getValue().toString().trim();
                                bd = dataSnapshot.child("date_of_birth").getValue().toString().trim();
                                ms= dataSnapshot.child("mobstudent").getValue().toString().trim();
                                mp = dataSnapshot.child("mobparent").getValue().toString().trim();
                                blood = dataSnapshot.child("blood").getValue().toString().trim();
                                cgpa = dataSnapshot.child("cgpa").getValue().toString().trim();
                                blogs = dataSnapshot.child("backlogs").getValue().toString().trim();

                                zprns.setText(zprn);
                                f.setText(first);
                                m.setText(middle);
                                l.setText(last);
                                ei.setText(email);
                                mother.setText(mom);
                                birth.setText(bd);
                                mobs.setText(ms);
                                mobp.setText(mp);
                                blood1.setText(blood);
                                cgpa1.setText(cgpa);
                                blog.setText(blogs);


                                submit1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference myRef = database.getReference("User");

                                       // String zprn1=.getText().toString().trim();

                                        //myRef.child(prev).setValue(add2);
                                        updateData();


                                    }
                                });



                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value

                            }
                        });
                    }
                });

            }
        });


        return root1;
    }

    private void updateData() {
      FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref1 = database.getReference();
        myref1.child("User").child(prev).child("last").setValue(l.getText().toString());
        myref1.child("User").child(prev).child("email").setValue(ei.getText().toString());
        myref1.child("User").child(prev).child("mother").setValue(mother.getText().toString());
        myref1.child("User").child(prev).child("date_of_birth").setValue(birth.getText().toString());
        myref1.child("User").child(prev).child("mobstudent").setValue(mobs.getText().toString());
        myref1.child("User").child(prev).child("mobparent").setValue(mobp.getText().toString());
        myref1.child("User").child(prev).child("blood").setValue(blood1.getText().toString());
        myref1.child("User").child(prev).child("cgpa").setValue(cgpa1.getText().toString());
        myref1.child("User").child(prev).child("backlogs").setValue(blog.getText().toString());

    }
}