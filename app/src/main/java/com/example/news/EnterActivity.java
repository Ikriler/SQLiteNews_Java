package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.biometric.BiometricPrompt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import java.util.concurrent.Executor;

public class EnterActivity extends AppCompatActivity {

    EditText etxtLogin, etxtPassword;
    Button btnLogin, btnReg;
    DataBaseHelper dataBaseHelper;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        executor = ContextCompat.getMainExecutor(this);
        dataBaseHelper = new DataBaseHelper(this);
        etxtLogin = findViewById(R.id.etxtLogin);
        etxtPassword = findViewById(R.id.etxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnReg);

        biometricPrompt = new BiometricPrompt(EnterActivity.this, executor, new BiometricPrompt.AuthenticationCallback(){

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Log.e("ErrorAUTH", errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Intent intent = new Intent();
                if(CurrentUser.getRole().equals("Админ")){
                    intent = new Intent(EnterActivity.this, AdminPanelActivity.class);
                }
                else {
                    intent = new Intent(EnterActivity.this, ReaderActivity.class);
                }
                startActivity(intent);
                Toast.makeText(EnterActivity.this, "Успешно!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.e("FailedAUTH", "Fail!");
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Авторизация")
                .setSubtitle("Приложите палец")
                .setNegativeButtonText("Отмена")
                .build();

        btnLogin.setOnClickListener(view -> {
            Cursor user = dataBaseHelper.getUserByLoginAndPassword(etxtLogin.getText().toString(), etxtPassword.getText().toString());
            if(user.getCount() == 1) {
                user.moveToFirst();
                CurrentUser.authorizeUser(user.getInt(0), user.getString(1), user.getString(2), user.getString(3), user.getString(4));
                biometricPrompt.authenticate(promptInfo);
            }
            else {
                Toast.makeText(this, "Неправильный логин или пароль",Toast.LENGTH_LONG).show();
            }
        });

        btnReg.setOnClickListener(view -> {
            Intent intent = new Intent(EnterActivity.this, RegActivity.class);
            startActivity(intent);
        });
    }
}