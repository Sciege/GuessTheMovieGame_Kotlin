import java.io.File


fun main() {
    // read file
    val file = File("movies.txt").readLines()
//    val random = Random
    val moviePicked = file.random()
    val playerGuess = mutableListOf<Char>()
    val wrongGuesses = mutableListOf<Char>()
    var guessedMovie = movieToBeGuess(moviePicked)

    var guessesLeft = 10

    println(moviePicked)
// make game loop

    while (true) {
        println("You are guessing: $guessedMovie")
        println("You have guessed (${wrongGuesses.size}) wrong letters: $wrongGuesses")
        // make input character

        // if true end
        if (guessesLeft <= 0) {
            println("You are out of guesses")
            break
        }
        val input = readln().lowercase()

        // dapat ang user input is equal to 1 ra
        // dapat letter ang ma input dili numbers or any special case
        // dapat dili empty
        if (input.isEmpty() || input.length != 1 || !input[0].isLetter()) {
            println("Invalid input please try again")
            continue
        }
        val char = input[0]
        if (moviePicked.contains(char)) {
            playerGuess.add(char)
        } else {
            wrongGuesses.add(char)
            guessesLeft--
        }

        guessedMovie = movieUpdate(moviePicked, playerGuess)

        if (guessedMovie == moviePicked){
            println("You guessed the movie right!")
            break
        }
    }
}

private fun movieToBeGuess(movie: String): String {
    return movie.replace(Regex("[a-zA-Z]"), "_")
}

//
private fun movieUpdate(movie: String, playerGuess: List<Char>): String {
    return buildString {
        for (i in movie) {
            if (i.isLetter() && playerGuess.contains(i.lowercaseChar())) {
                append(i)
            } else if (i.isWhitespace()) {
                append(' ')
            } else {
                append('_')
            }
        }
    }
}