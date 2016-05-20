package mintcode.com.myscrollerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mark on 16-5-17.
 */
public class MarkScrollerView extends FrameLayout {
//    private static Logger logger = Logger.getLogger(TAG);

    private static float DENSITY;
    private Scroller mScroller;
    private Context context;
    private int width;
    private int height;

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
        mScroller = new Scroller(ctx);
        DENSITY = context.getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = widthMeasureSpec;
        height = heightMeasureSpec;
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        drawTestPolygon(canvas);
//    }
//
//    void drawTestPolygon(Canvas canvas) {
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(Color.RED);
//        int padding = dip2Pix(10);
//        RectF rectF = new RectF(0, 0, width - padding, height - padding);
//        canvas.drawRoundRect(rectF, padding, padding, paint);
//    }

    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }

    public void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        invalidate();
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getFinalY());
            postInvalidate();
        }
        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = MotionEventCompat.getActionMasked(event);
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
//        Log.e("fuck ", x + " " + y + " " + expression);
        return false;
    }

    static int dip2Pix(int dip) {
        return (int) (dip * DENSITY + 0.5f);
    }
}
