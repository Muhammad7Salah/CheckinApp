package com.example.toshiba.checkinapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signin(View view){
        EditText mail = (EditText)findViewById(R.id.email);
        EditText pass = (EditText)findViewById(R.id.pass);

        String usermail = mail.getText().toString() ;
        String userpass = pass.getText().toString();

        if(usermail.matches("") && userpass.matches("")) {

            Toast.makeText(getApplicationContext(), "Please, fill the fields", Toast.LENGTH_SHORT).show();
        }


        else if(usermail.matches("")){

            Toast.makeText(getApplicationContext(),"Please, Enter the Email!",Toast.LENGTH_SHORT).show();

        }


        else if(pass.getText().toString().matches("")){

            Toast.makeText(getApplicationContext(),"Please, Enter the Password!",Toast.LENGTH_SHORT).show();

        }

        else if(usermail.equals("test") && userpass.equals("password")) {

            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);

        }
        else
        {

            Toast.makeText(getApplicationContext(), "wrong email or password!", Toast.LENGTH_SHORT).show();

        }



    }




    public void Signup(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
