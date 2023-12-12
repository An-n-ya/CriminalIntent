package host.ankh.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_DATE = "date"

class DatePickerFragment : DialogFragment(){
    interface Callbacks {
        fun onDateSelected(date: Date)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calender = Calendar.getInstance()
        calender.time = date
        val initialYear = calender.get(Calendar.YEAR)
        val initialMonth = calender.get(Calendar.MONTH)
        val initialDay = calender.get(Calendar.DAY_OF_MONTH)

        val dateListener = DatePickerDialog.OnDateSetListener {
                _: DatePicker, year: Int, month: Int, day:Int ->
            val resultDate: Date = GregorianCalendar(year, month, day).time
            targetFragment?.let { fragment ->
                (fragment as Callbacks).onDateSelected(resultDate)
            }
        }

        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }

    companion object {
        fun newInstance(date: Date) : DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }
            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }
}