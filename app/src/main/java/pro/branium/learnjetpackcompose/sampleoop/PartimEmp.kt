package pro.branium.learnjetpackcompose.sampleoop

class PartimeEmp(
    val shift: String,
    val totalHours: Int
) : Employee() {
    private var _workingDays: Int = 0

    fun calculateWorkingDay() {
        _workingDays = totalHours / 8
    }

    override fun calculateSalary(numOfWorkingDayInMoth: Int): Double {
        calculateWorkingDay()
        return salary / numOfWorkingDayInMoth * _workingDays
    }
}