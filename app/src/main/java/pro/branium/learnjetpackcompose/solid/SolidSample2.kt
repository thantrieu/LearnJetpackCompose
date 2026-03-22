package pro.branium.learnjetpackcompose.solid

interface Vehicle {
    fun drive()
    fun start()
    fun stop()
    fun turnLeft()
    fun turnRight()
}

interface FuelVehicleAction {
    fun fuelUp() // đổ xăng
}

interface ElectricVehicleAction {
    fun charge() // sạc điện
}

class FuelVehicle : Vehicle, FuelVehicleAction { // xe xăng
    override fun drive() {
        TODO("Not yet implemented")
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun turnLeft() {
        TODO("Not yet implemented")
    }

    override fun turnRight() {
        TODO("Not yet implemented")
    }

    override fun fuelUp() {
        TODO("Not yet implemented")
    }

}

class ElectricVehicle : Vehicle, ElectricVehicleAction { // xe điện
    override fun drive() {
        TODO("Not yet implemented")
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun turnLeft() {
        TODO("Not yet implemented")
    }

    override fun turnRight() {
        TODO("Not yet implemented")
    }

    override fun charge() {
        TODO("Not yet implemented")
    }

}

interface Flyable { // có thể bay được
    fun flying()
}

interface Swimmable { // có thể bơi được
    fun swim()
}

open class FutureVehicle : Vehicle, Flyable, Swimmable  {
    override fun drive() {
        TODO("Not yet implemented")
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun turnLeft() {
        TODO("Not yet implemented")
    }

    override fun turnRight() {
        TODO("Not yet implemented")
    }

    override fun flying() {
        TODO("Not yet implemented")
    }

    override fun swim() {
        TODO("Not yet implemented")
    }

}

class FutureVehicleN : FutureVehicle() {

}

class HybridVehicle : Vehicle, FuelVehicleAction, ElectricVehicleAction { // xe xăng lai điện
    override fun drive() {
        TODO("Not yet implemented")
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun turnLeft() {
        TODO("Not yet implemented")
    }

    override fun turnRight() {
        TODO("Not yet implemented")
    }

    override fun fuelUp() {
        TODO("Not yet implemented")
    }

    override fun charge() {
        TODO("Not yet implemented")
    }

}