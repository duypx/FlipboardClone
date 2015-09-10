package clone.flipboard.flipboardclone.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import clone.flipboard.flipboardclone.R;

public class StartActivity extends Activity {
//    private ImageView mImgGeneral, mImgYouth, mImgConomy, mImgPolitics, mImgEducation, mImgScience, mImgHearth, mImgEntertaiment;
//    private Animation slide, clockwise, move, fade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_start2);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        init();
//        mImgGeneral.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(StartActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        mImgGeneral.startAnimation(slide);
//        mImgScience.startAnimation(slide);
//        mImgHearth.startAnimation(slide);
//        mImgPolitics.startAnimation(slide);
//        mImgYouth.startAnimation(slide);
//        mImgConomy.startAnimation(slide);
//        mImgEducation.startAnimation(slide);
//        mImgEntertaiment.startAnimation(slide);

    }

//    public void init() {
//        mImgGeneral = (ImageView) findViewById(R.id.img_general);
//        mImgConomy = (ImageView) findViewById(R.id.img_conomy);
//        mImgYouth = (ImageView) findViewById(R.id.img_youth);
//        mImgEducation = (ImageView) findViewById(R.id.img_education);
//        mImgEntertaiment = (ImageView) findViewById(R.id.img_entertainment);
//        mImgHearth = (ImageView) findViewById(R.id.img_health);
//        mImgPolitics = (ImageView) findViewById(R.id.img_politics);
//        mImgScience = (ImageView) findViewById(R.id.img_science);
//
//        slide = new ScaleAnimation((float) 1, (float) 1, (float) 0, (float) 1,
//                Animation.RELATIVE_TO_SELF, (float) 0,
//                Animation.RELATIVE_TO_SELF, (float) 1);
//        slide.setDuration(1000);
//        slide.setFillAfter(true);
//        slide.setFillEnabled(true);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
