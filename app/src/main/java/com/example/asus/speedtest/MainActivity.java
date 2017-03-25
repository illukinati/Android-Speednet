package com.example.asus.speedtest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asus.speedtest.speedtest.SpeedTestReport;
import com.example.asus.speedtest.speedtest.SpeedTestSocket;
import com.example.asus.speedtest.speedtest.inter.ISpeedTestListener;
import com.example.asus.speedtest.speedtest.model.SpeedTestError;

import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity {

    boolean status = false;
    TextView tv_download_status;
    ProgressBar pg_wait;

    double download_result;
    DownloadStatus executor = new DownloadStatus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        executor.execute();

        tv_download_status = (TextView) findViewById(R.id.tv_download_status);
        pg_wait = (ProgressBar) findViewById(R.id.pg_wait);

        tv_download_status.setVisibility(View.GONE);
    }

    class DownloadStatus extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            SpeedTestSocket speedTestSocket = new SpeedTestSocket();

            // add a listener to wait for speedtest completion and progress
            speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {

                @Override
                public void onDownloadFinished(SpeedTestReport report) {
                    // called when download is finished
                    Log.v("speedtest", "[DL FINISHED] rate in octet/s : " + report.getTransferRateOctet());
                    Log.v("speedtest", "[DL FINISHED] rate in bit/s   : " + report.getTransferRateBit());
                    download_result = report.getTransferRateBit().doubleValue() / 1048576;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pg_wait.setVisibility(View.GONE);
                            tv_download_status.setText("Download Speed : " + download_result);
                            tv_download_status.setVisibility(View.VISIBLE);
                        }
                    });
                }

                @Override
                public void onDownloadError(SpeedTestError speedTestError, String errorMessage) {
                    // called when a download error occur
                }

                @Override
                public void onUploadFinished(SpeedTestReport report) {
                    // called when an upload is finished
                    Log.v("speedtest", "[UL FINISHED] rate in octet/s : " + report.getTransferRateOctet());
                    Log.v("speedtest", "[UL FINISHED] rate in bit/s   : " + report.getTransferRateBit());
                }

                @Override
                public void onUploadError(SpeedTestError speedTestError, String errorMessage) {
                    // called when an upload error occur
                }

                @Override
                public void onDownloadProgress(float percent, SpeedTestReport report) {
                    // called to notify download progress
                    Log.v("speedtest", "[DL PROGRESS] progress : " + percent + "%");
                    Log.v("speedtest", "[DL PROGRESS] rate in octet/s : " + report.getTransferRateOctet());
                    Log.v("speedtest", "[DL PROGRESS] rate in bit/s   : " + report.getTransferRateBit());
                }

                @Override
                public void onUploadProgress(float percent, SpeedTestReport report) {
                    // called to notify upload progress
                    Log.v("speedtest", "[UL PROGRESS] progress : " + percent + "%");
                    Log.v("speedtest", "[UL PROGRESS] rate in octet/s : " + report.getTransferRateOctet());
                    Log.v("speedtest", "[UL PROGRESS] rate in bit/s   : " + report.getTransferRateBit());
                }

                @Override
                public void onInterruption() {
                    // triggered when forceStopTask is called
                }
            });

            speedTestSocket.startDownload("2.testdebit.info", "/fichiers/1Mo.dat");

            return null;
        }
    }
}
