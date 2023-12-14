package host.ankh.criminalintent

import androidx.lifecycle.ViewModel

private const val TAG = "CrimeListFragment"

class CrimeListViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()

    val crimeListLiveData = crimeRepository.getCrimes()

    fun addCrime(crime: Crime) {
        crimeRepository.addCrime(crime)
    }
}