package idpa.makalgorithm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_recyclerview)
    RecyclerView search_recyclerview;
    @BindView(R.id.results_textView)
    TextView results_textView;


    List<SearchObject> data;
    HorizontalAdapter adapter;
    HashMap<String, Double> similarities = new HashMap<String, Double>();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        similarities = (HashMap<String, Double>) extras.get("values");
        makeSearch();

    }

    private void makeSearch() {
        data = new ArrayList<>();
        for ( String key : similarities.keySet() ) {
            data.add(new SearchObject(key,similarities.get(key)));
        }
        search_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HorizontalAdapter(this, data);
        search_recyclerview.setAdapter(adapter);
        search_recyclerview.setLayoutManager(new GridLayoutManager(this, 1));
        adapter.setClickListener(new HorizontalAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SearchActivity.this, data.get(position).getSimilarity() + "", Toast.LENGTH_SHORT).show();
            }
        });
        results_textView.setText(data.size() + " Results");
    }

    public void backButton(View view) {
        onBackPressed();
    }
}