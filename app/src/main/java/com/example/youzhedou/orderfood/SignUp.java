package com.example.youzhedou.orderfood;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.youzhedou.orderfood.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {


    Button buttonSignUp;
    MaterialEditText editTel, editName, editPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        editName = (MaterialEditText) findViewById(R.id.editName);
        editPwd = (MaterialEditText) findViewById(R.id.editPwd);
        editTel = (MaterialEditText) findViewById(R.id.editTel);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if already exist
                        if(dataSnapshot.child(editTel.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "You have registered before, please sign in", Toast.LENGTH_SHORT).show();
                        }else{
                            mDialog.dismiss();
                            User user = new User(editName.getText().toString(),editPwd.getText().toString());
                            table_user.child(editTel.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            finish();
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
