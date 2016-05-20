package mintcode.com.myscrollerviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import mintcode.com.myscrollerview.MarkScrollerView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

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
        oldY = markScrollerView.getScrollY();
        mGestureDetector = new GestureDetector(this, this);

        sampleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                String expression = "";
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        expression = "ACTION_DOWN";
                        break;
                    case MotionEvent.ACTION_MOVE:
                        expression = "ACTION_MOVE";
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        expression = "ACTION_CANCEL";
                        break;
                    case MotionEvent.ACTION_UP:
                        expression = "ACTION_UP";
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        expression = "ACTION_OUTSIDE";
                        break;
                }
                System.out.println("action " + expression);
                return true;
            }
        });
        sampleView.setVisibility(View.GONE);
    }

    @OnClick(R.id.down)
    public void pullDown(View view) {
        System.out.println("fuck " + view.getClass().getName());
        oldY += deltaY;
        markScrollerView.smoothScrollBy(0, deltaY);

    }

    @OnClick(R.id.up)
    public void pullUp(View view) {
        markScrollerView.smoothScrollBy(0, -deltaY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        System.out.println("fuck");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e("fuck ", "scroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e("fuck ", "fling");
        return false;
    }
}
