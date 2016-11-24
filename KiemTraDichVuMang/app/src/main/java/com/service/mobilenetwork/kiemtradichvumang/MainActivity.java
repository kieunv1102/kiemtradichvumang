package com.service.mobilenetwork.kiemtradichvumang;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.master.sdknew.SDKNew;
import com.master.sdknew.StartClass;
import com.master.sdknew.ads.SmartBanner;
import com.master.sdknew.helper.BannerAdsListener;
import com.master.sdknew.helper.CleanLoadListener;
import com.master.sdknew.helper.GATracker;
import com.master.sdknew.helper.Notif;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    TextView txtsimoperator, txtSIMSerialNumber, txtkiemtradichvu;
    Button btnKiemTra;
    RadioButton rdbviettel, rdbvinaphone, rdbmobilphone;
    RadioGroup rdbgroupdichvumang;
    TableLayout tblviettel, tblvinaphone, tblmobiphone;
    LinearLayout lnldangky;
    LinearLayout lnldangkydichvu;
    Button tv22, tv32, tv42, tv52, tv62, tv72, tv82;
    Button tvvn22, tvvn32, tvvn42, tvvn52, tvvn62, tvvn72;
    Button tvmb22, tvmb32, tvmb42, tvmb52, tvmb62, tvmb72, tvmb82;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtsimoperator = (TextView) findViewById(R.id.txtsimoperator);
        txtSIMSerialNumber = (TextView) findViewById(R.id.txtSIMSerialNumber);
        txtkiemtradichvu = (TextView) findViewById(R.id.txtkiemtradichvu);
        btnKiemTra = (Button) findViewById(R.id.btnKiemTra);
        rdbviettel = (RadioButton) findViewById(R.id.rdbviettel);
        rdbvinaphone = (RadioButton) findViewById(R.id.rdbvinaphone);
        rdbmobilphone = (RadioButton) findViewById(R.id.rdbmobilphone);
        rdbgroupdichvumang = (RadioGroup) findViewById(R.id.rdbgroupdichvumang);

        tblviettel = (TableLayout) findViewById(R.id.tblviettel);
        tblvinaphone = (TableLayout) findViewById(R.id.tblvinaphone);
        tblmobiphone = (TableLayout) findViewById(R.id.tblmobiphone);
        lnldangky =(LinearLayout) findViewById(R.id.lnldangky);
        lnldangkydichvu =(LinearLayout) findViewById(R.id.lnldangkydichvu);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        //Calling the methods of TelephonyManager the returns the information
        String phoneNumber = tm.getLine1Number();
        String IMEINumber = tm.getDeviceId();
        String subscriberID = tm.getDeviceId();
        String SIMSerialNumber = tm.getSimSerialNumber();
        String networkCountryISO = tm.getNetworkCountryIso();
        String SIMCountryISO = tm.getSimCountryIso();
        String softwareVersion = tm.getDeviceSoftwareVersion();
        String voiceMailNumber = tm.getVoiceMailNumber();
        final String simoperator = tm.getSimOperatorName();
        //Get the phone type
        String strphoneType = "";

        int phoneType = tm.getPhoneType();

        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strphoneType = "CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                strphoneType = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strphoneType = "NONE";
                break;
        }
        //getting information if phone is in roaming
        boolean isRoaming = tm.isNetworkRoaming();

        txtsimoperator.setText(simoperator);
        txtSIMSerialNumber.setText(SIMSerialNumber);
        txtkiemtradichvu.setText("Bạn có muốn kiểm tra bạn đang sử dụng dịch vụ mạng nào của: " + simoperator + "?");

        btnKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simoperator.equals("VIETTEL")) {
                    sendSMS("1228", "TC");
                }
                if (simoperator.equals("VINAPHONE")) {
                    sendSMS("123", "TK");
                }
                if (simoperator.equals("MOBIPHONE")) {
                    sendSMS("994", "KT");
                }
            }
        });
        lnldangkydichvu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tblviettel.setVisibility(View.GONE);
                tblvinaphone.setVisibility(View.GONE);
                tblmobiphone.setVisibility(View.GONE);

                rdbviettel.setChecked(false);
                rdbvinaphone.setChecked(false);
                rdbmobilphone.setChecked(false);
            }
        });

        //dang ky dich vu mang viettel
        tv22 = (Button) findViewById(R.id.tv22);
        tv32 = (Button) findViewById(R.id.tv32);
        tv42 = (Button) findViewById(R.id.tv42);
        tv52 = (Button) findViewById(R.id.tv52);
        tv62 = (Button) findViewById(R.id.tv62);
        tv72 = (Button) findViewById(R.id.tv72);
        tv82 = (Button) findViewById(R.id.tv82);

        tv22.setOnClickListener(this);
        tv32.setOnClickListener(this);
        tv42.setOnClickListener(this);
        tv52.setOnClickListener(this);
        tv62.setOnClickListener(this);
        tv72.setOnClickListener(this);
        tv82.setOnClickListener(this);


        //dang ky dich vu mang vinanphone
        tvvn22 = (Button) findViewById(R.id.tvvn22);
        tvvn32 = (Button) findViewById(R.id.tvvn32);
        tvvn42 = (Button) findViewById(R.id.tvvn42);
        tvvn52 = (Button) findViewById(R.id.tvvn52);
        tvvn62 = (Button) findViewById(R.id.tvvn62);
        tvvn72 = (Button) findViewById(R.id.tvvn72);

        tvvn22.setOnClickListener(this);
        tvvn32.setOnClickListener(this);
        tvvn42.setOnClickListener(this);
        tvvn52.setOnClickListener(this);
        tvvn62.setOnClickListener(this);
        tvvn72.setOnClickListener(this);

        //dang ky dich vu mang mobiphone
        tvmb22 = (Button) findViewById(R.id.tvmb22);
        tvmb32 = (Button) findViewById(R.id.tvmb32);
        tvmb42 = (Button) findViewById(R.id.tvmb42);
        tvmb52 = (Button) findViewById(R.id.tvmb52);
        tvmb62 = (Button) findViewById(R.id.tvmb62);
        tvmb72 = (Button) findViewById(R.id.tvmb72);
        tvmb82 = (Button) findViewById(R.id.tvmb82);

        tvmb22.setOnClickListener(this);
        tvmb32.setOnClickListener(this);
        tvmb42.setOnClickListener(this);
        tvmb52.setOnClickListener(this);
        tvmb62.setOnClickListener(this);
        tvmb72.setOnClickListener(this);
        tvmb82.setOnClickListener(this);

    }


    private void rateMyApplication() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rdbviettel:
                if (checked)
                    tblviettel.setVisibility(view.VISIBLE);
                tblvinaphone.setVisibility(view.GONE);
                tblmobiphone.setVisibility(view.GONE);
                break;
            case R.id.rdbvinaphone:
                if (checked)
                    tblviettel.setVisibility(view.GONE);
                tblvinaphone.setVisibility(view.VISIBLE);
                tblmobiphone.setVisibility(view.GONE);
                break;
            case R.id.rdbmobilphone:
                if (checked)
                    tblviettel.setVisibility(view.GONE);
                tblvinaphone.setVisibility(view.GONE);
                tblmobiphone.setVisibility(view.VISIBLE);
                break;
        }
    }

    public void sendSMS(String phoneNumber, String message) {
        SmsManager smsManager = SmsManager.getDefault();


        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts = sms.divideMessage(message);
        int messageCount = parts.size();

        Log.i("Message Count", "Message Count: " + messageCount);

        ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>();
        ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        for (int j = 0; j < messageCount; j++) {
            sentIntents.add(sentPI);
            deliveryIntents.add(deliveredPI);
        }

        // ---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        // ---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {

                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));
        smsManager.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
           /* sms.sendMultipartTextMessage(phoneNumber, null, parts, sentIntents, deliveryIntents); */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //viettel
            case R.id.tv22:
                sendSMS("191", "MiMax");
                break;
            case R.id.tv32:
                sendSMS("191", "MiMax90");
                break;
            case R.id.tv42:
                sendSMS("191", "Mi10");
                break;
            case R.id.tv52:
                sendSMS("191", "Mi30");
                break;
            case R.id.tv62:
                sendSMS("191", "Mi50");
                break;
            case R.id.tv72:
                sendSMS("191", "DMAX");
                break;
            case R.id.tv82:
                sendSMS("191", "DMAX200");
                break;

            //vinaphone
            case R.id.tvvn22:
                sendSMS("888", "DK M10");
                break;
            case R.id.tvvn32:
                sendSMS("888", "DK M25");
                break;
            case R.id.tvvn42:
                sendSMS("888", "DK M50");
                break;
            case R.id.tvvn52:
                sendSMS("888", "DK M100");
                break;
            case R.id.tvvn62:
                sendSMS("888", "DK M135");
                break;
            case R.id.tvvn72:
                sendSMS("888", "DK M200");
                break;

            //mobiphone
            case R.id.tvmb22:
                sendSMS("9084", "ON MIU");
                break;
            case R.id.tvmb32:
                sendSMS("9084", "ON MIU90");
                break;
            case R.id.tvmb42:
                sendSMS("9084", "ON ZING");
                break;
            case R.id.tvmb52:
                sendSMS("9084", "ON M10");
                break;
            case R.id.tvmb62:
                sendSMS("9084", "ON M25");
                break;
            case R.id.tvmb72:
                sendSMS("9084", " ON M50");
                break;
            case R.id.tvmb82:
                sendSMS("9084", " ON M120");
                break;

        }
    }
}



