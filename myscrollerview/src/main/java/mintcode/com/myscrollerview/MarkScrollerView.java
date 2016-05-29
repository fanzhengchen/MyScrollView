package mintcode.com.myscrollerview;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.OverScroller;

/**
 * Created by mark on 16-5-17.
 */
public class MarkScrollerView extends FrameLayout {

    private static float DENSITY;
    private static int OVER_PIXELS = 50;
    private OverScroller mOverScroller;
    private Context context;
    private GestureDetectorCompat mGestureDetectorCompat;
    private int width;
    private int height;
    private int positionX;
    private int positionY;

    public MarkScrollerView(Context context) {
        this(context, null);
    }

    public MarkScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MarkScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public MarkScrollerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs);
    }

    private void initView(Context ctx) {
        context = ctx;
        mOverScroller = new OverScroller(ctx);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mGestureDetectorCompat = new GestureDetectorCompat(ctx, gestureListener);
        DENSITY = dm.density;
        width = dm.widthPixels;
        height = dm.heightPixels;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetectorCompat.onTouchEvent(event);
        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mOverScroller.computeScrollOffset()) {
            positionX = mOverScroller.getCurrX();
            positionY = mOverScroller.getCurrY();
            scrollTo(positionX, positionY);
        } else {
            mOverScroller.springBack(positionX, positionY, 0, width, 0, height);
        }
    }

    static int dip2Pix(int dip) {
        return (int) (dip * DENSITY + 0.5f);
    }

    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            mOverScroller.forceFinished(true);
            ViewCompat.postInvalidateOnAnimation(MarkScrollerView.this);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            mOverScroller.forceFinished(true);
            mOverScroller.fling(positionX, positionY,
                    (int) -velocityX, (int) -velocityY, 0, width, 0, height);
            ViewCompat.postInvalidateOnAnimation(MarkScrollerView.this);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mOverScroller.forceFinished(true);
            int dx = (int) distanceX;
            int dy = (int) distanceY;
//            int newPositionX = positionX + dx;
//            int newPositionY = positionY + dy;
            mOverScroller.startScroll(positionX, positionY, dx, dy, 0);
            ViewCompat.postInvalidateOnAnimation(MarkScrollerView.this);
            return true;
        }

    };


}
