# Doodle

*Doodle is a Scala library for 2D graphics, animation, data visualization, and creative coding.*

Doodle was designed in part as an example to support teaching functional programming. It separates:

- Most of the library is for composing data structures that describe the image or animation to be generated. All  
  operations are pure. The result of such composition has type `Image`.
- There are effectful interpreters called Backends that render the `Image` in a given environment. The standard one is
  based on Scala Swing but there are other that can generate SVG files for use in Scalajs web apps.

## Example

```scala mdoc:silent
import doodle.image.*
import doodle.core.*

val blackSquare = Image.rectangle(30, 30).fillColor(Color.black)
val redSquare = Image.rectangle(30, 30).fillColor(Color.red)

val twoByTwo = (redSquare.beside(blackSquare)).above(blackSquare.beside(redSquare))

val fourByFour = (twoByTwo.beside(twoByTwo)).above(twoByTwo.beside(twoByTwo))

val chessboard = (fourByFour.beside(fourByFour)).above(fourByFour.beside(fourByFour))
```

To run
```scala mdoc:compile-only
import doodle.image.syntax.all.*
import doodle.java2d.*
import cats.effect.unsafe.implicits.global

object main:
  @main def main() = chessboard.draw()
```