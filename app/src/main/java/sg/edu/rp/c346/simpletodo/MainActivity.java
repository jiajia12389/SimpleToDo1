package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd,buttonDelete,buttonClear;
    ListView listView;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etTask = findViewById(R.id.etTask);
        btnAdd = findViewById(R.id.btnAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonClear = findViewById(R.id.buttonClear);
        listView = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);

        final ArrayList taskList;
        final ArrayAdapter<String> adapter;
        taskList = new ArrayList<>();

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etTask.setHint("Type in a new task");
                        buttonDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        buttonDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();

                taskList.add(task);
                adapter.notifyDataSetChanged();

            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskList.size()<0||etTask.getText()!=null){
                    taskList.clear();
                    etTask.setText("");
                    adapter.notifyDataSetChanged();
                }
                if(taskList.isEmpty()){
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int index = Integer.parseInt(etTask.getText().toString());
                    taskList.remove(index);
                    adapter.notifyDataSetChanged();

                }catch(IndexOutOfBoundsException e){
                    Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();
                }



            }
        });



    }
}
