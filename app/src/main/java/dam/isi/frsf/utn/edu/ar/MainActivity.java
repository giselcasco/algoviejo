package dam.isi.frsf.utn.edu.ar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    String[] tasasArray = {"25%", "27.5%", "30%", "32.3%", "35%", "38.5%"};
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


        seekDias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekDias, int i, boolean b) {
                tvDiasSeleccionados.setText(i);
                pf.setDias(i);
                tvIntereses.setText(pf.intereses().toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekDias) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekDias) {
            }
        });

    }

}
