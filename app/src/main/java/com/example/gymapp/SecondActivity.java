package com.example.gymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    // private Button logout;
      private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();
     //   toolbar=(Toolbar) findViewById(R.id.toolbar2);
        listView=(ListView) findViewById(R.id.lvMain);

       // setupUiViews();
       // initToolbar();
       setupListview();

      //  logout=(Button)findViewById(R.id.btnLogout);

      /*  logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Logout();
            }
        });
*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this,MainActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
            }
        }

        return super.onOptionsItemSelected(item);
    }
   /*
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("yoga app");

    }

*/
    private void setupListview(){

        String[] title=getResources().getStringArray(R.array.Main);
        String[] description=getResources().getStringArray(R.array.Description);

        SimpleAdapter simpleAdapter=new SimpleAdapter(this,title,description);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        Intent intent= new Intent(SecondActivity.this,ListExercises.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        Intent intent= new Intent(SecondActivity.this,StopWatch_Activity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        Intent intent= new Intent(SecondActivity.this,BmiActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }

    public class SimpleAdapter extends BaseAdapter{

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;

        public SimpleAdapter(Context context,String[] title, String[] description){
            mContext=context;
            titleArray=title;
            descriptionArray=description;
            layoutInflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.second_activity_single_item, null);
            }
            title=(TextView)convertView.findViewById(R.id.tvMain);
            description=(TextView)convertView.findViewById(R.id.tvDescription);

            title.setText(titleArray[position]);
            description.setText(descriptionArray[position]);

            return convertView;
        }

    }

}
