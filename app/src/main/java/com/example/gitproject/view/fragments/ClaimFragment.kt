package com.example.gitproject.view.fragments

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.gitproject.R
import com.example.gitproject.models.dataModel.ClaimDummyModel
import com.example.gitproject.models.dataModel.ClaimsDataModel
import com.example.gitproject.models.dataModel.Claimtype
import com.example.gitproject.models.httpService.Result
import com.example.gitproject.util.toast
import com.example.gitproject.view.adapters.ClaimsListAdapter
import com.example.gitproject.view.baseFragment.BaseFragment
import com.example.gitproject.viewModels.ClaimsListViewModel
import kotlinx.android.synthetic.main.claim_layout.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ClaimFragment : BaseFragment(), ClaimsListAdapter.ItemClickListener {

    lateinit var claimsListViewModel: ClaimsListViewModel
    lateinit var itemClickListener: ClaimsListAdapter.ItemClickListener
    //  lateinit var claimLayoutBinding: ClaimLayoutBinding

    var claimTypeList = ArrayList<Claimtype>()
    var claimDummyList = ArrayList<ClaimDummyModel>()
    lateinit var claimsDataModel: ClaimsDataModel


    val claimListAdapter = ClaimsListAdapter()
    var selectedPosition = 0


    override fun getLayoutId() = R.layout.claim_layout

    override fun init() {

        setRecyclerView()
        initViewModels()
        initClickListeners()
        callClaimData()
        setClaimDate()
    }

    private fun initClickListeners() {
        addClaimDataButton.setOnClickListener {

            val claimDummyModel = ClaimDummyModel(
                claimType.selectedItem.toString(), claimDate.text.toString(),
                expensesText.text.toString()
            )
            claimDummyList.add(claimDummyModel);
            claimListAdapter.setClaimDateList(activity!!, claimDummyList, this)
        }
    }

    private fun setClaimDate() {
        val calendar = Calendar.getInstance().time
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        claimDate.text = sdf.format(calendar)
    }


    private fun setSpinnerLayouts() {

        setClaimTypeSpinner()
        setDistrictSpinner()
        setModeOfTravelSpinner()
        setClassOfTravelSpinner()
    }

    private fun setClaimTypeSpinner() {
        val tempList = ArrayList<String>()
        for (i in 0 until claimTypeList.size) {
            tempList.add(claimTypeList[i].name)
        }

        // val listOfLanguages = listOf<String>(*resources.getStringArray(R.array.languages))
        val claimTypeListAdapter =
            ArrayAdapter<String>(activity!!, R.layout.spinner_text_view_layout, tempList)
        claimType.adapter = claimTypeListAdapter
        claimType.setSelection(selectedPosition)
        claimType.onItemSelectedListener =
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

    private fun setDistrictSpinner() {
        val districtList = ArrayList<String>()
        districtList.add("Delhi")
        districtList.add("Kanpur")
        districtList.add("api_district")

        val districtAdapter =
            ArrayAdapter<String>(activity!!, R.layout.spinner_text_view_layout, districtList)
        fromDistrict.adapter = districtAdapter
        fromDistrict.setTitle(getString(R.string.from_district))
        fromDistrict.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }

        toDistrict.adapter = districtAdapter
        toDistrict.setTitle(getString(R.string.to_district))
        toDistrict.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }

    }

    private fun setModeOfTravelSpinner() {
        val modeOfTravelList = ArrayList<String>()
        modeOfTravelList.add("Company Vehicle")
        modeOfTravelList.add("Train")
        modeOfTravelList.add("Bus")
        modeOfTravelList.add("Flight")
        modeOfTravelList.add("Car")
        modeOfTravelList.add("Taxi")
        modeOfTravelList.add("Motor Cycle")

        val modeOfTravelAdapter =
            ArrayAdapter<String>(activity!!, R.layout.spinner_text_view_layout, modeOfTravelList)
        modeOfTravel.adapter = modeOfTravelAdapter
        modeOfTravel.setTitle(getString(R.string.mode_of_travel))
        modeOfTravel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }
    }

    private fun setClassOfTravelSpinner() {
        val classOfTravelList = ArrayList<String>()
        classOfTravelList.add("NA")
        classOfTravelList.add("Chair Car")
        classOfTravelList.add("I AC Train")
        classOfTravelList.add("II AC Train")
        classOfTravelList.add("III tier Train")
        classOfTravelList.add("Sleeper Bus")
        classOfTravelList.add("AC Bus")
        classOfTravelList.add("Ordinary Bus")
        classOfTravelList.add("Sleeper")
        classOfTravelList.add("General")

        val classOfTravelAdapter =
            ArrayAdapter<String>(activity!!, R.layout.spinner_text_view_layout, classOfTravelList)
        classOfTravel.adapter = classOfTravelAdapter
        classOfTravel.setTitle(getString(R.string.class_of_travel))
        classOfTravel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }
    }

    private fun setRecyclerView() {
        claimRecyclerView.adapter = claimListAdapter
    }

    private fun initViewModels() {

        claimsListViewModel = getViewModelInstance(ClaimsListViewModel::class.java)

        claimsListViewModel.claimLiveData.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    claimsDataModel = it.data
                    claimTypeList.clear()
                    for (i in claimsDataModel.Claims.indices) {
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

    override fun itemClick(claimListModel: ClaimDummyModel) {

    }

}