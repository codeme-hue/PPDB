package id.kardihaekal.ppdb.adapter.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;

/**View Pager non slide Adapter tapi gak jadi dipake**/

public class NonSwipeableViewPager extends ViewPager {

  public NonSwipeableViewPager(Context context) {
    super(context);
    setMyScroller();
  }

  public NonSwipeableViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
    setMyScroller();
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    // Never allow swiping to switch between pages
    return false;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    // Never allow swiping to switch between pages
    return false;
  }

  //down one is added for smooth scrolling

  private void setMyScroller() {
    try {
      Class<?> viewpager = ViewPager.class;
      Field scroller = viewpager.getDeclaredField("mScroller");
      scroller.setAccessible(true);
      scroller.set(this, new MyScroller(getContext()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public class MyScroller extends Scroller {

    public MyScroller(Context context) {
      super(context, new DecelerateInterpolator());
    }
  }
}