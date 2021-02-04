import java.util.*

//TODO - Main method
fun main(args: Array<String>) {
//    println("Hello World!")
//    operator fun String?.plus(other: Any?): String {}

    /*
     Index access Operator
     Bitwise Operation
     */

//        val number1: Int = 545344
//        val number2: Byte = number1.toByte()
//        println("number1 = $number1")
//        println("number2 = $number2")

//        kotlineDataTypes()
//        kotlineExpressionsAndStatements()
//        kotlinInputs()
//        kotlinExpression()
//        kotlinWhenExpression()
//        kotlinWhileLoop()
//        kotlinForLoop()
//        kotlinBreakExpression()
//        kotlinContinue()

    //TODO : Functions - Method call
//    val number1 = 12.2
//    val number2 = 3.4
//    val result: Int
//
//    result = addNumbers(number1, number2)
//    println("result = $result")
//
//    println("------------------")
//    var data = getDetails("Aisha", "Test", "Address", 34321345);
//    println(data)

    kotlinInfixFunctionCall()

}


//TODO : Data types
fun kotlineDataTypes(){

    println("-------------------------------------")
    println("========Kotlin Data Types============")

    //String
    var string = "This is a string"
    val stringDeclartion: String?
    stringDeclartion = "String initialization"

    println(string)
    println(stringDeclartion)

    //Int
    var number = 1;
    //Having "?" meaning, the variable can have a null value
    val numberDeclaration:Int?
    numberDeclaration = null

    println(number)
    println(numberDeclaration)

    //Short
    var shortValue = 127
    val shortDeclaration: Short
    shortDeclaration = -128

    println(shortValue)
    println(shortDeclaration)

    //Boolean
    var boolValue = false;
    val boolValueDeclaration: Boolean
    boolValueDeclaration = true;

    println(boolValue)
    println(boolValueDeclaration)
}

//TODO : Expressions and Statements
fun kotlineExpressionsAndStatements(){

    println("-------------------------------------")
    println("==Kotlin Expressions and Statements===")
    val a = 12
    val b = 13
    val max: Int

    max = if (a > b) a else b
    println("$max")

    val checkState = false
    val result:Boolean

    //If checkState is true then return false, otherwise, return true
    result = if(checkState) false else true
    println("$result")

    /*
    Statements are everything that make up a complete unit of execution. For example,

    val score = 90 + 25

    Here, 90 + 25 is an expression that returns 115, and val score = 9*5; is a statement.

    Expressions are part of statements.
     */
}

//TODO : Inputs
fun kotlinInputs() {
    println("-------------------------------------")
    println("========Kotlin Inputs============")

    print("Enter text: ")

    /*
    It's possible to take input as a string using readLine() function, and convert it to values of other data type (like Int) explicitly
     */
    val stringInput = readLine()!!
    println("You entered: $stringInput")

    /*
        If you want input of other data types, you can use Scanner object.

        For that, you need to import Scanner class from Java standard library using:

        import java.util.Scanner
        Then, you need to create Scannerobject from this class.

        val reader = Scanner(System.`in`)
        Now, the reader object is used to take input from the user.
     */
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    print("Enter a number: ")

    /*
    Here, reader object of Scanner class is created. Then,
     the nextInt() method is called which takes integer input from the user which is stored in variable integer.
     */
    // nextInt() reads the next integer from the keyboard
    var integer:Int = reader.nextInt()

    println("You entered: $integer")

}

//TODO : Expressions
fun kotlinExpression(){
    /*
         Unlike Java (and other many programming languages), if can be used an expression in Kotlin; it returns a value.

         Example:

        fun main(args: Array<String>) {

            val number = -10

            val result = if (number > 0) {
                "Positive number"
            } else {
                "Negative number"
            }

            println(result)
        }
     */

    val number = -10
    val result = if (number > 0) "Positive number" else "Negative number"
    println(result)
}

//TODO : When Expression
fun kotlinWhenExpression(){
    val a = 10
    val b = 5

    print("Enter an operator")

    //Enter the number via command line
    val operator = readLine()

    val result = when(operator){
        "*" -> a * b
        "-" -> a - b
        "/" -> a / b
        "+" -> a + b
        else -> "$operator is not valid"
    }

    println("result = $result")

    /*
            You can combine two or more branch conditions with a comma

             val n = -1

            when (n) {
                1, 2, 3 -> println("n is a positive integer less than 4.")
                0 -> println("n is zero")
                -1, -2 -> println("n is a negative integer greater than 3.")
            }
     */


    //Check value in range
    val range = 100

    when (range) {
        in 1..10 -> println("A positive number less than 11.")
        in 10..100 -> println("A positive number between 10 and 100 (inclusive)")
    }

    /*
    To check whether a value is of a particular type in runtime, we can use is and !is operator. For example,

            when (x) {
            is Int -> print(x + 1)
            is String -> print(x.length + 1)
            is IntArray -> print(x.sum())
            }
     */

    val ac = 11
    val n = "11"

    when (n) {
        "cat" -> println("Cat? Really?")
        12.toString() -> println("Close but not close enough.")
        ac.toString() -> println("Bingo! It's eleven.")
    }

    println(ac.toString())
}

//TODO : While loop
fun kotlinWhileLoop(){
    var sum = 0
    var i = 100

    while (i != 0) {
        sum += i     // sum = sum + i;
        --i
    }
    println("sum = $sum")

    var sum2: Int = 0
    var input: String

    do {
        print("Enter an integer: ")
        //"!!" is ensuring that the input is not null
        input = readLine()!!
        sum2 += input.toInt()

    } while (input != "0")

    println("sum = $sum2")
}

//TODO : For loop
fun kotlinForLoop(){
    /*
        The for loop in Kotlin iterates through anything that provides an iterator. In this article, you learn to create for loop (with the help of examples).

        There is no traditional for loop in Kotlin unlike Java and other languages.

        In Kotlin, for loop is used to iterate through ranges, arrays, maps and so on (anything that provides an iterator).

        The syntax of for loop in Kotlin is:

        for (item in collection) {
            // body of loop
        }
     */
    for (i in 1..5) {
        println(i)
    }

    //To decrement, instead of using ".." you need to use the keyword "downTo"
    for(i in 5 downTo 1) println(i)

    println("----------------------------")
    println("==Iterating through an array==")

    var language = arrayOf("Ruby", "Koltin", "Python", "Java")
    //Iterating through the entire array
//    for (item in language)
//        println(item)

    /*
    var languageResult:String
    //Iterating through the entire array
    for (item in language){
        //Checking if the item "Ruby" exists in the array
        if(item.equals("Ruby"))
            //If it does exist then  execute this line of code which prints that item
               println(item)
    }
    */

    for (item in language.indices) {
        // printing array elements having even index only
        if (item%2 == 0)
            println(language[item])
    }

    println("*********")
    //Iterating through a string
    var text= "Kotlin"

    for (letter in text) {
        println(letter)
    }

    var text2= "Kotlin"

    //indices?
    for (item in text2.indices) {
        println(text[item])
    }

}

//TODO : Break Expression
fun kotlinBreakExpression(){
//    for (i in 1..10) {
//        if (i == 5) {
//            //If the i is equals to 5 then it will exit out of the if statement
//            break
//        }
//        println(i)
//    }
//
//    println("---------------------------")

    //Keep entering number until the user enters the value 0
    /*
    var sum = 0
    var number: Int

    while (true) {
        print("Enter a number: ")
        number = readLine()!!.toInt()

        if (number == 0)
            break

        sum += number
    }

    println("sum = $sum")
     */

    println("-------------------------")
    /*
    Here, test@ is a label marked at the outer while loop. Now, by using break with a label (break@test in this case), you can break the specific loop.
     */

    first@ for (i in 1..4) {

        second@ for (j in 1..2) {
            println("i = $i; j = $j")

            if (i == 2)
                //Will exit out of the identifier e.g. @second
                break@first
        }
        println("First loop")
    }
}

//TODO : Continue keyword
fun kotlinContinue(){
    /*
        fun main(args: Array<String>) {

        for (i in 1..5) {
            println("$i Always printed.")
            if (i > 1 && i < 5) {
                continue
            }
            println("$i Not always printed.")
        }
        }

        When you run the program, the output will be:

        1 Always printed.
        1 Not always printed.
        2 Always printed.
        3 Always printed.
        4 Always printed.
        5 Always printed.
        5 Not always printed.

        ----------------------------
        What you have learned till now is unlabeled form of continue,
        which skips current iteration of the nearest enclosing loop.
        continue can also be used to skip the iteration of the desired loop (can be outer loop) by using continue labels.
     */

    var number: Int
    var sum = 0

    for (i in 1..6) {
        print("Enter an integer: ")
        number = readLine()!!.toInt()

        if (number <= 0)
            continue

        sum += number
    }
    println("sum = $sum")

}

class Test(){
    var accessAnotherClass = Test1()
}

class Test1(){

}

//TODO : Functions - Methods
fun kotlinFunctions(){
    /*
        In this article, you'll learn about functions; what functions are, its syntax and how to create a user-function in Kotlin.

        In programming, function is a group of related statements that perform a specific task.

        Functions are used to break a large program into smaller and modular chunks. For example,
         you need to create and color a circle based on input from the user. You can create two functions to solve this problem:

        createCircle() Function
        colorCircle() Function
        Dividing a complex program into smaller components makes our program more organized and manageable.

        Furthermore, it avoids repetition and makes code reusable.
     */
    var number = 5.5
    //print() is a Kotlin defined method, so is sqrt()
    print("Result = ${Math.sqrt(number)}")
}

fun addNumbers(n1: Double, n2: Double): Int {
    val sum = n1 + n2
    val sumInteger = sum.toInt()
    return sumInteger

    /*
    If the function doesn't return any value, its return type is Unit. It is optional to specify the return type in the function definition if the return type is Unit.
     */
}

    /*
        Here, the getName() function takes two String arguments, and returns a String.

        You can omit the curly braces { } of the function body and specify the body after = symbol if the function returns a single expression (like above example).

        It is optional to explicitly declare the return type in such case because the return type can be inferred by the compiler. In the above example, you can replace

        fun getName(firstName: String, lastName: String): String = "$firstName $lastName"

        with

        fun getName(firstName: String, lastName: String) = "$firstName $lastName"
    */
fun getName(firstName: String, lastName: String): String = "$firstName $lastName"

fun getDetails(firstName:String, lastName:String,address:String,phoneNumber:Int) = "$firstName $lastName $address $phoneNumber"

//TODO : Infix Function Call
fun kotlinInfixFunctionCall(){
    /*
    val a = true
    val b = false
    var result: Boolean

    result = a or b // a.or(b)
    println("result = $result")

    result = a and b // a.and(b)
    println("result = $result")
     */

    /*
        You can make a function call in Kotlin using infix notation if the function

        is a member function (or an extension function).
        has only one single parameter.
        is marked with infix keyword.
     */
    val p = Structure()
    p createPyramid 4
}

class Structure() {
    //infix?
    infix fun createPyramid(rows: Int) {
        var k = 0
        for (i in 1..rows) {
            k = 0
            for (space in 1..rows-i) {
                print("  ")
            }
            while (k != 2*i-1) {
                print("* ")
                ++k
            }
            println()
        }
    }
}

//TODO : Default and named arguments
fun kolinDefaultAndNamedArguments(){

}
