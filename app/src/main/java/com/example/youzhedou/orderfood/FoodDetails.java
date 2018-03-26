package com.example.youzhedou.orderfood;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.youzhedou.orderfood.Model.Food;
import com.example.youzhedou.orderfood.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class FoodDetails extends AppCompatActivity {

    TextView foodName, foodPrice, foodDescription;
    ImageView foodImage;

    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton buttonCart;
    ElegantNumberButton buttonAmount;

    String foodId = "";

    FirebaseDatabase database;
    DatabaseReference foodList;
    //FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food");

        buttonAmount = (ElegantNumberButton) findViewById(R.id.buttonAmount);
        buttonCart = (FloatingActionButton) findViewById(R.id.buttonCart);
        foodDescription = (TextView) findViewById(R.id.food_description);
        foodName = (TextView) findViewById(R.id.food_name);
        foodPrice = (TextView) findViewById(R.id.food_price);
        foodImage = (ImageView) findViewById(R.id.img_food);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppbar);

        if(getIntent()!=null){
            foodId = getIntent().getStringExtra("FoodId");
        }
        if(!foodId.isEmpty()&&foodId!=null){
            getFoodDetail(foodId);
        }



    }

    private void getFoodDetail(String foodId) {
        foodList.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food food = dataSnapshot.getValue(Food.class);
                Picasso.with(getBaseContext()).load(food.getImage()).into(foodImage);
                collapsingToolbarLayout.setTitle(food.getName());
                foodPrice.setText(food.getPrice());
                foodName.setText(food.getName());
                foodDescription.setText(food.getDescription());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
