package pro.branium.learnjetpackcompose.sampleoop

class Director(
    private val bonus: Float,
    private val role: String
) : Leader(0.05f, 50000f) {
    override fun calculateSalary(numOfWorkingDayInMoth: Int): Double {
        return super.calculateSalary(numOfWorkingDayInMoth) + bonus
    }
}