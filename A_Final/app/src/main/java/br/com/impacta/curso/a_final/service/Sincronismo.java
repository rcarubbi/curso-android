package br.com.impacta.curso.a_final.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import br.com.impacta.curso.a_final.R;
import br.com.impacta.curso.a_final.banco.Constantes;
import br.com.impacta.curso.a_final.dao.PacienteDao;
import br.com.impacta.curso.a_final.model.Trans_Env;
import br.com.impacta.curso.a_final.model.Trans_Rec;
import br.com.impacta.curso.mlib.ToolBox;

/**
 * Created by nalmir on 12/05/2018.
 */

public class Sincronismo extends IntentService {

    private String mensagem;

    public Sincronismo() {
        super("Sincronismo");
    }

    // Nesse metodo all o processamento sera realizado em paralelo
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try {

            Gson gson = new Gson();

            Trans_Env env = new Trans_Env();
            env.setUser("Hugo");
            env.setPwd(ToolBox.md5("teste"));
            env.setPacientes(100);

            String resposta = ToolBox.cominicacao(
                    Constantes.WSPACIENTES,
                    gson.toJson(env)
            );

            String [] parts = resposta.split("#WSTAG#");

            switch (parts.length){
                case 1:
                    mensagem = "Erro: " + parts[0];
                    break;
                default:

                    if (parts[0].equals("0")){
                        Trans_Rec rec = gson.fromJson(
                                parts[1],
                                Trans_Rec.class
                        );

                        PacienteDao pacienteDao = new PacienteDao(getApplicationContext());
                        pacienteDao.inserirListaPaicentes(rec.getPacientes());

                        enviarBroadcast();

                        mensagem = "Sincronismo realizado com Sucesso";

                    } else {
                        mensagem = "Erro: " + parts[1];
                    }
                    break;
            }

        } catch (Exception e) {
            mensagem = "Erro: " + e.toString();
        }

        notificacao();
    }

    // nao é necessario pedir permissao para enviar um broadcast
    private void enviarBroadcast() {
        Intent mIntent = new Intent(); // mensagem s.o.
        mIntent.setAction(Constantes.EVENTO);
        mIntent.addCategory(Intent.CATEGORY_DEFAULT);
        //
        sendBroadcast(mIntent);
    }

    private void notificacao() {
        NotificationManager nm = (NotificationManager)
                getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        // É a minha notificacao
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setContentTitle("Sincronismo");
        builder.setContentText(mensagem);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setAutoCancel(true);
        builder.setDefaults(
                Notification.DEFAULT_SOUND |
                        Notification.DEFAULT_VIBRATE);
        //
        // devolve a versao do s.o. android rodando no equipamento
        int versao = Build.VERSION.SDK_INT;
        //
        if (versao >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            nm.notify(10, builder.build());
        } else {
            nm.notify(10, builder.getNotification());
        }
    }
}
