package br.com.impacta.curso.prj_007_listview_interface;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ListView lv_posts;
    private CustomAdapter<Post> postAdapter;
    private List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);
        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();
        lv_posts = findViewById(R.id.lv_posts);
        int[] to = {R.id.celula_tv_post, R.id.celula_cb_curtir};
        String[] from = {"Texto", "Gostei"};
        gerarPosts();
        postAdapter = new CustomAdapter<>(context, posts, R.layout.celula, from, to);
        postAdapter.setOnCheckBoxChangedListener(
                new CustomAdapter.ICheckBoxChanged() {
                    @Override
                    public void checkboxChanged(CompoundButton cb, boolean value, long id) {
                        if (cb.getId() == R.id.celula_cb_curtir) updatePost(id, value);
                    }
                }
        );
        lv_posts.setAdapter(postAdapter);

    }

    private void updatePost(long id, boolean value) {
        for (Post post: posts) {
            if (post.getId() == id) {
                post.setGostei(value);
            }
        }
    }

    private void gerarPosts() {
        boolean gostei = false;
        posts = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Post p = new Post(i, "Texto " + String.valueOf(i), gostei);
            gostei = !gostei;
            posts.add(p);
        }
    }

    private void initActions() {
        lv_posts.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Post p = (Post)adapterView.getItemAtPosition(i);
                        Toast.makeText(context, p.getTexto(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
