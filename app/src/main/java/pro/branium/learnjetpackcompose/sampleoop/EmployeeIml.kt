package pro.branium.learnjetpackcompose.sampleoop

val employees = mutableListOf<Employee>()

fun addNewEmployee() {
    // thêm mới nhân viên
    val emp = Employee()
    employees.add(emp)

    val leader = Leader(0.05f, 50000f)
    employees.add(leader)

    val director = Director(100000f, "CEO")
    employees.add(director)

    val partimeEmp = PartimeEmp("Morning", 100)
    employees.add(partimeEmp)
}

fun displayEmployeeList() {
    for (emp in employees) {
        println(emp.fullName)
    }
}

fun searchEmployee() {

}
/// .....