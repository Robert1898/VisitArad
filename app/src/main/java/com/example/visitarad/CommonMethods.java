package com.example.visitarad;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.widget.ScrollView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class CommonMethods  {

    private Boolean isExpanded = false;

    public void setTitleActionBar(AppCompatActivity activity,String titlu)
    {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar!= null){
            actionBar.setTitle(titlu);
        }
    }

    public static void onClickUrl(Context context, String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }


    public static void onClickContact(Context context, String telNumber)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + telNumber));
        context.startActivity(intent);
    }

    public static void onClickMail(Context context, String mailText)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailText});

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(intent, "Alege o aplicație de e-mail"));
        } else {
            Toast.makeText(context, "Nu există nicio aplicație pe dispozitiv pentru a trimite mail-uri", Toast.LENGTH_LONG).show();
        }
    }

    public static void blockScrollView(ScrollView scrollView) {
        scrollView.requestDisallowInterceptTouchEvent(true);
    }

    public static void enableScrollView(ScrollView scrollView) {
        scrollView.requestDisallowInterceptTouchEvent(false);
    }

    public void getGoogleMapsMarker (GoogleMap googleMap, LatLng pozitie, String titluMarker,float zoomLevel){

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(pozitie).title(titluMarker));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pozitie, zoomLevel));

    }

    public void expandText(TextView prgramText, TextView expandText)
    {

        if (isExpanded){
                prgramText.setMaxLines(3);
                expandText.setText("+");
                isExpanded = false;

        }else {
            prgramText.setMaxLines(Integer.MAX_VALUE);
            expandText.setText("-");
            isExpanded = true;
        }

    }


}
