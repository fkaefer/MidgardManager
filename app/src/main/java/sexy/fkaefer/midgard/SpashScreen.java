package sexy.fkaefer.midgard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.sql.Time;

public class SpashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);
        if (Application.mainRef.getAuth() == null){
            startActivity(new Intent(SpashScreen.this, LoginActivity.class));
            finish();
            return;
        }
        if(Application.mainRef.getAuth().getExpires() < System.currentTimeMillis()/1000){
            Intent i = new Intent(SpashScreen.this, MainActivity.class);
            startActivity(i);
            finish();
        } else {
            startActivity(new Intent(SpashScreen.this, LoginActivity.class));
            finish();
        }
    }

}
