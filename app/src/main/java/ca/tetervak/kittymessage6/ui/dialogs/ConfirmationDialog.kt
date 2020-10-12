package ca.tetervak.kittymessage6.ui.dialogs

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import ca.tetervak.kittymessage6.R

class ConfirmationDialog : DialogFragment() {

    companion object{

        private const val MESSAGE = "message"
        private const val TAG = "confirmation_dialog"

        fun show(message: String, target: Fragment, requestCode: Int) {
            ConfirmationDialog().apply {
                arguments = Bundle().apply { putString(MESSAGE, message) }
                setTargetFragment(target, requestCode)
                show(target.parentFragmentManager, TAG)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val activity = requireActivity()
        val message = arguments?.getString(MESSAGE) ?: ""

        return AlertDialog.Builder(activity).apply{
            setTitle(R.string.app_name)
            setMessage(message)
            setPositiveButton(android.R.string.ok) { _, _ -> confirmed() }
            setNegativeButton(android.R.string.cancel, null)
        }.create()
    }

    private fun confirmed() {
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, null)
    }

}