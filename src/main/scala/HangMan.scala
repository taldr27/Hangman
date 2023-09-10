import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn
import scala.util.Random

val words = List("elbow", "writer", "circle", "polish", "bridge", "store", "fang", "scarecrow", "show", "jeans", "wilderness", "attempt", "waxing", "aftermath", "banana", "wrist", "wheel", "spring", "cherries", "nerve")
var word = ""
val guesses = ArrayBuffer[Char]()
var remainingGuesses = 6
var mistakes = 0

@main
def hangman() =
  setupGame()

def setupGame() =
  val wordIndex = Random().nextInt(words.size)
  word = words(wordIndex).toUpperCase()
  println(word)

  for (i <- word.indices)
    guesses.addOne('_')

  var gameOver = false

  while (!gameOver)
    printGameStatus()
    println("Please enter a letter:")
    val input = StdIn.readLine()

    if (input.isEmpty)
      println("That's an invalid input. Please try again!")
    else
      val letter = input.charAt(0).toUpper
      if (word.contains(letter))
        for (i <- word.indices)
          if (word.charAt(i) == letter)
            guesses(i) = letter
        if (!guesses.contains('_'))
          gameOver = true
      else
        println("Sorry, that's not part of the word")
        remainingGuesses -= 1
        mistakes += 1
        if (mistakes == 6)
          gameOver = true

  if (mistakes == 6)
    printGameStatus()
    println(s"Sorry, you have lost. The word was $word")
  else
    println("Congratulations, you win!")
    println(s"The word was $word")


def printGameStatus() =
  mistakes match
    case 0 => print0Mistakes()
    case 1 => print1Mistake()
    case 2 => print2Mistakes()
    case 3 => print3Mistakes()
    case 4 => print4Mistakes()
    case 5 => print5Mistakes()
    case 6 => print6Mistakes()

  print("Word: ")
  for (element <- guesses)
    print(s"$element ")

  println(s"\nYou have $remainingGuesses guess(es) left")

def print0Mistakes() =
  println("  |------|-")
  println("  |      |")
  println("  |       ")
  println("  |")
  println("  |")
  println("  |")
  println(" /|\\")
  println("/ | \\")

def print1Mistake() =
  println("  |------|-")
  println("  |      |")
  println("  |      O")
  println("  |")
  println("  |")
  println("  |")
  println(" /|\\")
  println("/ | \\")

def print2Mistakes() =
  println("  |------|-")
  println("  |      |")
  println("  |      O")
  println("  |      |")
  println("  |      |")
  println("  |")
  println(" /|\\")
  println("/ | \\")

def print3Mistakes() =
  println("  |------|-")
  println("  |      |")
  println("  |      O")
  println("  |     /|")
  println("  |      |")
  println("  |")
  println(" /|\\")
  println("/ | \\")

def print4Mistakes() =
  println("  |------|-")
  println("  |      |")
  println("  |      O")
  println("  |     /|\\")
  println("  |      |")
  println("  |")
  println(" /|\\")
  println("/ | \\")

def print5Mistakes() =
  println("  |------|-")
  println("  |      |")
  println("  |      O")
  println("  |     /|\\")
  println("  |      |")
  println("  |     /")
  println(" /|\\")
  println("/ | \\")

def print6Mistakes() =
  println("  |------|-")
  println("  |      |")
  println("  |      O")
  println("  |     /|\\")
  println("  |      |")
  println("  |     / \\")
  println(" /|\\")
  println("/ | \\")