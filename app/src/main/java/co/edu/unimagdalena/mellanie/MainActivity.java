package co.edu.unimagdalena.mellanie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
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

        if (estaLoguedo()){
            Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);

            startActivity(intent);
        }

        loginButton = (LoginButton) findViewById(R.id.login_button);
        titulolbl = (TextView) findViewById(R.id.txtLoginPage);


        callbackManager = CallbackManager.Factory.create(); //inicialisacion del callback

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);

                startActivity(intent);
                //titulolbl.setText(loginResult.getAccessToken().getUserId()); //cambia el texto por la id del ususario
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Cancelaste el Login", Toast.LENGTH_LONG);
                //titulolbl.setText("User Cancel reques");

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Hubo un error al loguarte en la aplicacion", Toast.LENGTH_LONG);
                //titulolbl.setText("Facebok Error");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private boolean estaLoguedo(){
        return AccessToken.getCurrentAccessToken() != null;
    }


}
