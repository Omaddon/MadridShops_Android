package com.ammyt.madridshops.fragment


import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ammyt.domain.model.Shop
import com.ammyt.domain.model.Shops

import com.ammyt.madridshops.R
import com.ammyt.madridshops.adapter.ShopRecyclerViewAdapter


class ShopsListFragment : Fragment() {

    private lateinit var root: View
    private lateinit var shopRecyclerView: RecyclerView
    private var onShowShopDetail: OnShowShopDetail? = null

    private var shops: Shops? = null

    companion object {
        private val SHOP_ARG = "SHOP_ARG"

        fun newInstance(shops: Shops): ShopsListFragment {
            val argument = Bundle()
            argument.putSerializable(SHOP_ARG, shops)

            val listFragment = ShopsListFragment()
            listFragment.arguments = argument

            return listFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater?.let {
            root = it.inflate(R.layout.fragment_list, container, false)

            shopRecyclerView = root.findViewById(R.id.shops_recycler_view) as RecyclerView
            shopRecyclerView.layoutManager = GridLayoutManager(activity, resources.getInteger(R.integer.recycler_columns))
            shopRecyclerView.itemAnimator = DefaultItemAnimator()

            val adapter: ShopRecyclerViewAdapter


            if (arguments != null) {
                shops = arguments.getSerializable(SHOP_ARG) as? Shops
                adapter = ShopRecyclerViewAdapter(shops)
                setListenerToAdapter(adapter)
            } else {
                adapter = ShopRecyclerViewAdapter(Shops(ArrayList()))
            }


            shopRecyclerView.adapter = adapter

        }

        return root
    }

    private fun setListenerToAdapter(adapter: ShopRecyclerViewAdapter) {
        adapter.onClickListener = View.OnClickListener { v: View? ->
            val position = shopRecyclerView.getChildAdapterPosition(v)
            val shop = shops?.get(position)

            // Send order to navigate to activity container
            onShowShopDetail?.showShopDetail(shop!!)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnShowShopDetail) {
            onShowShopDetail = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        onShowShopDetail = null
    }


    /**
     * INTERFACES
     */

    // TODO implementar navegación a shopDetail
    interface OnShowShopDetail {
        fun showShopDetail(shop: Shop)
    }

}
