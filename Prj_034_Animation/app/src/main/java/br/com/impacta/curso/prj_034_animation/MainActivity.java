package br.com.impacta.curso.prj_034_animation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    private Context context;

    private Button btn_alpha;
    private Button btn_girar;

    private ImageView iv_foto;

    private Button btn_escala;
    private Button btn_tudo;
    private Button btn_trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        //
        btn_alpha = (Button) findViewById(R.id.btn_alpha);
        btn_girar = (Button) findViewById(R.id.btn_girar);

        iv_foto = (ImageView) findViewById(R.id.iv_foto);

        btn_escala = (Button) findViewById(R.id.btn_escala);
        btn_tudo = (Button) findViewById(R.id.btn_tudo);
        btn_trans = (Button) findViewById(R.id.btn_trans);
    }

    private void initActions() {
        btn_alpha.setOnClickListener(btnAnimation);
        btn_girar.setOnClickListener(btnAnimation);
        btn_escala.setOnClickListener(btnAnimation);
        btn_tudo.setOnClickListener(btnAnimation);
        btn_trans.setOnClickListener(btnAnimation);
    }

    private View.OnClickListener btnAnimation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_alpha:
                    ativarAnimation(context, iv_foto, R.anim.alpha);
                    break;
                case R.id.btn_girar:
                    ativarAnimation(context, iv_foto, R.anim.girar);
                    break;

                case R.id.btn_escala:
                    ativarAnimation(context, iv_foto, R.anim.escala);
                    break;

                case R.id.btn_tudo:
                    ativarAnimation(context, iv_foto, R.anim.tudo);
                    break;

                case R.id.btn_trans:
                    ativarAnimation(context, iv_foto, R.anim.trans);
                    break;

                default:
                    break;
            }
        }
    };

    private void ativarAnimation(Context context, View view, int efeito) {
        Animation mAnim = AnimationUtils.
                loadAnimation(
                        context,
                        efeito
                );
        view.requestLayout();
        view.setAnimation(mAnim);
        mAnim.start();
    }

    private void habilitarDesabilitarBotoes(boolean status) {
        btn_alpha.setEnabled(status);
        btn_girar.setEnabled(status);
        btn_escala.setEnabled(status);
        btn_tudo.setEnabled(status);
        btn_trans.setEnabled(status);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        habilitarDesabilitarBotoes(false);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        habilitarDesabilitarBotoes(true);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
