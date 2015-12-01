package co.edu.unimagdalena.mellanie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView titulolbl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext()); //inicializando el sdk de facebook
        setContentView(R.layout.activity_main);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        titulolbl = (TextView) findViewById(R.id.txtLoginPage);


        callbackManager = CallbackManager.Factory.create(); //inicialisacion del callback

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                titulolbl.setText(loginResult.getAccessToken().getUserId()); //cambia el texto por la id del ususario
            }

            @Override
            public void onCancel() {
                titulolbl.setText("User Cancel reques");

            }

            @Override
            public void onError(FacebookException error) {
                titulolbl.setText("Facebok Error");
            }
        });


    }

}
