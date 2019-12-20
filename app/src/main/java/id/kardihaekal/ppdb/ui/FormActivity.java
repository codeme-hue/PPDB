package id.kardihaekal.ppdb.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import id.kardihaekal.ppdb.R;
import id.kardihaekal.ppdb.adapter.viewpager.ViewPagerAdapter;

/** Class View Pager yang ga jadi di pake**/
public class FormActivity extends FragmentActivity {


  private ViewPager viewPagerForm;
  private Button buttonNext, buttonPrev;
  ViewPagerAdapter viewPagerAdapterForm;
  int page = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form);
    getApplicationContext();

    initObjects();
    initComponentViews();

    viewPagerForm.setAdapter(viewPagerAdapterForm);
    buttonPrev.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        page -= 1;
        viewPagerForm.setCurrentItem(page);
        if (page <= 0) {
          buttonPrev.setVisibility(Button.INVISIBLE);
        } else {
          buttonPrev.setVisibility(Button.VISIBLE);
        }
        if (page == 5) {
          buttonNext.setVisibility(Button.VISIBLE);
        }
      }
    });
    buttonNext.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        page += 1;
        viewPagerForm.setCurrentItem(page);
        if (page >= ViewPagerAdapter.PAGER_LENGTH) {
          buttonNext.setVisibility(Button.INVISIBLE);
        } else {
          buttonPrev.setVisibility(Button.VISIBLE);
        }
        if (page == 1) {
          buttonPrev.setVisibility(Button.VISIBLE);
        }

      }
    });
  }

  private void initComponentViews() {
    viewPagerForm = findViewById(R.id.viewPager);
    buttonNext = findViewById(R.id.buttonSelanjutnya);
    buttonPrev = findViewById(R.id.buttonSebelumnya);
  }


  private void initObjects() {
    viewPagerAdapterForm = new ViewPagerAdapter(getSupportFragmentManager());
  }
}
