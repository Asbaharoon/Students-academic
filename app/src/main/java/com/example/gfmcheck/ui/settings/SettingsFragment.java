package com.example.gfmcheck.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gfmcheck.R;
import com.example.gfmcheck.dataact;
import com.example.gfmcheck.loginact;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsFragment extends Fragment {
    private String prev11,oldp,newp,connewp,oldp1;
    private EditText op;
    private EditText np;
    private EditText cnp;
    private SettingsViewModel settingsViewModel;
    private Button logout1;
    private Button set1;
    public DatabaseReference myRef;
    public ValueEventListener eventListener;
    dataact dataactobj;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
       final View root = inflater.inflate(R.layout.fragment_settings, container, false);
        logout1=(Button)root.findViewById(R.id.logout);

        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten=new Intent(getActivity(),loginact.class);
                startActivity(inten);
            }
        });


        final TextView textView = root.findViewById(R.id.text_settings);
        settingsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);



            }
        });


        dataactobj = (dataact)getActivity();
        prev11=dataactobj.usernameAct;

        op=(EditText)root.findViewById(R.id.opwd);
        np=(EditText)root.findViewById(R.id.npwd);
        cnp=(EditText)root.findViewById(R.id.cnpwd);

        set1=root.findViewById(R.id.set);

//


        set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // oldp1=op.getText().toString().trim();
                //newp=np.getText().toString().trim();
                //connewp=cnp.getText().toString().trim();
                //FirebaseDatabase database = FirebaseDatabase.getInstance();
                //DatabaseReference myRef = database.getReference("User");

                myRef = FirebaseDatabase.getInstance().getReference("User").child(prev11);

                eventListener = myRef.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        oldp1=op.getText().toString().trim();


                        oldp = dataSnapshot.child("pass").getValue().toString();
                        //first = dataSnapshot.child("first").getValue().toString().trim();
                        //middle = dataSnapshot.child("middle").getValue().toString().trim();
                        TextView txt1=root.findViewById(R.id.now1);
                        txt1.setText(oldp1);

                        TextView txt=root.findViewById(R.id.now);
                        txt.setText(oldp);
                        if(TextUtils.equals(oldp,oldp1))
                        {
                            newp=np.getText().toString().trim();
                            connewp=cnp.getText().toString().trim();
                            if(newp.equals(connewp))
                            {
                                FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                                DatabaseReference myref55 = database1.getReference();
                                myref55.child("User").child(prev11).child("pass").setValue(newp);
                                myref55.child("User").child(prev11).child("copass").setValue(connewp);
                            }
                            else{cnp.setError("Invalid Credentials");}
                        }
                        else{
                        op.setError("Invalid");
                        }
                        // String zprn1=.getText().toString().trim();

                        //myRef.child(prev).setValue(add2);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });



            }

            //
        });
        return root;}

        }


