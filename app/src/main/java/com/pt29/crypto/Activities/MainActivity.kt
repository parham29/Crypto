package com.pt29.crypto.Activities

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.pt29.crypto.Adapters.CoinListAdapter
import com.pt29.crypto.DBManager.dbManagment
import com.pt29.crypto.Fragments.CoinsListFragment
import com.pt29.crypto.Fragments.NewsFragment
import com.pt29.crypto.Fragments.SearchFragment
import com.pt29.crypto.Network.NetworkManager
import com.pt29.crypto.R
import com.pt29.crypto.Sync.SyncJob
import com.pt29.crypto.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.util.*

var adapter: CoinListAdapter? = null


class MainActivity : AppCompatActivity() {


    private var coinsListFragment: CoinsListFragment? = null
    private var searchFragment: SearchFragment? = null
    private var newsFragment: NewsFragment? = null


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_coins_list -> {

                if (navigation.selectedItemId != R.id.navigation_coins_list) {
                    Log.i("LOG2902", "coinlist added")
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_frame, coinsListFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .addToBackStack(null)
                            .commit()

                }
//                addFragmentOnTop(coinsListFragment!!)
//                NetworkManager().getAllFavoritesData()
//                adapter!!.onNewData(dbManagment().getAllFavorites(true))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_add_coin -> {

                if (navigation.selectedItemId != R.id.navigation_add_coin) {
                    Log.i("LOG2902", "add added")
                    addFragmentOnTop(searchFragment!!,"ADDD")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_news -> {

//                adapter!!.onNewData(dbManagment().getAllFavorites(false))
                if (navigation.selectedItemId != R.id.navigation_news) {
                    Log.i("LOG2902", "news added")

                    addFragmentOnTop(newsFragment!!,"NEWS")
                }


//                RealmImporter.importFromJson(resources)
//                coinsListFragment = coinsListFragment ?: CoinsListFragment.newInstance("", "")
//                replaceFragment(coinsListFragment!!, R.id.main_frame)
                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        adapter = CoinListAdapter(this)

        binding.fab.setOnClickListener(View.OnClickListener {
            addFragmentOnTop(searchFragment!!,"ADDD")

        })



        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance("", "")

        }
        if (coinsListFragment == null) {
            coinsListFragment = CoinsListFragment.newInstance("", "")
        }
        if (newsFragment == null) {
            newsFragment = NewsFragment.newInstance("", "")
        }


        val p = dbManagment().getAllFavorites(true)
        adapter!!.onNewData(p)

        SyncJob.scheduleJob()
//
        NetworkManager().getAllFavoritesData()


//        binding.navigation.enableItemShiftingMode(false)
//        binding.navigation.enableShiftingMode(false)
//        binding.navigation.enableAnimation(false)
//        addFragmentOnTop(coinsListFragment!!)
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame, coinsListFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .addToBackStack(null)
                .commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.setOnNavigationItemReselectedListener(null)

    }


//    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
//        beginTransaction().func().commit()
//    }

//    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
//        supportFragmentManager.inTransaction { add(frameId, fragment) }
//    }
//
//
//    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
//        supportFragmentManager.inTransaction { replace(frameId, fragment) }
//    }


    private fun addFragmentOnTop(fragment: Fragment,name: String ) {
        supportFragmentManager

                .beginTransaction()
                .replace(R.id.main_frame, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(name)
                .commit()

        


    }


    override fun onBackPressed() {
//        super.onBackPressed()


    }

    @Override
    protected override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}

