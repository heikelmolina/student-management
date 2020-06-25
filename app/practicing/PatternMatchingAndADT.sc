//////////////////////////////////
//First example

case class IceCream(flavour: String)

def workingOn(iceCream: Option[IceCream]) = {
  iceCream match {
    //que pasa si me vino algo
    case Some(iceCream) => println("The ice cream machine is making ice cream of " + iceCream.flavour)
    //que pasa vino nada
    case None => println("Nothing")
    //que pasa en cualquier otro caaso
    case _ => println("The machine is not working")
  }
}


//Inputs
//cambiar el null por el none
val nothing = null
val iceCream = Option(IceCream("pineaple"))

println("------------------")
println("Ice Cream Machine")
println("------------------")

workingOn(iceCream)
workingOn(nothing)







//////////////////////////////////
//Second example

trait Product

case class IceCreams(flavour: String) extends Product
case class McDonalds(amount: Int, size: String) extends Product

def whatIsDoing(product: Option[Product]) =
  product match {
    case Some(IceCreams(_)) => println("The machine is making Ice Cream")
    case Some(McDonalds(amount, size)) => println("The machine is making " + amount + " " + size + " Mc Donalds")
    case _ => println("The machine is not working")

  }

//Inputs
val ice = Option(IceCreams("pineaple"))
val mc = Option(McDonalds(3, "big"))

println("------------------")
println("Another Machine")
println("------------------")

whatIsDoing(ice)
whatIsDoing(mc)
whatIsDoing(nothing)











//////////////////////////////////
//Third example

case class Content(contentName: String)

case class Cup(content: Option[Content])

def inTheCup(something: Cup) = {
  something match {
    case Cup(Some(Content(x))) => println("There is " + x +" in the cup")
    //quitar estos null
    case Cup(null) => println("There is nothing in the cup")
    case null => println("The cup exist?")
    case _ => println("We dont know what is in the cup")
  }
}

//Inputs
val nullCup = Cup(null)
val milk = Cup(Option(Content("milk")))
val coffe = Cup(Option(Content("coffe")))


println("------------------")
println("The Cup example")
println("------------------")

inTheCup(coffe)
inTheCup(milk)
inTheCup(nullCup)
inTheCup(nothing)

