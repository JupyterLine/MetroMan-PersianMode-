package ir.anjoman.zeroone.khoshtip;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class SinglePlayerActivity extends AppCompatActivity {

    final int[] clickCount = {0};
    final long[] lastClickTime = {0};
    VideoView videoView;
    Button btnPlay;
    Uri videoUri;
    PrefManager pref;
    int score,clicker;
    TextView txtt;
    Handler handler = new Handler();
    long chunkTime = 1000;

    int currentPosition = 0;

    final Runnable noClickRunnable = new Runnable() {
        @Override
        public void run() {
            videoView.pause();
            clickCount[0] = 0;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        pref = new PrefManager(this);
        videoView = findViewById(R.id.videoView);
        btnPlay = findViewById(R.id.btnPlay);
        txtt = findViewById(R.id.textt);
        if (pref.getSave().toString().equals("khoshtip")) {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.khoshtip);
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.s_khoshtip));
        }
        if (pref.getSave().toString().equals("doshman")) {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.doshman);
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.s_doshman));
        }
        if (pref.getSave().toString().equals("bist6")) {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bist6);
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.s_bist6));
        }
        if (pref.getSave().toString().equals("danosh")) {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.danosh);
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.s_danosh));
        }
        if (pref.getSave().toString().equals("golzar")) {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.golzar);
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.s_golzar));
        }
        if (pref.getSave().toString().equals("moshali")) {
            videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mosh);
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.s_doshman));
        }
        videoView.start();

        videoView.setOnCompletionListener(mp -> {
            if (!btnPlay.isEnabled()) btnPlay.setEnabled(true);
        });

        btnPlay.setOnClickListener(v -> {
            long now = System.currentTimeMillis();
            if (now - lastClickTime[0] < 500) {
                clicker++;
                clickCount[0]++;
            } else {
                clickCount[0] = 1;
            }
            lastClickTime[0] = now;

            if (clickCount[0] >= 3) {
                videoView.start();
            }
            if (clicker==3){
                clicker=0;
                score++;
                txtt.setText((("SCORE :"+score).toString()));
                int a = Integer.parseInt(pref.getStart());
                if (score>a) pref.start(String.valueOf(score));
            }
            handler.removeCallbacks(noClickRunnable);
            handler.postDelayed(noClickRunnable, 500);

            if (!videoView.isPlaying()) playNextChunk();
        });
    }

    private void playNextChunk() {

        videoView.stopPlayback();
        videoView.setVideoURI(videoUri);
        videoView.setOnPreparedListener(mp -> {

            if (currentPosition >= mp.getDuration()) {
                currentPosition = 0;
            }

            videoView.seekTo(currentPosition);
            videoView.start();
            handler.postDelayed(() -> {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    currentPosition += chunkTime;
                }
            }, chunkTime);

        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
