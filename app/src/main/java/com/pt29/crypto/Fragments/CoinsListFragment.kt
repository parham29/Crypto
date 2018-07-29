package com.pt29.crypto.Fragments


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pt29.crypto.Activities.MainActivity
import com.pt29.crypto.Activities.adapter
import com.pt29.crypto.Network.NetworkManager
import com.pt29.crypto.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_scrolling.view.*
import kotlinx.android.synthetic.main.fragment_coins_list.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [CoinsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoinsListFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var shown = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_coins_list, container, false)
        view.recycler_coin_list_fragment.layoutManager = LinearLayoutManager(context)
        view.recycler_coin_list_fragment.adapter = adapter


        (activity as MainActivity).navigation.selectedItemId = R.id.navigation_coins_list


        //   val act = activity as AppCompatActivity
        //  act.setSupportActionBar(view.toolbar)
        //  act.getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        //  act.getSupportActionBar()?.title = ""
        //  view.img_header_search.setOnClickListener(click)
        //      view.search_close.setOnClickListener(click)
        view.swiperefreshlayout.setOnRefreshListener {
            NetworkManager().getAllFavoritesData()
            Handler().postDelayed({
                view.swiperefreshlayout.isRefreshing = false
            }, 1450)

        }
        return view
    }


    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CoinsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): CoinsListFragment {
            val fragment = CoinsListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}
