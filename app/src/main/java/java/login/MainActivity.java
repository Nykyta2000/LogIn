package java.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;
import java.util.Collection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CallbackManager callbackManager;
    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button FacebookBtn = (Button) findViewById(R.id.buttonF);
        Button GoogleBtn = (Button) findViewById(R.id.buttonG);

        GoogleBtn.setOnClickListener(this);
        FacebookBtn.setOnClickListener(this);

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        loginManager = LoginManager.getInstance();

        loginManager.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Toast.makeText(getApplicationContext(), loginResult.getAccessToken().toString(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        Collection<String> permissions = Arrays.asList("public_profile", "user_friends");
        loginManager.logInWithReadPermissions(this, permissions);

    }

    @Override
    public void onClick(View view) {
        LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile"));
    }
}
