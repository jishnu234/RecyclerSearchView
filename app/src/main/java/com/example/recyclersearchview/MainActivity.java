package com.example.recyclersearchview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<User> arrayList=new ArrayList<>();
    public static final String TAG="MainActivity";
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started");

        recyclerView=findViewById(R.id.recyclerView);

        arrayList.add(new User("jishnu",20));
        arrayList.add(new User("scahu",25));
        arrayList.add(new User("janvi",30));
        arrayList.add(new User("vasu",12));
        arrayList.add(new User("frudo",26));
        arrayList.add(new User("lewendoski",54));
        arrayList.add(new User("gabriel",32));
        arrayList.add(new User("venus",28));
        arrayList.add(new User("vasco",45));
        arrayList.add(new User("frenandus",23));
        arrayList.add(new User("guera",29));
        arrayList.add(new User("vladh",20));
        arrayList.add(new User("dracarys",25));
        arrayList.add(new User("lopu",54));


        inflateLayout();



    }

    private void inflateLayout() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =new MyAdapter(arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);//<-this takes away the search icon from the keyBoard

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
