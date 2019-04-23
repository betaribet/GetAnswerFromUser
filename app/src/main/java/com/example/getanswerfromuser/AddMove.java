package com.example.getanswerfromuser;

import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMove {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String moveId;


    public AddMove() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("userAnswers");
        mFirebaseInstance.getReference("app_title").setValue("MultiChoiceQuestion");
    }
    public void create(String date, String question, String answer, boolean check) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(moveId)) {
            moveId = mFirebaseDatabase.push().getKey();
        }

        Move move = new Move(date, question, answer, check);

        mFirebaseDatabase.child(moveId).setValue(move);

        addMoveChangeListener();
    }

    /**
     * User data change listener
     */
    private void addMoveChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(moveId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Move move = dataSnapshot.getValue(Move.class);

                // Check for null
                if (move == null) {
                    Log.e(TAG, "Data is null!");
                    return;
                }

                Log.e(TAG, "Move data is changed!" + move.getDate() + ", " + move.getQuestion() + "," + move.getUserAnswer()+ "," + move.getCheck());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

}
