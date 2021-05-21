package idpa.makalgorithm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsActivty extends AppCompatActivity {

    @BindView(R.id.info_textview)
    TextView info_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_activty);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        String values = extras.getString("values");
        info_textview.setText(values);
    }

    public void backButton(View view) {
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}