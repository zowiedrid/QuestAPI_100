package com.example.roomsiswa.view.route

import com.example.roomsiswa.R
import com.example.roomsiswa.uicontroller.DestinasiNavigasi

object DestinasiEditSiswa : DestinasiNavigasi{
    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa
    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"

}