package datasign.calculadoraip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditIp;
    private Spinner mSpinnerBits;
    private TextView mTextRede;
    private TextView mTextBroadCast;
    private TextView mTextQtdHots;
    private EditText mEditMascara;
    private Button mBtnCalcula;
    private Conversor conversor = Conversor.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, conversor.getBits());
        mSpinnerBits.setAdapter(adapter);
        mBtnCalcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSpinnerBits.getSelectedItem().equals("-")){
                    if(validate()){
                        String binIp = conversor.toBinAddr(mEditIp.getText().toString());
                        String binMask = conversor.toBinAddr(mEditMascara.getText().toString());
                        mTextRede.setText("Rede: " + conversor.getRedeAddr(binIp, binMask));
                        mTextBroadCast.setText("BroadCast: " + conversor.getBroadCastAddr(binIp, binMask));
                        mTextQtdHots.setText("Endereços: " + conversor.getQuantidadeHosts(binMask));
                    }
                }else {
                    if(validadeIP()){
                        String binIp = conversor.toBinAddr(mEditIp.getText().toString());
                        String binMask = conversor.addPontos(conversor.getMascara(Integer.valueOf(mSpinnerBits.getSelectedItem().toString())));
                        mTextRede.setText("Rede: " + conversor.getRedeAddr(binIp, binMask));
                        mTextBroadCast.setText("BroadCast: " + conversor.getBroadCastAddr(binIp, binMask));
                        mTextQtdHots.setText("Endereços: " + conversor.getQuantidadeHosts(binMask));
                        mEditMascara.setText(conversor.getMascaraDecimal(binMask));
                    }
                }
            }
        });
    }

    private boolean validate(){
        String ip = mEditIp.getText().toString();
        String mask = mEditMascara.getText().toString();
        if (ip.isEmpty() || !Patterns.IP_ADDRESS.matcher(ip).matches()) {
            mEditIp.setError("Endereço de IP inválido");
            return false;
        }else if(mask.isEmpty() || !Patterns.IP_ADDRESS.matcher(mask).matches()) {
            mEditMascara.setError("Endereço de Máscara inválida");
            return false;
        } else {
            mEditIp.setError(null);
            mEditMascara.setError(null);
            return true;
        }
    }

    private boolean validadeIP(){
        String ip = mEditIp.getText().toString();
        if (ip.isEmpty() || !Patterns.IP_ADDRESS.matcher(ip).matches()) {
            mEditIp.setError("Endereço de IP inválido");
            return false;
        }else {
            mEditIp.setError(null);
            return true;
        }
    }

    private void initViews(){
        mEditIp = (EditText)findViewById(R.id.activity_main_ip);
        mEditMascara = (EditText)findViewById(R.id.activity_main_mask);
        mBtnCalcula = (Button)findViewById(R.id.activity_main_btn_calcula);
        mTextBroadCast = (TextView)findViewById(R.id.activity_main_broadcast);
        mTextRede = (TextView)findViewById(R.id.activity_main_rede);
        mTextQtdHots = (TextView)findViewById(R.id.activity_main_qtd_hosts);
        mSpinnerBits = (Spinner)findViewById(R.id.activity_home_bits);
    }
}