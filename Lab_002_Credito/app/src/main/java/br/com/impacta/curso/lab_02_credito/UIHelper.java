package br.com.impacta.cusro.lab_02_credito;

/**
 * Created by Raphael on 17/02/2018.
 */


import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Raphael on 03/02/2018.
 */

public class UIHelper {
    private Context _context;

    public UIHelper(Context context) {
        _context = context;
    }

    public void showShortToast(String message) {
        Toast.makeText(_context, message, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String message) {
        Toast.makeText(_context, message, Toast.LENGTH_LONG).show();
    }

    public void addHighlight(LinearLayout layout) {
        layout.setBackground(_context.getDrawable(R.drawable.borda));
    }


    public void removeHightlight(LinearLayout layout) {
        layout.setBackground(null);
    }
}
