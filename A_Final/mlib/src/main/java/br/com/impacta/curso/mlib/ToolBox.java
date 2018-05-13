package br.com.impacta.curso.mlib;

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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by nalmir on 12/05/2018.
 */

public class ToolBox {

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



    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
