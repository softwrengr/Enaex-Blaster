package com.techease.enaexblaster.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.techease.enaexblaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenPDFActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.pdfview)
    PDFView pdfView;
    @BindView(R.id.iv_rotate)
    ImageView ivRotate;
    LinkHandler linkHandler;
    Bundle bundle;

    private float checkRotation = 1;
    private int page=0;
    private boolean strScreen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pdf);
        ((AppCompatActivity)this).getSupportActionBar().hide();

        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            String strPdf = bundle.getString("check_pdf");
            strScreen = bundle.getBoolean("screen");
            page = bundle.getInt("checkPage");
            showPDF(strPdf);
        }

        ivRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkRotation==1){
                    pdfView.setRotation(90);
                    checkRotation++;
                }
                else if(checkRotation==2){
                    pdfView.setRotation(180);
                    checkRotation++;
                }
                else if(checkRotation==3){
                    pdfView.setRotation(270);
                    checkRotation++;
                }
                else if(checkRotation==4){
                    pdfView.setRotation(360);
                    checkRotation++;
                }
                else if(checkRotation==5){
                    pdfView.setRotation(0);
                    checkRotation=1;
                }


            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strScreen){
                    onBackPressed();
                }
                else {
                    onBackPressed();
                }

            }
        });

    }

    private void showPDF(String pdf) {
        pdfView.fromAsset(pdf)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .enableSwipe(true)
                .defaultPage(page)
                .pageFitPolicy(FitPolicy.BOTH)
                .enableAnnotationRendering(true)
                .password(null)
                .linkHandler(linkHandler = new LinkHandler() {
                    @Override
                    public void handleLinkEvent(LinkTapEvent event) {
                    }
                })
                .scrollHandle(new ScrollHandle() {
                    @Override
                    public void setScroll(float position) {

                    }

                    @Override
                    public void setupLayout(PDFView pdfView) {
                    }

                    @Override
                    public void destroyLayout() {

                    }

                    @Override
                    public void setPageNum(int pageNum) {
                    }

                    @Override
                    public boolean shown() {
                        return false;
                    }

                    @Override
                    public void show() {

                    }

                    @Override
                    public void hide() {

                    }

                    @Override
                    public void hideDelayed() {

                    }
                })
                .enableAntialiasing(false)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {

                    }
                })
                .spacing(0)
                .load();

        pdfView.zoomTo(1);

        pdfView.fitToWidth(1);
    }
}
