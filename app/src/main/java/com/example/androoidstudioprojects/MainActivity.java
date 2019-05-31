package com.example.androoidstudioprojects;
//Android Toolbar Application
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.service.media.MediaBrowserService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    CheckBox checkBox;

    ActionMode actionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = findViewById(R.id.mycheckBox);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Home Page");
        toolbar.setSubtitle("Welcome User..!");

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    actionMode = MainActivity.this.startSupportActionMode(new ActionBarCallback());

                }else{

                    actionMode.finish();

                }

            }
        });

    }

    private class ActionBarCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {


            actionMode.getMenuInflater().inflate(R.menu.cotextual_menu, menu);


            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {


            actionMode.setTitle("My Action Mode");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();

            // You can add Functionality to your Menu Buttons here.
            // Apply switch case statements in case there are more than one Menu Buttons.

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

            // This is called when Action Mode is completed.

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R. menu.menu,menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
int id=item.getItemId();
switch(id){
    case R.id.action_settings:
        startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
        Toast.makeText(MainActivity.this,"Settings clicked",Toast.LENGTH_LONG).show();
        break;
    case R.id.action_camera:
        startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        Toast.makeText(MainActivity.this,"Camera clicked",Toast.LENGTH_LONG).show();
        break;
    case R.id.action_logout:
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startActivity(new Intent(getApplicationContext(),HomeSp.class));
        }
        Toast.makeText(MainActivity.this,"Logout clicked",Toast.LENGTH_LONG).show();
        break;

}
        return super.onOptionsItemSelected(item);

    }
}
