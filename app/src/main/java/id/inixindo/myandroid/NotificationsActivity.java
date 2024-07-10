package id.inixindo.myandroid;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NotificationsActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        toolbar = findViewById(R.id.activity_notifications_toolbar);
        bottomNavigationView = findViewById(R.id.bottomNav);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.icon_back));

        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.bottomnav_home);    // fragment pertama kali dibuka
    }

    HomeFragment homeFragment = new HomeFragment();
    ShareFragment shareFragment = new ShareFragment();
    GalleryFragment galleryFragment = new GalleryFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottomnav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_frame_notification, homeFragment).commit();
                return true;
            case R.id.bottomnav_share:
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_frame_notification, shareFragment).commit();
                return true;
            case R.id.bottomnav_gallery:
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_frame_notification, galleryFragment).commit();
                return true;
        }
        return false;
    }
}