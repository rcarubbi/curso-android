package br.nm.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by nalmir on 14/04/2018.
 */

public class ToolBox {

    public static String fazerSaudacao(String nome){
        return "Seja bem vindo " + nome;
    }

    public static String cominicacao(String urlEnd, String params) {
        StringBuilder sb = new StringBuilder();
        //
        URL url;
        HttpURLConnection conn = null;
        //
        try {
            url = new URL(urlEnd);
            conn = (HttpURLConnection) url.openConnection();
            //
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //
            //conn.setConnectTimeout(60000);
            //conn.setReadTimeout(60000);
            //
            StringBuilder parametrosFormatados = new StringBuilder();
            parametrosFormatados.append(URLEncoder.encode("json", "UTF-8"));
            parametrosFormatados.append("=");
            parametrosFormatados.append(URLEncoder.encode(params, "UTF-8"));
            //
            // Enviar os parametros para o servidor
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            os,
                            "UTF-8"
                    )
            );
            writer.write(parametrosFormatados.toString());
            writer.flush();
            writer.close();
            //
            // Ler os dados enviados pelo servidor
            sb.append(readStream(conn.getInputStream()));
        } catch (Exception e) {
            sb.append("Erro: " + e.toString());
        }
        //
        return sb.toString();
    }

    private static String readStream(InputStream inputStream) throws IOException {
        Reader reader = null;
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        //
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            inputStream,
                            "UTF-8"
                    )
            );

            int n;

            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

        } catch (Exception e) {
        } finally {
            reader.close();
        }


        return writer.toString();
    }


}
