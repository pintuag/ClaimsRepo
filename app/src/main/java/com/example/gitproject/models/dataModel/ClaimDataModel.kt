package com.example.gitproject.models.dataModel

data class ClaimsDataModel(
    val Claims: List<Claim>,
    val Reason: String,
    val Result: Boolean
)

data class Claim(
    val Claimtype: Claimtype,
    val Claimtypedetail: List<Claimtypedetail>
)

data class Claimtype(
    val id: String,
    val name: String
)

data class Claimtypedetail(
    val Claimfield: Claimfield,
    val claimfield_id: String,
    val claimtype_id: String,
    val id: String
)

data class Claimfield(
    val Claimfieldoption: List<Claimfieldoption>,
    val created: String,
    val id: String,
    val isdependant: String,
    val label: String,
    val modified: String,
    val name: String,
    val required: String,
    val type: String
)

data class Claimfieldoption(
    val belongsto: Any,
    val claimfield_id: String,
    val hasmany: String,
    val id: String,
    val label: String,
    val name: String
)

data class ClaimDummyModel(
    val name: String,
    val date: String,
    val expensesAmount: String
)

