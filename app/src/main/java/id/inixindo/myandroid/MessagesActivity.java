package id.inixindo.myandroid;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.regex.Pattern;

import id.inixindo.myandroid.databinding.ActivityMessagesBinding;

public class MessagesActivity extends AppCompatActivity {
    private ActivityMessagesBinding binding;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = binding.activityMessagesToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.icon_back));

        emailFocusListener();
        phoneFocusListener();
        passwordFocusListener();

        binding.sendButton.setOnClickListener(v -> submitForm());
    }

    private void submitForm() {
        String message = "Email: " + binding.emailEditText.getText() + "\nPhone: " + binding.phoneEditText.getText() + "\nPassword: " + binding.passwordEditText.getText();

        new MaterialAlertDialogBuilder(this)
                .setTitle("Submit Form")
                .setMessage(message)
                .setNeutralButton("OK", null)
                .setIcon(R.drawable.icon_info)
                .setCancelable(false)
                .show();
    }

    private String validateEmail() {
        String emailText = binding.emailEditText.getText().toString();
        if (emailText.isEmpty()) {
            return "Email address cannot be empty";
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Email address invalid";
        }
        return null;
    }

    private String validatePhone() {
        String phoneText = binding.phoneEditText.getText().toString();
        if (phoneText.isEmpty()) {
            return "Phone number cannot be empty";
        } else if (phoneText.length() < 12) {
            return "Phone number must be 12 digits";
        } else if (!phoneText.matches(".*[0-9].*")) {
            return "Phone number must contain only numbers";
        }
        return null;
    }

    private String validatePassword() {
        String passwordText = binding.passwordEditText.getText().toString();
        if (passwordText.length() < 8) {
            return "Password length minimum 8 characters";
        }
        if (passwordText.length() > 16) {
            return "Password length maximum 16 characters";
        }
        if (!passwordText.matches(".*[0-9].*")) {
            return "Password must contain at least one number";
        }
        if (!passwordText.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter";
        }
        if (!passwordText.matches(".*[a-z].*")) {
            return "Password must contain at least one lowercase letter";
        }
        if (!passwordText.matches(".*[!@#$%^&*+=_].*")) {
            return "Password must contain at least one special letter";
        }
        return null;
    }

    private void emailFocusListener() {
        binding.emailEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                binding.emailContainer.setHelperText(validateEmail());
            }
        });
    }

    private void phoneFocusListener() {
        binding.phoneEditText.setOnFocusChangeListener((v, isFocus) -> {
            if (!isFocus) {
                binding.phoneContainer.setHelperText(validatePhone());
            }
        });
    }

    private void passwordFocusListener() {
        binding.passwordEditText.setOnFocusChangeListener((v, isFocus) -> {
            if (!isFocus) {
                binding.passwordContainer.setHelperText(validatePassword());
            }
        });
    }
}