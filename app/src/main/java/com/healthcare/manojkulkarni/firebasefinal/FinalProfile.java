package com.healthcare.manojkulkarni.firebasefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.healthcare.ModelClass.UserClass;
import com.healthcare.R;

public class FinalProfile extends AppCompatActivity {



    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    TextView Name , Contact , addres , donate;

    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("DATA");

        Name = (TextView) findViewById(R.id.Name);

        Contact = (TextView) findViewById(R.id.Contact);

        addres = (TextView) findViewById(R.id.addres);

        donate = (TextView) findViewById(R.id.donate);


        btnEdit = (Button) findViewById(R.id.EditProfile);


            GETDATA();

    }


    public  void GETDATA(){


        databaseReference.child("USERS_DATA").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){


                    try {


                        UserClass userClass = dataSnapshot.getValue(UserClass.class);



                        Name.setText(userClass.getFirstName());
                        Contact.setText(userClass.getContact());
                        addres.setText(userClass.getAddress());
                        donate.setText("Unspecified");

                    }catch (Exception e){


                    }


                }else{


                    Toast.makeText(FinalProfile.this, "Not Found Data", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
