package com.example.firebase_mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    Button vote;
    Member member;
    RadioButton radioButton, radioButton2, radioButton3;
    FirebaseDatabase database;
    DatabaseReference reference;
    int i = 0;
    EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vote = findViewById(R.id.vote);
        member = new Member();
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

        reference = database.getInstance().getReference().child("User");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    i = (int)dataSnapshot.getChildrenCount();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        vote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String m1 = radioButton.getText().toString();
                String m2 = radioButton2.getText().toString();
                String m3 = radioButton3.getText().toString();

                if(radioButton.isChecked()) {
                    member.setYvote(m1);
                    reference.child(String.valueOf(i + 1)).setValue(member);
                    Toast.makeText(getBaseContext(), "Your vote is registered, THANK YOU." , Toast.LENGTH_SHORT ).show();
                }


                else if(radioButton2.isChecked()){
                    member.setYvote(m2);
                    reference.child(String.valueOf(i + 1)).setValue(member);
                    Toast.makeText(getBaseContext(), "Your vote is registered, THANK YOU." , Toast.LENGTH_SHORT ).show();
                }
                else if(radioButton3.isChecked()){
                    member.setYvote(m3);
                    reference.child(String.valueOf(i + 1)).setValue(member);
                    Toast.makeText(getBaseContext(), "Your vote is registered, THANK YOU." , Toast.LENGTH_SHORT ).show();
                }
                else{
                    Toast.makeText(getBaseContext(), "Please select an option!!" , Toast.LENGTH_SHORT ).show();
                }
            }
        });

    }
}