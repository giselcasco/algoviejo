package dam.isi.frsf.utn.edu.ar;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import dam.isi.frsf.utn.edu.ar.Modelo.Cliente;
import dam.isi.frsf.utn.edu.ar.Modelo.PlazoFijo;

public class MainActivity extends AppCompatActivity {

    String[] tasasArray = {"25", "27.5", "30", "32.3", "35", "38.5"};
    private PlazoFijo pf;
    private Cliente cliente;

    // widgets de la vista
    private Button btnPF;
    private RadioGroup optMoneda;
    private RadioButton optDolar;
    private RadioButton optPesos;
    private SeekBar seekDias;
    private TextView tvCorreo;
    private TextView tvCuit;
    private TextView tvDias;
    private TextView tvMoneda;
    private TextView tvApp;
    private TextView tvMonto;
    private TextView tvDiasSeleccionados;
    private TextView tvMensaje;
    private TextView tvAvisarVencimiento;
    private TextView tvIntereses;
    private EditText edtMonto;
    private EditText edtCuit;
    private EditText edMail;
    private Switch swAvisarVencimiento;
    private ToggleButton togAccion;
    private CheckBox chkAceptoTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pf = new PlazoFijo(getResources().getStringArray(R.array.tasas));
        //pf = new PlazoFijo(tasasArray);   //R.array.tasas da nul i dont why know
        cliente = new Cliente();

        // widgets de la vista
        btnPF = (Button) findViewById(R.id.btnPF);
        btnPF.setEnabled(false);
        edtMonto = (EditText) findViewById(R.id.edtMonto);
        optMoneda = (RadioGroup) findViewById(R.id.optMoneda);
        optDolar = (RadioButton) findViewById(R.id.optDolar);
        optPesos = (RadioButton) findViewById(R.id.optPesos);
        seekDias = (SeekBar) findViewById(R.id.seekDias);
        seekDias.incrementProgressBy(10);
        seekDias.incrementSecondaryProgressBy(180);
        //seekDias.setMax(180);
        tvCorreo = (TextView) findViewById(R.id.tvCorreo);
        tvCuit = (TextView) findViewById(R.id.tvCuit);
        tvDias = (TextView) findViewById(R.id.tvDias);
        tvMoneda = (TextView) findViewById(R.id.tvMoneda);
        tvApp = (TextView) findViewById(R.id.tvApp);
        tvMonto = (TextView) findViewById(R.id.tvMonto);
        tvDiasSeleccionados = (TextView) findViewById(R.id.tvDiasSeleccionados);
        tvMensaje = (TextView) findViewById(R.id.tvMensaje);
        tvAvisarVencimiento = (TextView) findViewById(R.id.tvAvisarVencimiento);
        tvIntereses = (TextView) findViewById(R.id.tvIntereses);
        edtCuit = (EditText) findViewById(R.id.edtCuit);
        edMail = (EditText) findViewById(R.id.edMail);
        swAvisarVencimiento = (Switch) findViewById(R.id.swAvisarVencimiento);
        togAccion = (ToggleButton) findViewById(R.id.togAccion);
        chkAceptoTerminos = (CheckBox) findViewById(R.id.chkAceptoTerminos);

        //Ejercicio 5
        seekDias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekDias, int i, boolean b) {
                tvDiasSeleccionados.setText(" "+i);
                pf.setDias(i);
                if(Integer.valueOf(edtMonto.getText().toString()) > 0){
                pf.setMonto((double) Integer.valueOf(edtMonto.getText().toString()));
                tvIntereses.setText(pf.intereses().toString());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekDias) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekDias) {
            }
        });


        //Ejercicio 6
        chkAceptoTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                /// RESOLVER logica
                if(!b){
                    Context context;
                    context = getApplicationContext();
                    CharSequence text = "Es obligatorio aceptar las condiciones";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else{
                    btnPF.setEnabled(true);
                }

            }
        });

        //Ejercicio 7
        btnPF.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnPFClicked();
            }
        });
    }


    private void btnPFClicked() {
        boolean error=false;
        String email;
        String cuit;
        int monto;
        int dias;

        //Validar Correo Electronico
        email = edMail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(!email.matches(emailPattern) ){
            Toast.makeText(MainActivity.this,"Debe Ingresar un Correo Electrónico válido",Toast.LENGTH_LONG).show();
            return;
        }

        cuit = edtCuit.getText().toString().replace(" ", "");
        monto = Integer.parseInt(edtMonto.getText().toString());
        dias = pf.getDias();

        if(dias<=10){
            error =true;
            tvMensaje.append("La cantidad de días Seleccionados debe ser superior a 10");
        }
        if(monto <=0){
            error =true;
            tvMensaje.append("El Monto a Invertir no puede ser nulo");
        }
        if(email.length()<=0){
            error =true;
            tvMensaje.append("Debe ingresar un Email");
        }
        if(cuit.length()<=0 ){
            error =true;
            tvMensaje.append("Debe ingresar un CUIT/CUIL");
        }

        if(error){
            tvMensaje.setTextColor(Color.RED);
            Context context;
            context = getApplicationContext();
            CharSequence text = "ERROR! no es posible realizar el Plazo Fijo con los Datos Ingresados";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
            tvMensaje.setTextColor(Color.BLUE);
            tvMensaje.append("El Plazo Fijo se Realizo Exitosamente");
            tvMensaje.append("Plazo Fijo ( Días: "
                    + dias+" , Monto: "
                    + monto +" Avisar Vencimiento: Renovar Automáticamente: Moneda: )");
        }
    }



}
