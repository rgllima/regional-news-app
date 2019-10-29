package com.br.regionalnews.view.searcharticle

import android.content.DialogInterface
import android.graphics.Rect
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.br.regionalnews.R

class SearchArticleDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.search_article_dialog, container, false)

        val displayReact = Rect()
        val window = dialog?.window

        // Alterando as propriedades do Dialog
        window?.setGravity(Gravity.TOP)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.decorView?.getWindowVisibleDisplayFrame(displayReact)
        window?.setLayout(displayReact.width(), WindowManager.LayoutParams.WRAP_CONTENT)
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        return view
    }

    companion object {
        const val TAG = "SearchArticleDialog.TAG"
    }

    override fun onDismiss(dialog: DialogInterface) {
        findNavController().popBackStack()
        super.onDismiss(dialog)
    }

}