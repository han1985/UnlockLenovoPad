package com.unlock.unlock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetRoot();

        Button unLockTouch = findViewById(R.id.button);
        unLockTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // settings put system block_untrusted_touches 0
                // settings put global block_untrusted_touches 0
                // settings put secure block_untrusted_touches 0


                CommandExecution.execCommand("settings put system block_untrusted_touches 0",true);

                CommandExecution.execCommand("settings put global block_untrusted_touches 0",true);

                CommandExecution.execCommand("settings put secure block_untrusted_touches 0",true);

                CommandExecution.execCommand("reboot",true);
            }
        });
    }

    void GetRoot()
    {
        try {
            CommandExecution.COMMAND_SU = "ppap";
            Runtime.getRuntime().exec(CommandExecution.COMMAND_SU + " -c setenforce 0");
        }
        catch (IOException e)
        {
            e.printStackTrace();

            CommandExecution.COMMAND_SU = "su";

            try {
                Runtime.getRuntime().exec(CommandExecution.COMMAND_SU + " -c setenforce 0");
            }
            catch (IOException e2)
            {

            }
        }
    }
}