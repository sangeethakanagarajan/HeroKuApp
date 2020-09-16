package com.ex.herokuapp.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.ex.herokuapp.R
import com.ex.herokuapp.view.activity.OrderDetailActivity
import kotlinx.android.synthetic.main.fragment_dialog.view.*

class CustomDialogFragment : DialogFragment() {

    private var isIPEmpty: Boolean = false
    private var content: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = arguments?.getString("content")

        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style, theme)
    }

    // Override the Fragment.onAttach() method to instantiate the
    // NoticeDialogListener
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_dialog, container, false)

        val ip1 = view.edit_ip1.text
        val ip2 = view.edit_ip2.text
        val ip3 = view.edit_ip3.text
        val ip4 = view.edit_ip4.text

        view.btn_cancel.setOnClickListener {
            Toast.makeText(activity, "action cancelled", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        view.btn_ok.setOnClickListener {
            Toast.makeText(activity, "User Accepted Action", Toast.LENGTH_SHORT).show()
            isIPEmpty =
                (ip1 != null && ip2 != null && ip3 != null && ip4 != null) && (ip1.length > 0 && ip2.length > 0 && ip3.length > 0 && ip4.length > 0)

            val callingActivity: OrderDetailActivity? = activity as OrderDetailActivity?
            callingActivity?.onUserSelectValue(isIPEmpty)
            dismiss()
        }
        return view
    }

    companion object {


        /**
         * Create a new instance of CustomDialogFragment, providing "num" as an
         * argument.
         */
        fun newInstance(content: String): CustomDialogFragment {
            val f = CustomDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putString("content", content)
            f.arguments = args

            return f
        }
    }

}
