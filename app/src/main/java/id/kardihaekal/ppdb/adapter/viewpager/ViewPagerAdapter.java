package id.kardihaekal.ppdb.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**View Pager Adapter tapi gak jadi dipake**/


public class ViewPagerAdapter extends FragmentPagerAdapter {

  public static int PAGER_LENGTH = 6;

  public ViewPagerAdapter(@NonNull FragmentManager fm) {
    super(fm);
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    switch (position){
      case 0:
       // return new SatuFragment();

      case 1:
        //return new DuaFragment();

      case 2:
        //return new TigaFragment();

      case 3:
        //return new EmpatFragment();

      case 4:
        //return new LimaFragment();

      case 5:
        //return new EnamFragment();
    }
    return null;

  }

  @Override
  public int getCount() {
    return PAGER_LENGTH;
  }
}
