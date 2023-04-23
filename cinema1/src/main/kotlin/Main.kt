fun printRoom(rows: Int, seats: Int): MutableList<MutableList<String>>  {

    val room = mutableListOf<MutableList<String>>()
    val row = mutableListOf<String>(" ")

    for (i in 1..seats) {
        row.add(i.toString())
    }

    room.add(row)

    for (i in 1..rows){
        val r = mutableListOf<String>()
        r.add("$i")
        for (j in 0 until seats)
            r.add("S")
        room.add(r)
    }
    return room

}
fun cost(rows: Int, seats: Int, selectedRow: Int): Int {
    if (rows * seats <= 60) {
        return 10
    } else {
        return if (selectedRow > (rows/2)) 8 else 10
    }
}
fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
    var room = mutableListOf<MutableList<String>>()
    var selectedRow: Int = -1
    var selectedSeat: Int = -1
    var coutOfTicketsBought = 0
    var currIncome=0
    room = printRoom(rows, seats)

    while (true) {
        println(" ")
        print("1.Show the seats\n2.Buy a ticket\n3.Statistics\n0.Exit\n")
        val selection = readln().toInt()
        when (selection) {
            1 -> {
                if (selectedRow > 0 && selectedSeat > 0) {
                    println("Cinema:")
                    for (i in 0 until room.size) {
                        println(room[i].joinToString(" "))
                    }
                } else {
                    println("Cinema:")
                    for (i in 0 until room.size) {
                        println(room[i].joinToString(" "))
                    }
                }
            }

            2 -> {
                while (true) {
                    println("Enter a row number:")
                    selectedRow = readln().toInt()
                    println("Enter a seat number in that row:")
                    selectedSeat = readln().toInt()
                    try {
                        if (room[selectedRow][selectedSeat] == "B") {
                            println("That ticket has already been purchased!")
                        } else {
                            room[selectedRow][selectedSeat] = "B"
                            coutOfTicketsBought += 1

                            val cp = cost(rows, seats, selectedRow)
                            println("Ticket price: $$cp")
                            currIncome += cp
                            break
                        }
                    } catch (e: IndexOutOfBoundsException) {
                        println("Wrong Input")
                    }
                }
            }        3 -> {
            println("Number of purchased tickets:$coutOfTicketsBought")
            var totalSeats=rows*seats
            var totalIncome = 0
            if(rows*seats<=60){
                totalIncome=totalSeats*10
            }
            else{
                var topRow=rows/2
                var bottomRow= rows-topRow
                var topRowIncome=(topRow*seats)*10
                var bottomRowIncome=(bottomRow*seats)*8
                totalIncome=topRowIncome+bottomRowIncome

            }
            var perct = (coutOfTicketsBought.toFloat()/totalSeats)*100
            val formatPercentage = "%.2f".format(perct)
            println("Percentage:$formatPercentage%")
            println("Current Income:$$currIncome")
            println("Total income:$$totalIncome")


        }

            0 -> {
                break
            }

        }
    }
}