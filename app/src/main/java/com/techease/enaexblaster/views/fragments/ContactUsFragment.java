package com.techease.enaexblaster.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactUsFragment extends Fragment implements View.OnClickListener {
    View view;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.layout_contact)
    RelativeLayout layoutContact;
    @BindView(R.id.layout_send_mail)
    RelativeLayout layoutSendMail;
    @BindView(R.id.layout_website)
    RelativeLayout layoutVisitWebsite;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    private void initViews(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragment(getActivity(),new HomeFragment());
            }
        });

        layoutContact.setOnClickListener(this);
        layoutSendMail.setOnClickListener(this);
        layoutVisitWebsite.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.layout_contact:
               makeCall();
               break;
           case R.id.layout_send_mail:
               sendMail();
               break;
           case R.id.layout_website:
               Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.enaexusa.com/"));
               startActivity(browserIntent);
               break;
       }
    }

    private void sendMail(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"contact-usa@enaex.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Enaex");
        i.putExtra(Intent.EXTRA_TEXT   , "write you query");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeCall(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+18015623045"));
        startActivity(intent);
    }
}
