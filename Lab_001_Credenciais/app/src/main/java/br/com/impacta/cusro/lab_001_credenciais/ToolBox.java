package br.com.impacta.cusro.lab_001_credenciais;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Raphael on 03/02/2018.
 */

public class ToolBox {
    private Context _context;

    public ToolBox(Context context) {
        _context = context;
    }

    public void showShortToast(String message) {
        Toast.makeText(_context, message, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String message) {
        Toast.makeText(_context, message, Toast.LENGTH_LONG).show();
    }
}
