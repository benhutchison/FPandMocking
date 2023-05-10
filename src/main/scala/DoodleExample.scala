import doodle.image.*
import doodle.core.*

val blackSquare = Image.rectangle(30, 30).fillColor(Color.black)
val redSquare = Image.rectangle(30, 30).fillColor(Color.red)

val twoByTwo = (redSquare.beside(blackSquare)).above(blackSquare.beside(redSquare))

val fourByFour = (twoByTwo.beside(twoByTwo)).above(twoByTwo.beside(twoByTwo))

val chessboard = (fourByFour.beside(fourByFour)).above(fourByFour.beside(fourByFour))

import doodle.image.syntax.all.*
import doodle.java2d.*
import cats.effect.unsafe.implicits.global

@main def main() = chessboard.draw()