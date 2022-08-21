package com.example.a2_avaliativo_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UsuarioLogado extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private ArrayList<Cliente> clienteArrayList = new ArrayList<>();

    RecyclerView recyclerView;
    Button btFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_logado);

        btFavoritos = findViewById(R.id.buttonFavoritos);
        btFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UsuarioLogado.this, UsuarioFavoritos.class));
            }
        });

        iniciarFirebase();

        Tarefa tarefa = new Tarefa();
        tarefa.execute("https://reqres.in/api/users");

    }

    public class Tarefa extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String retorno = Conexao.getDados(strings[0]);
            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);

            clienteArrayList = ConsumirJson.jsonDados(s);
            //System.out.println("***RESPOSTA*** " + clienteArrayList.toString());

            recyclerView = findViewById(R.id.recyclerView01);
            setAdapter();

            /**ArrayAdapter<Usuario> adapter = new ArrayAdapter<>(
                    UsuarioLogado.this,
                    android.R.layout.simple_list_item_1,
                    clienteArrayList
            );

            setListAdapter(adapter);*/

        }
    }

    private void setAdapter() {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(clienteArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN) {

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();

                databaseReference.child("Cliente").
                        child(clienteArrayList.get(position).getFirstName()).
                        setValue(clienteArrayList.get(position));

                clienteArrayList.remove(position);

                recyclerAdapter.notifyDataSetChanged();

            }
        };

        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

    }

    private void iniciarFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

}