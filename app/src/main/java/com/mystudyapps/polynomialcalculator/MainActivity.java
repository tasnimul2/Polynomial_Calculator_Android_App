package com.mystudyapps.polynomialcalculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText pEquation ;
    EditText qEquation;
    TextView simplifyTV, outputTV, differenceTV;
    Button sumBTN, differenceBtn,productBtn;
    Animation scaleUp, scaleDown;
    public AbstractPolynomial p,q;

    private void setPolyomials(AbstractPolynomial p, AbstractPolynomial q){
        this.p = p;
        this.q = q;
    }

    //what ever is in the text view, this method will take it and set it at the p and q polynomial equation in the algorithm.
    private void updateOutput(){
        try {
            if(!pEquation.getText().toString().isEmpty() && !qEquation.getText().toString().isEmpty()) {
                setPolyomials(new Polynomial(pEquation.getText().toString()), new Polynomial(qEquation.getText().toString()));
            }else if(pEquation.getText().toString().isEmpty() && !qEquation.getText().toString().isEmpty()){
                setPolyomials(new Polynomial("x^2+2"),new Polynomial("x^3+3"));
                Toast.makeText(MainActivity.this, "Can't leave one input empty", Toast.LENGTH_SHORT).show();
            }else if(!pEquation.getText().toString().isEmpty() && qEquation.getText().toString().isEmpty()){
                setPolyomials(new Polynomial("x^2+2"),new Polynomial("x^3+3"));
                Toast.makeText(MainActivity.this, "Can't leave one input empty", Toast.LENGTH_SHORT).show();
            }else{
                setPolyomials(new Polynomial("x^2+2"),new Polynomial("x^3+3"));
                Toast.makeText(MainActivity.this, "Must input an equation first", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
        }
    }
    private void simplifyPolynomials(AbstractPolynomial p, AbstractPolynomial q){
        simplifyTV.setText("Polynomials Simplified\np = " + p + "\nq = " + q);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pEquation = findViewById(R.id.polynomialP);
        qEquation = findViewById(R.id.polynomialQ);
        simplifyTV = findViewById(R.id.simplifyTV);
        outputTV = findViewById(R.id.outputTV);
        sumBTN = (Button) findViewById(R.id.sumBtn);
        differenceBtn = (Button) findViewById(R.id.differenceBtn);
        productBtn = (Button) findViewById(R.id.productBtn);


        pEquation.setGravity(Gravity.CENTER);
        qEquation.setGravity(Gravity.CENTER);
        outputTV.setGravity(Gravity.CENTER);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        sumBTN.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    sumBTN.startAnimation(scaleUp);
                    updateOutput();
                    simplifyPolynomials(p, q);
                    outputTV.setText("Sum: " + p.add(q));
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    sumBTN.startAnimation(scaleDown);
                }
                return true;
            }

        });

        differenceBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    differenceBtn.startAnimation(scaleUp);
                    updateOutput();
                    simplifyPolynomials(p,q);
                    outputTV.setText("Difference: " + p.subtract(q));
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    differenceBtn.startAnimation(scaleDown);
                }
                return true;
            }
        });

        productBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    productBtn.startAnimation(scaleUp);
                    updateOutput();
                    simplifyPolynomials(p,q);
                    outputTV.setText("Product: " + p.multiply(q));
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    productBtn.startAnimation(scaleDown);
                }
                return true;
            }
        });



    }
}