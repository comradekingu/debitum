
package org.ebur.debitum.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import org.ebur.debitum.R;

// TODO add ActionBar-Button + Activity to add/edit Person
// TODO put fab in MainActivity instead of TransactionListFragment
// TODO make fab briefly disappear when changing tabs
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NEW_PERSON = "org.ebur.debitum.NEW_PERSON";

    // TODO can't I set these in xml and that's it?
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_people_title, R.string.tab_txn_title};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MainTabPagerAdapter adapter = new MainTabPagerAdapter(this);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        // setup and TabLayout and attach it to the ViewPager
        // TODO remove naming of tabs from here
        TabLayout tabs = findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> tab.setText(TAB_TITLES[position])
        ).attach();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onAddPersonAction(MenuItem item) {
        Intent intent = new Intent(this, EditPersonActivity.class);
        intent.putExtra(EXTRA_NEW_PERSON, true);
        startActivity(intent, null);
    }
}