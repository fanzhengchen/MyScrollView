package mintcode.com.myscrollerviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import mintcode.com.myscrollerview.MarkScrollerView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.up)
    ImageView up;
    @BindView(R.id.down)
    ImageView down;
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.right)
    ImageView right;
    @BindView(R.id.my_scroller)
    MarkScrollerView markScrollerView;

    private int oldY;
    private int deltaY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        oldY = markScrollerView.getScrollY();
    }

    @OnClick(R.id.down)
    public void pullDown(View view) {
        System.out.println("fuck " + view.getClass().getName());
        oldY += deltaY;
        markScrollerView.smoothScrollTo(0, oldY);

    }

    @OnClick(R.id.up)
    public void pullUp(View view){
        oldY -= deltaY;
        markScrollerView.smoothScrollTo(0, oldY);
    }


}
