package mintcode.com.myscrollerviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import mintcode.com.myscrollerview.MarkScrollerView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.my_scroller)
    MarkScrollerView markScrollerView;
    @BindView(R.id.sample_view)
    View sampleView;

    private int oldY;
    private int deltaY = 10;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
    
}
