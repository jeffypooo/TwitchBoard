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

    private class TabsAdapter extends FragmentPagerAdapter {

        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Live";
        }

        @Override
        public Fragment getItem(int position) {
            return new DashboardFragment();
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

}
