package com.example.pagview;

import android.app.Activity;
import android.os.Bundle;

import org.libpag.PAGFile;
import org.libpag.PAGText;
import org.libpag.PAGView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PAGView pagView = findViewById(R.id.pagView);
        PAGFile pagFile = PAGFile.Load(getAssets(), "check_in.pag");
        updateCheckInDate(pagFile);

        pagView.setComposition(pagFile);
        pagView.play();

        findViewById(R.id.checkIn).setOnClickListener(v -> {
            pagView.setProgress(0);
            updateCheckInDate(pagFile);
            pagView.play();
        });
    }

    private void updateCheckInDate(PAGFile pagFile) {
        PAGText textData2 = pagFile.getTextData(2);
        textData2.text = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date());
        pagFile.replaceText(2, textData2);
    }
}