package clone.flipboard.flipboardclone.screen;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


import clone.flipboard.flipboardclone.R;

import static clone.flipboard.flipboardclone.R.id.fragment_container;

public class MainActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new RssFragment()).commit();
        }

    }

//    private void addRssFragment() {
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        RssFragment fragment = new RssFragment();
//        transaction.add(R.id.fragment_container, fragment);
//        transaction.commit();
//    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putBoolean("fragment_added", true);
//    }

}