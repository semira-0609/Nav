package com.example.nav;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomAppBar bottomAppBar;
    private SearchView searchView;
    private ImageButton searchButton;

    // Declare the Floating Action Buttons
    private FloatingActionButton fabOption1, fabOption2, fabMain;
    private boolean isFabOpen = false;  // To track the visibility state of the child FABs

    // Declare ImageView references
    private ImageView car1, car2, car3, car4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(bottomAppBar);

        // SearchView and Search Button
        searchView = findViewById(R.id.search_view);
        searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchView.getVisibility() == View.GONE) {
                    searchView.setVisibility(View.VISIBLE);
                    searchButton.setVisibility(View.GONE);  // Hide search button when SearchView is visible
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    searchView.setVisibility(View.GONE);
                    searchButton.setVisibility(View.VISIBLE);  // Show search button again
                }
                return false;
            }
        });

        bottomAppBar.setOnMenuItemClickListener(new BottomAppBar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_menu) {
                    showPopupMenu(bottomAppBar);
                    return true;
                }
                return false;
            }
        });

        // Handle profile button click
        ImageButton profileButton = findViewById(R.id.ic_action_name); // Bind ImageButton
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch ProfileActivity when the button is clicked
                Intent intent = new Intent(MainActivity.this, com.example.nav.ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Initialize Floating Action Buttons
        fabMain = findViewById(R.id.fab_main);  // Correct ID for the main FAB
        fabOption1 = findViewById(R.id.fab_option_1);  // First action FAB
        fabOption2 = findViewById(R.id.fab_option_2);  // Second action FAB

        // Initially hide the child FABs
        fabOption1.setVisibility(View.GONE);
        fabOption2.setVisibility(View.GONE);

        // Set Click Listener for Main FAB
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFABMenu();  // Show/Hide the child FABs
            }
        });

        // Set Click Listeners for child FABs
        fabOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for FAB Option 1 (Share)
                shareAction();
            }
        });

        fabOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for FAB Option 2 (Camera)
                cameraAction();
            }
        });

        // Initialize ImageViews and initially hide them
        car1 = findViewById(R.id.car1);
        car2 = findViewById(R.id.car2);
        car3 = findViewById(R.id.car3);
        car4 = findViewById(R.id.car4);

        // Assuming you want to show the ImageViews when a button is clicked (e.g., fabMain)
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFABMenu();  // Toggle FABs

                // Show the car images
                car1.setVisibility(View.VISIBLE);
                car2.setVisibility(View.VISIBLE);
                car3.setVisibility(View.VISIBLE);
                car4.setVisibility(View.VISIBLE);
            }
        });
    }

    // Toggle the visibility of the child FABs
    private void toggleFABMenu() {
        if (isFabOpen) {
            fabOption1.setVisibility(View.GONE);
            fabOption2.setVisibility(View.GONE);
            isFabOpen = false;
        } else {
            fabOption1.setVisibility(View.VISIBLE);
            fabOption2.setVisibility(View.VISIBLE);
            isFabOpen = true;
        }
    }

    // Function to handle share action
    private void shareAction() {
        // Implement your sharing logic here
    }

    // Function to handle camera action
    private void cameraAction() {
        // Implement your camera opening logic here
    }

    private void showPopupMenu(View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubottom_up_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
