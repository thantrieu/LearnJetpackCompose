package pro.branium.learnjetpackcompose.sampleoop

open class Leader(
    private val commissionPercent: Float,
    private val totalSales: Float
) : Employee() {
    override fun calculateSalary(numOfWorkingDayInMoth: Int): Double {
        val res = super.calculateSalary(numOfWorkingDayInMoth)
        val commission = totalSales * commissionPercent
        return res + commission
    }
}