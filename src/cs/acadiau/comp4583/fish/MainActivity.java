package cs.acadiau.comp4583.fish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        ((Button) super.findViewById(R.id.fishButton)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent fishIntent = new Intent(getApplicationContext(), FishActivity.class);
                startActivity(fishIntent);
            }
        });
    }
}