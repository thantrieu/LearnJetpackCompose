package pro.branium.learnjetpackcompose.sampleoop

open class Employee : EmployeeAction {
    private var _empId: String = ""
    private var _fullName: String = ""
    private var _phone: String = ""
    private var _salary: Double = 0.0
    private var _workingDays: Int = 0

    val empId: String
        get() = _empId
    val fullName: String
        get() = _fullName
    val phone: String
        get() = _phone
    val salary: Double
        get() = _salary
    val workingDays: Int
        get() = _workingDays

    fun updateEmpId(empId: String) {
        _empId = empId
    }

    fun updateFullName(fullName: String) {
        _fullName = fullName
    }

    fun updatePhone(phone: String) {
        _phone = phone
    }

    fun updateSalary(salary: Double) {
        _salary = salary
    }

    fun updateWorkingDays(workingDays: Int) {
        _workingDays = workingDays
    }

    override fun checkin(): Long {
        println("Nhân viên checkin")
        return System.currentTimeMillis()
    }

    override fun checkout(): Long {
        println("Nhân viên checkout")
        return System.currentTimeMillis()
    }

    override fun calculateSalary(numOfWorkingDayInMoth: Int): Double {
        return salary / numOfWorkingDayInMoth * workingDays
    }

    override fun work() {
        println("Nhân viên làm việc")
    }
}