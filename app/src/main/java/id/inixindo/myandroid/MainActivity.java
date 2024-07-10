package id.inixindo.myandroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // 1. declare variables
    private Toolbar toolbar;
    private Button activityButton;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Fragment fragmentHome;
    private Fragment fragmentCapture;
    private Fragment fragmentGallery;
    private Fragment fragmentShare;
    private Fragment fragmentSend;
    private Fragment fragmentBookmark;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_CAPTURE = 1;
    private static final int FRAGMENT_GALLERY = 2;
    private static final int FRAGMENT_SHARE = 3;
    private static final int FRAGMENT_SEND = 4;
    private static final int FRAGMENT_BOOKMARK = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. identify all widgets
        toolbar = findViewById(R.id.activity_main_toolbar);
        activityButton = findViewById(R.id.activityButton);
        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.activity_navigation_view);

        // 3. event handling widgets
        toolbar.setTitle("Inixindo Android");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Learn android with Inixindo");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.icon_menu));
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.icon_more));
        setSupportActionBar(toolbar);

        // event handling untuk button
        activityButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(getApplicationContext(), DisplayActivity.class);
            startActivity(myIntent);
        });

        // event handling untuk drawer layout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, (R.string.open), (R.string.close)
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        toggle.syncState();

        // event handling untuk navigation view
        navigationView.setNavigationItemSelectedListener(this);

        // menampilkan fragment pertama
        showFirstFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy Activity", Toast.LENGTH_SHORT).show();
    }

    // menyematkan options menu kedalam toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    // memilih salah satu dari options menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.menu_search) {
            startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            return true;
        } else if (menuId == R.id.menu_notification) {
            startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
            return true;
        } else if (menuId == R.id.menu_person) {
            startActivity(new Intent(getApplicationContext(), UsersActivity.class));
            return true;
        } else if (menuId == R.id.menu_message) {
            startActivity(new Intent(getApplicationContext(), MessagesActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_frame_layout, fragment).commit();
        }
    }

    private void showHomeFragment() {
        fragmentHome = HomeFragment.newInstance();
        startTransactionFragment(fragmentHome);
    }

    private void showCaptureFragment() {
        fragmentCapture = CaptureFragment.newInstance();
        startTransactionFragment(fragmentCapture);
    }

    private void showGalleryFragment() {
        fragmentGallery = GalleryFragment.newInstance();
        startTransactionFragment(fragmentGallery);
    }

    private void showShareFragment() {
        fragmentShare = ShareFragment.newInstance();
        startTransactionFragment(fragmentShare);
    }

    private void showSendFragment() {
        fragmentSend = SendFragment.newInstance();
        startTransactionFragment(fragmentSend);
    }

    private void showBookmarkFragment() {
        fragmentBookmark = BookmarkFragment.newInstance();
        startTransactionFragment(fragmentBookmark);
    }

    private void selectFragment(int fragmentIndex) {
        switch (fragmentIndex) {
            case FRAGMENT_HOME:
                showHomeFragment();
                toolbar.setTitle("HOME FRAGMENT");
                break;
            case FRAGMENT_CAPTURE:
                showCaptureFragment();
                toolbar.setTitle("CAPTURE FRAGMENT");
                break;
            case FRAGMENT_GALLERY:
                showGalleryFragment();
                toolbar.setTitle("GALLERY FRAGMENT");
                break;
            case FRAGMENT_SHARE:
                showShareFragment();
                toolbar.setTitle("SHARE FRAGMENT");
                break;
            case FRAGMENT_SEND:
                showSendFragment();
                toolbar.setTitle("SEND FRAGMENT");
                break;
            case FRAGMENT_BOOKMARK:
                showBookmarkFragment();
                toolbar.setTitle("BOOKMARK FRAGMENT");
                break;
            default:
                break;
        }
    }

    private void showFirstFragment() {
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_frame_layout);
        if (visibleFragment == null) {
            selectFragment(FRAGMENT_HOME);
            navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

//        switch (menuId) {
//            case R.id.drawer_home:
//                selectFragment(FRAGMENT_HOME);
//                break;
//            case R.id.drawer_capture:
//                selectFragment(FRAGMENT_CAPTURE);
//                break;
//            case R.id.drawer_gallery:
//                selectFragment(FRAGMENT_GALLERY);
//                break;
//            case R.id.drawer_share:
//                selectFragment(FRAGMENT_SHARE);
//                break;
//            case R.id.drawer_send:
//                selectFragment(FRAGMENT_SEND);
//                break;
//            case R.id.drawer_bookmark:
//                selectFragment(FRAGMENT_BOOKMARK);
//                break;
//        }

        if (menuId == R.id.drawer_home) {
            selectFragment(FRAGMENT_HOME);
        } else if (menuId == R.id.drawer_capture) {
            selectFragment(FRAGMENT_CAPTURE);
        } else if (menuId == R.id.drawer_gallery) {
            selectFragment(FRAGMENT_GALLERY);
        } else if (menuId == R.id.drawer_share) {
            selectFragment(FRAGMENT_SHARE);
        } else if (menuId == R.id.drawer_send) {
            selectFragment(FRAGMENT_SEND);
        } else if (menuId == R.id.drawer_bookmark) {
            selectFragment(FRAGMENT_BOOKMARK);
        } else {
            throw new Error("Menu not found");
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}