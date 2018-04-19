package br.com.impacta.curso.prj_027_cdt;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nalmir on 14/04/2018.
 */

public class CDT extends android.support.v7.widget.AppCompatEditText implements View.OnClickListener {

    private Context context;

    private SimpleDateFormat dateFormat;

    public CDT(Context context) {
        super(context);

        inicializar(context);
    }

    public CDT(Context context, AttributeSet attrs) {
        super(context, attrs);

        inicializar(context);
    }

    public CDT(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inicializar(context);
    }

    private void inicializar(Context context) {
        this.context = context;
        //
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //
        // abortar o teclado
        setInputType(InputType.TYPE_NULL);
        //
        // Reprogramar o click na area retangular
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Obter a data informada no controle
        String conteudo = getText().toString();

        // Obter a data de hoje
        Calendar mDate = Calendar.getInstance();
        //
        try {
            mDate.setTime(dateFormat.parse(conteudo));

        } catch (Exception e) {
        }
        // ou a data é a atual ou é a data informada pelo usuário
        // separar os componentes da data
        int mAno = mDate.get(Calendar.YEAR);
        int mMes = mDate.get(Calendar.MONTH);
        int mDia = mDate.get(Calendar.DAY_OF_MONTH);
        //
        DatePickerDialog mDatePicker = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calNew = Calendar.getInstance();
                        calNew.set(year, month, dayOfMonth);
                        //
                        setText(dateFormat.format(calNew.getTime()));
                    }
                },
                mAno,
                mMes,
                mDia
        );
        //
        mDatePicker.setButton(
                DatePickerDialog.BUTTON_POSITIVE,
                "Ok",
                mDatePicker
        );
        //
        mDatePicker.setButton(
                DatePickerDialog.BUTTON_NEGATIVE,
                "Cancelar",
                (DialogInterface.OnClickListener) null
        );
        //
        mDatePicker.show();
    }
}
