package me.list.twitchboard;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.list.twitchboard.view.ChatFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TabHostActivity extends AppCompatActivity {

    @BindView(R.id.TabHost_ViewPager)
    ViewPager viewPager;
    @BindView(R.id.TabHost_TabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);
        ButterKnife.bind(this);
        initTabs();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initTabs() {
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
    }

    //TODO make this not shit
    private class TabsAdapter extends FragmentPagerAdapter {

        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Dashboard";
                case 1:
                    return "Chat";
                default:
                    throw new RuntimeException("this position is invalid: " + position);
            }
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new DashboardFragment();
                case 1:
                    return new ChatFragment();
                default:
                    throw new RuntimeException("this position is invalid: " + position);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
