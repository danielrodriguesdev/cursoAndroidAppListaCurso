package devandroid.daniel.appgaseta.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


import devandroid.daniel.appgaseta.R;
import devandroid.daniel.appgaseta.controller.CursoController;
import devandroid.daniel.appgaseta.controller.PessoaController;
import devandroid.daniel.appgaseta.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    PessoaController controller;
    CursoController cursoController;

    Pessoa pessoa;
    List<String> nomesDosCursos;
    String dadosPessoa;

    EditText editPrimeiroNome;
    EditText editSegundoNome;
    EditText editNomeCurso;
    EditText editTelefoneContato;

    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSegundoNome = findViewById(R.id.editSegundoNome);
        editNomeCurso = findViewById(R.id.editNomeCurso);
        editTelefoneContato = findViewById(R.id.editTelefoneContato);


        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        // Adapter
        // Layout
        // Injetar o Adapter ao Spinner - A lista gerada

        cursoController = new CursoController();
        nomesDosCursos = cursoController.dadosParaSpinner();

        controller = new PessoaController(MainActivity.this);
        pessoa = new Pessoa();
        controller.buscar(pessoa);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                cursoController.dadosParaSpinner());

        adapter.setDropDownViewResource( android.R.layout.simple_list_item_1);

        spinner.setAdapter(adapter);

        editPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editSegundoNome.setText(pessoa.getSobreNome());
        editTelefoneContato.setText(pessoa.getTelefoneContato());
        editNomeCurso.setText(pessoa.getCursoDesejao());

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPrimeiroNome.setText("");
                editSegundoNome.setText("");
                editTelefoneContato.setText("");
                editNomeCurso.setText("");

                controller.limpar();

            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte Sempre!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pessoa.setPrimeiroNome(editPrimeiroNome.getText().toString());
                pessoa.setSobreNome(editSegundoNome.getText().toString());
                pessoa.setCursoDesejao(editNomeCurso.getText().toString());
                pessoa.setTelefoneContato(editTelefoneContato.getText().toString());

                Toast.makeText(MainActivity.this, pessoa.toString(), Toast.LENGTH_LONG).show();

                controller.salvar(pessoa);
            }
        });

        dadosPessoa = "Primeiro nome: ";
        dadosPessoa += pessoa.getPrimeiroNome();
        dadosPessoa = " Sobrenome: ";
        dadosPessoa += pessoa.getSobreNome();
        dadosPessoa = " Curso Desejado: ";
        dadosPessoa += pessoa.getCursoDesejao();
        dadosPessoa = " Telefone de Contato: ";
        dadosPessoa += pessoa.getTelefoneContato();

        Log.i("POOAndroid", pessoa.toString());

    }
}