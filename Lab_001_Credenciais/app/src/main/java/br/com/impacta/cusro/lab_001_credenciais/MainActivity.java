package br.com.impacta.cusro.lab_001_credenciais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText _et_password;
    private EditText _et_name;
    private Button _btn_cancel;
    private Button _btn_login;
    private ToolBox _toolBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);
        initVars();
        initActions();

    }

    private void initVars() {
        _et_name = (EditText) findViewById(R.id.et_name);
        _et_password = (EditText) findViewById(R.id.et_password);
        _btn_cancel = (Button) findViewById(R.id.btn_cancel);
        _btn_login = (Button) findViewById(R.id.btn_login);

        _toolBox = new ToolBox(getBaseContext());
    }


    private void initActions() {
        _btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearForm();
            }
        });

        _btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate();
            }
        });
    }

    private void authenticate() {
        User user = new User(_et_name.getText().toString(),
                _et_password.getText().toString());

        ValidationContract validations = configureValidations(user);

        if (!validations.isValid()) {
            _toolBox.showShortToast(validations.getErrors());
        } else {

            AuthService service = new AuthService();
            if (service.Auth(user)) {
                clearForm();
                _toolBox.showShortToast(getString(R.string.login_success_message));
            } else {
                _toolBox.showShortToast(getString(R.string.error_message_access_denied));
            }
        }
    }

    private ValidationContract configureValidations(User user) {
        ValidationContract validations = new ValidationContract();
        validations.isRequired(user.get_name(), getString(R.string.error_message_required_name));
        validations.hasMaxLength(user.get_name(), 10, getString(R.string.error_message_name_max_length));
        validations.hasMinLength(user.get_name(), 3, getString(R.string.error_message_min_length));
        validations.isRequired(user.get_password(), getString(R.string.error_message_required_password));
        return validations;
    }

    private void clearForm() {
        _et_name.setText("");
        _et_password.setText("");
        _et_name.requestFocus();
    }
}
