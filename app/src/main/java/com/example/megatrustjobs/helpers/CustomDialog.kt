package com.example.megatrustjobs.helpers

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.megatrustjobs.R

object CustomDialog {

    private var okayBtnClickListener: ((Boolean) -> Unit)? = null


    private fun okCast(ok: Boolean) {
        okayBtnClickListener?.let {
            it(ok)
        }
    }





    fun showErrorDialog(
        context: Context,
        layoutInflater: LayoutInflater,
        errorMsg: String,
        localOkayBtnListener: ((Boolean) -> Unit)? = null
    ) {
        okayBtnClickListener = localOkayBtnListener
        val dialogBuilder: androidx.appcompat.app.AlertDialog.Builder =
            androidx.appcompat.app.AlertDialog.Builder(context, R.style.FullScreenAlertDialogTheme)
        val dialog: Dialog
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_info_layout, null)
        val dialogImage: ImageView = dialogView.findViewById(R.id.dialog_image)
        dialogImage.setImageResource(R.drawable.ic_security)
        val dialogTitle: TextView = dialogView.findViewById(R.id.info_dialog_title)
        dialogTitle.setText(context.getString(R.string.how_to_apply))
        val dialogContent: TextView = dialogView.findViewById(R.id.info_dialog_content)
        val btnOk: Button = dialogView.findViewById(R.id.btn_dialog_ok)
        if (dialogView.parent != null) {
            (dialogView.parent as ViewGroup).removeView(dialogView)
        }
        dialogBuilder
            .setView(dialogView)
            .setCancelable(false)
        dialog = dialogBuilder.create()
        dialog.window?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.rounded_dialog
            )
        )
        dialogContent.text = errorMsg
        dialog.show()
        btnOk.setOnClickListener {
            dialog.dismiss()
            okCast(true)
        }
    }

}