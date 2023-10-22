package devandroid.daniel.applistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import devandroid.daniel.applistacurso.R;
import devandroid.daniel.applistacurso.controller.PessoaController;
import devandroid.daniel.applistacurso.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    PessoaController controller;
    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;
    public static final String NOME_PREFERENCES =  "pref_listavip";

    Pessoa pessoa;
    String dadosPessoa;

    EditText editPrimeiroNome;
    EditText editSegundoNome;
    EditText editNomeCurso;
    EditText editTelefoneContato;

    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSegundoNome = findViewById(R.id.editSegundoNome);
        editNomeCurso = findViewById(R.id.editNomeCurso);
        editTelefoneContato = findViewById(R.id.editTelefoneContato);

        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        preferences = getSharedPreferences(NOME_PREFERENCES,0);
        listaVip = preferences.edit();

        controller = new PessoaController();
        pessoa = new Pessoa();

        pessoa.setPrimeiroNome(preferences.getString("primeiroNome", ""));
        pessoa.setSobreNome(preferences.getString("sobreNome", ""));
        pessoa.setCursoDesejao(preferences.getString("nomeCurso", ""));
        pessoa.setTelefoneContato(preferences.getString("telefoneContato", ""));

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

                listaVip.clear();
                listaVip.apply();
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
                listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
                listaVip.putString("sobreNome", pessoa.getSobreNome());
                listaVip.putString("nomeCurso", pessoa.getCursoDesejao());
                listaVip.putString("telefoneContato", pessoa.getTelefoneContato());
                listaVip.apply();

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