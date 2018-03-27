package com.example.youzhedou.orderfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.youzhedou.orderfood.Common.Common;
import com.example.youzhedou.orderfood.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    Button buttonSignIn;
    EditText editTel, editPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        editPwd = (EditText) findViewById(R.id.editPwd);
        editTel = (EditText) findViewById(R.id.editTel);

        //initialise Firebase

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //check if user in db
                        if(dataSnapshot.child(editTel.getText().toString()).exists()) {
                            //Get user info
                            mDialog.dismiss();
                            User user = dataSnapshot.child(editTel.getText().toString()).getValue(User.class);
                            user.setTel(editTel.getText().toString());
                            //System.out.println(user.getName());
                            if (user.getPassword().equals(editPwd.getText().toString())) {
                                //Toast.makeText(SignIn.this, "Sign in successfully!", Toast.LENGTH_SHORT).show();
                                Intent homeIntent = new Intent(SignIn.this,Home.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "No such user!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
