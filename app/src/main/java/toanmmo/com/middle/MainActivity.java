package toanmmo.com.middle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edtA, edtB;
    private Button btnSubmit;
    private TextView txtResult;
    private ListView lvHistory;
    private ArrayList<String> arrayList;
    private ArrayAdapter adapter;
    private boolean checkNumber = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        handle();
        clearItemHistory();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        lvHistory.setAdapter(adapter);
    }

    private void init() {
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        txtResult = findViewById(R.id.txtResult);
        btnSubmit = findViewById(R.id.btnSubmit);
        lvHistory =(ListView) findViewById(R.id.lvHistory);
    }

    private void handle() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtA.getText().toString()) || TextUtils.isEmpty(edtB.getText().toString())){
                    Toast.makeText(MainActivity.this, "Vui long nhap day du du lieu vao !!", Toast.LENGTH_SHORT).show();
                    return;

                }
                try{
                    //hanlde
                    double numberA = Double.parseDouble(edtA.getText().toString());
                    double numberB = Double.parseDouble(edtB.getText().toString());
                    double result = numberA / numberB;
                    String resultText = numberA + " / " + numberB + " = " + result;
                    txtResult.setText(resultText);
                    arrayList.add(resultText);
                    adapter.notifyDataSetChanged();
                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Vui long nhap so vao !!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void clearItemHistory() {
        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Cleared !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
