package br.com.impacta.curso.prj_020_notificacao;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private Button btn_fn;
    private Button btn_cn;

    private int idNotificacao = 10;

    private NotificationManager nm;

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
        nm = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);
        //
        btn_fn = (Button) findViewById(R.id.btn_fn);
        btn_cn = (Button) findViewById(R.id.btn_cn);
    }

    private void initActions() {
        btn_fn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, MainActivity.class);
                //
                // Retira esperando ativacao feita pelo usuario
                PendingIntent pi = PendingIntent.getActivity(
                        context,
                        0,
                        mIntent,
                        0
                );
                //
                // É a minha notificacao
                Notification.Builder builder = new Notification.Builder(context);
                builder.setContentTitle("Sincronismo");
                builder.setContentText("Aguarde o Sincronismo...");
                builder.setSmallIcon(R.mipmap.ic_launcher_round);
                builder.setAutoCancel(true);
                builder.setContentIntent(pi);
                builder.setDefaults(
                        Notification.DEFAULT_SOUND |
                                Notification.DEFAULT_VIBRATE);
                //
                // devolve a versao do s.o. android rodando no equipamento
                int versao = Build.VERSION.SDK_INT;
                //
                if (versao >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                    nm.notify(idNotificacao, builder.build());
                } else {
                    nm.notify(idNotificacao, builder.getNotification());
                }
            }
        });

        btn_cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nm.cancel(idNotificacao);
            }
        });
    }

}
