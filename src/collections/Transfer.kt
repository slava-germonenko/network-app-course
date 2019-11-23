package collections

class Transfer : Comparable<Transfer> {
    var ContractId: Int = 0
    var Customer: String = ""
    var Load: String = ""
    var LoadMass: Double = 0.0
    var Transport: String = ""
    var Cost: Int = 0

    override fun compareTo(other: Transfer): Int {
        return this.Cost.compareTo(other.Cost);
    }
}
