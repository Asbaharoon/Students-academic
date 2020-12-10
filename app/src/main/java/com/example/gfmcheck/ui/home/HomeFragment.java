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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    dataact dataactobj;
    public String prev2,st1,st2,st3;
    private EditText l;
    private Button send1;
    protected int att,tot,grand,s1,s2,s3;
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

                        //piechart
                        PieChart pieChart=root.findViewById(R.id.piechart);
//pieChart.setUsePercentValues(true);
                        List<PieEntry> value=new ArrayList<>();
                        value.add(new PieEntry(100,"current"));
                        value.add(new PieEntry(s1,"total"));
                        PieDataSet pieDataSet=new PieDataSet(value,"");
                        PieData piedata =new PieData(pieDataSet);
                        pieChart.setData(piedata);
                        Description desc=new Description();
                        desc.setText("");
                        pieChart.setDescription(desc);
                        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                        pieChart.animateXY(1400,1400);
//



                        //piechart
                        PieChart pieChart1=root.findViewById(R.id.piechart1);
//pieChart.setUsePercentValues(true);
                        List<PieEntry> value1=new ArrayList<>();
                        value1.add(new PieEntry(grand,"total"));
                        value1.add(new PieEntry((s3),"Remaining"));
                        PieDataSet pieDataSet1=new PieDataSet(value1,"");
                        PieData piedata1 =new PieData(pieDataSet1);
                        pieChart1.setData(piedata1);
                        Description desc1=new Description();
                        desc1.setText("");
                        pieChart1.setDescription(desc1);
                        pieDataSet1.setColors(ColorTemplate.LIBERTY_COLORS);
                        pieChart1.animateXY(1400,1400);
//


//int[] colors={25,27};
                        //piechart
                        PieChart pieChart2=root.findViewById(R.id.piechart2);
//pieChart.setUsePercentValues(true);
                        List<PieEntry> value2=new ArrayList<>();
                        value2.add(new PieEntry(s3,"Total"));
                        value2.add(new PieEntry(s2,"Required"));
                        PieDataSet pieDataSet2=new PieDataSet(value2,"");
                        PieData piedata2 =new PieData(pieDataSet2);
                        pieChart2.setData(piedata2);
                        Description desc2=new Description();
                        desc2.setText("");
                        pieChart2.setDescription(desc2);
                        pieDataSet2.setColors(ColorTemplate.LIBERTY_COLORS);
                        pieChart2.animateXY(1400,1400);
//
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