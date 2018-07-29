package com.pt29.crypto.Fragments


import android.content.Context.INPUT_METHOD_SERVICE
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import com.otaliastudios.autocomplete.Autocomplete
import com.otaliastudios.autocomplete.AutocompleteCallback
import com.pt29.crypto.Activities.MainActivity
import com.pt29.crypto.Adapters.GridAdapter
import com.pt29.crypto.DBManager.dbManagment
import com.pt29.crypto.Models.CoinApiModel
import com.pt29.crypto.R
import com.pt29.crypto.Utils.CoinPresenter
import com.pt29.crypto.Utils.GridAutofitLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    //TODO reanem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val presenter = CoinPresenter(context!!)

        (activity as MainActivity).navigation.selectedItemId = R.id.navigation_add_coin

        val adapter = GridAdapter()
        val resId = R.anim.grid_layout_animation_from_bottom
        val animation = AnimationUtils.loadLayoutAnimation(view.context, resId)
        view.recycler_add_fragmnet.setLayoutAnimation(animation)


        view.recycler_add_fragmnet.layoutManager = GridAutofitLayoutManager(view.context, Math.round(100 * Resources.getSystem().getDisplayMetrics().density))
        view.recycler_add_fragmnet.adapter = adapter


        val list = dbManagment().getAllFavorites(false)
        adapter.onNewData(list)


        val callback = object : AutocompleteCallback<CoinApiModel> {
            override fun onPopupItemClicked(editable: Editable, item: CoinApiModel): Boolean {
                editable.clear()
                presenter.updateAllList(item)
                dbManagment().addToFavorites(item.id!!)
                list.add(0, item)
                adapter.onNewData(list)
                view.hideKeyboard()
                return true
            }

            override fun onPopupVisibilityChanged(shown: Boolean) {}
        }
        val backgroundDrawable = ColorDrawable(Color.WHITE)

        Autocomplete.on<CoinApiModel>(view.edt_search)
                .with(6f)
                .with(backgroundDrawable)
                .with(presenter)
                .with(callback)
                .build()
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
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): SearchFragment {
            val fragment = SearchFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}// Required empty public constructor
