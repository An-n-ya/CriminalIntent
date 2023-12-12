package host.ankh.criminalintent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import java.util.*

class CrimeDetailViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    private val crimeIdLiveData = MutableLiveData<UUID>();

    var crimeListData: LiveData<Crime?> = crimeIdLiveData.switchMap { crimeRepository.getCrime(it) }

    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

    fun saveCrime(crime: Crime) {
        crimeRepository.updateCrime(crime)
    }
}