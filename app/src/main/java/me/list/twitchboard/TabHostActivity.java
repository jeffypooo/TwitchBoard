package me.list.twitchboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.list.twitchboard.util.logging.LOG;
import me.list.twitchboard.view.ChatFragment;
import me.list.twitchboard.view.AccountFragment;
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
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(),
                new TabItem[]{new TabItem("Dashboard", DashboardFragment.class),
                              new TabItem("Chat", ChatFragment.class),
                              new TabItem("Account", AccountFragment.class)}
        ));
    }

    //TODO make this not shit
    private class TabsAdapter extends FragmentPagerAdapter {

        private final TabItem[] tabItems;

        public TabsAdapter(FragmentManager fm, TabItem[] tabItems) {
            super(fm);
            this.tabItems = tabItems;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position < tabItems.length) {
                return tabItems[position].getTitle();
            } else {
                throw new RuntimeException("invalid position! " + position);
            }
        }

        @Override
        public Fragment getItem(int position) {
            if (position < tabItems.length) {
                return tabItems[position].newFragment();
            } else {
                throw new RuntimeException("invalid position! " + position);
            }
        }

        @Override
        public int getCount() {
            return tabItems.length;
        }
    }

    private class TabItem {

        private static final String TAG = "TabItem";
        private final String                    title;
        private final Class<? extends Fragment> fragmentClass;

        public TabItem(String title, Class<? extends Fragment> fragmentClass) {
            this.title = title;
            this.fragmentClass = fragmentClass;
        }

        public String getTitle() {
            return title;
        }

        @Nullable
        public Fragment newFragment() {
            try {
                return fragmentClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                LOG.e(TAG,
                        e,
                        "Error creating new instance of %s",
                        fragmentClass.getCanonicalName()
                );
                return null;
            }
        }

    }

}
