package com.example.gitproject.view.fragments

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.gitproject.R
import com.example.gitproject.models.dataModel.Claimfieldoption
import com.example.gitproject.models.dataModel.ClaimsDataModel
import com.example.gitproject.models.dataModel.Claimtype
import com.example.gitproject.models.httpService.Result
import com.example.gitproject.util.toast
import com.example.gitproject.view.adapters.ClaimsListAdapter
import com.example.gitproject.view.baseFragment.BaseFragment
import com.example.gitproject.viewModels.ClaimsListViewModel
import kotlinx.android.synthetic.main.claim_layout.*
import okio.IOException

class ClaimFragment : BaseFragment() {

    lateinit var claimsListViewModel: ClaimsListViewModel
    lateinit var itemClickListener: ClaimsListAdapter.ItemClickListener
    //  lateinit var claimLayoutBinding: ClaimLayoutBinding

    var claimTypeList = ArrayList<Claimtype>()
    var claimFieldOptionList = ArrayList<Claimfieldoption>()
    lateinit var claimsDataModel: ClaimsDataModel


    val trendingListAdapter = ClaimsListAdapter()
    var selectedPosition = 0


    override fun getLayoutId() = R.layout.claim_layout

    override fun init() {

        //   setDataBinding()
        setRecyclerView()
        initViewModels()
        callClaimData()
    }


    private fun setSpinnerLayouts() {

        val tempList = ArrayList<String>()
        for (i in 0 until claimTypeList.size) {
            tempList.add(claimTypeList[i].name)
        }

        // val listOfLanguages = listOf<String>(*resources.getStringArray(R.array.languages))
        val insuranceAdapter =
            ArrayAdapter<String>(activity!!, R.layout.spinner_text_view_layout, tempList)
        languageSpinner.adapter = insuranceAdapter
        languageSpinner.setSelection(selectedPosition)
        languageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
//                    if(claimsDataModel.Claims[position].Claimtypedetail[])
//                    claimFieldOptionList.add()
                    if (position != selectedPosition) {
                        selectedPosition = position
                        //  callGitHubListApi()
                    }

                }
            }
    }

    private fun setRecyclerView() {

        claimRecyclerView.adapter = trendingListAdapter

    }

    private fun initViewModels() {

        claimsListViewModel = getViewModelInstance(ClaimsListViewModel::class.java)

        claimsListViewModel.claimLiveData.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    claimsDataModel = it.data
                    claimTypeList.clear()
                    Log.e("SizeOfAn", "  ${claimsDataModel.Claims.size}")
                    for (i in 0 until claimsDataModel.Claims.size) {
                        Log.e("SizeOfAn", "  $i")
                        val claimType = Claimtype(
                            claimsDataModel.Claims[i].Claimtype.id,
                            claimsDataModel.Claims[i].Claimtype.name
                        )
                        claimTypeList.add(claimType)
                    }
                    setSpinnerLayouts()
                }
                is Result.Error -> {
                    activity!!.toast(it.exception.message.toString())
                }
            }
        })

    }

    /* private fun setAdapterValues(data: List<TrendingListModel>) {
       //  trendingListAdapter.setTrendingListData(activity!!, data, itemClickListener)
     }*/


    private fun callClaimData() {
        claimsListViewModel.getClaimDataFromJson(loadJSONFromAsset()!!)
    }


    fun loadJSONFromAsset(): String? {
        val jsonString: String
        try {
            jsonString =
                activity!!.assets.open("claims_json.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}