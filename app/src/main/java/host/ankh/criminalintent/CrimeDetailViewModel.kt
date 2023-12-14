package host.ankh.criminalintent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import java.io.File
import java.util.*
private const val TAG = "CrimeDetailViewModel"
class CrimeDetailViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    private val crimeIdLiveData = MutableLiveData<UUID>();

    var crimeListData: LiveData<Crime?> = crimeIdLiveData.switchMap { crimeRepository.getCrime(it) }

    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

    fun saveCrime(crime: Crime) {
        Log.d(TAG, "saveCrime id= ${crime.id}, title=${crime.title}")
        crimeRepository.updateCrime(crime)
    }

    fun getPhotoFile(crime: Crime): File {
        return crimeRepository.getPhotoFile(crime)
    }
}