package pro.branium.learnjetpackcompose.sampleoop

interface EmployeeAction {
    fun checkin(): Long

    fun checkout(): Long

    fun calculateSalary(numOfWorkingDayInMoth: Int): Double

    fun work()
}