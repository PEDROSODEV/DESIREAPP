package com.example.pedroarthurbarrantes.aplicacaotremmetrodesire;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et_nome,et_url,et_update_name,et_update_url;
    Button btnAdd,btnUpdate,btnCancel;
    RecyclerView recyclerView;

    MyAdapter adapter;


    List<EstacaoData> list = new ArrayList<>();

    AlertDialog.Builder builder;

    AlertDialog dialog;

    String name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nome = (EditText) findViewById(R.id.et_name);
        et_url = (EditText) findViewById(R.id.civ_image);

        btnAdd = (Button) findViewById(R.id.btn_add);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = et_nome.getText().toString();
                email = et_url.getText().toString();

                EstacaoData userData = new EstacaoData();

                userData.setNome(name);
                userData.setUrl(email);

                list.add(userData);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Estação adicionada com sucesso!",Toast.LENGTH_SHORT).show();

                et_nome.setText("");
                et_url.setText("");

            }
        });
        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClick(int position, EstacaoData userData) {

                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Update User Info");
                builder.setCancelable(false);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_update,null,false);
                InitUpdateDialog(position,view);
                builder.setView(view);
                dialog = builder.create();
                dialog.show();
            }
        });


    }

    private void InitUpdateDialog(final int position, View view) {

        et_update_name = view.findViewById(R.id.et_update_name);
        et_update_url = view.findViewById(R.id.civ_image);
        btnUpdate = view.findViewById(R.id.btn_update_user);
        btnCancel = view.findViewById(R.id.btn_update_cancel);

        et_update_name.setText(name);
        et_update_url.setText(email);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = et_update_name.getText().toString();
                email = et_update_url.getText().toString();

                EstacaoData userData = new EstacaoData();

                userData.setNome(name);
                userData.setUrl(email);

                adapter.UpdateData(position,userData);
                Toast.makeText(MainActivity.this,"Registro atualizado!",Toast.LENGTH_SHORT).show();

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
