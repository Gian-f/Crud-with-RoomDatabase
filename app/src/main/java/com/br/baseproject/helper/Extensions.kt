package com.br.baseproject.helper

import androidx.appcompat.app.AppCompatActivity
import com.br.baseproject.R
import com.br.baseproject.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


fun AppCompatActivity.showBottomSheet (
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: Int,
    onClick: () -> Unit = {}
) {
    val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
    val bottomSheetBinding: BottomSheetBinding = BottomSheetBinding.inflate(layoutInflater, null, false)

    bottomSheetBinding.textTitle.text = getString(titleDialog ?: R.string.text_title_bottom_sheet)
    bottomSheetBinding.textMessage.text = getText(message)
    bottomSheetBinding.btnClick.text = getString(titleButton ?: R.string.text_button_bottom_sheet)
    bottomSheetBinding.btnClick.setOnClickListener {
        onClick()
        bottomSheetDialog.dismiss()
    }
    bottomSheetDialog.setContentView(bottomSheetBinding.root)
    bottomSheetDialog.show()
}