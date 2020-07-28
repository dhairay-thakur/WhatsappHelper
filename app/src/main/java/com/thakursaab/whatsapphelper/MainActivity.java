package com.thakursaab.whatsapphelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        String number ="0";
        Intent intent=this.getIntent();
        if(intent.getAction()==Intent.ACTION_PROCESS_TEXT){
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString();
        }
        if (number.charAt(0) == '+'){
            number = number.substring(1);
        }
        if(number.length()<=13){
            number=number.replaceAll("\\s", "");
        }
        if(number.matches("[0-9]+")){
            startWhatsapp(number);
        }
        else{
            Toast.makeText(this,"Number pe message bhejta hu main bas", Toast.LENGTH_SHORT).show();
        }

    }

    private void startWhatsapp(String number) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setPackage("com.whatsapp");
        String data = number.length() == 10 ? "91" + number : number;
        intent.setData(Uri.parse("https://wa.me/" + data));
        if (this.getPackageManager().resolveActivity(intent, 0) != null) {
            this.startActivity(intent);
        } else {
            Toast.makeText((Context)this, (CharSequence)"Whatsapp toh hai hi nahi!", Toast.LENGTH_SHORT).show();
        }
        this.finish();
    }
}