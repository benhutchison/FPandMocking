# Mocking

Mocking is a test technique that became prominent circa 2000-2008. 
It was initially associated with Object-oriented programming where it plays a valuable role.

Mocking addressed the problem that we want to break down big complicated systems into smaller
components & subsystems, and to test the system's behavior & interactions in
a local scope.

### Why are small tests desirable?

Isolating a System under Test (SUT) from its environment and collaborators
(here means: the other components of the system that the SUT interacts with)
means we can use simpler, smaller tests whose behavior we can reason about. 

It also lets us avoid combinatorial explosions that can occur when we try to
test a complex system composed of many components. 

For a simple example, consider a hypothetical program that can render colored shapes
onto the screen. Let there be three colors and three shapes

```scala mdoc
enum Shape:
    case Square, Circle, Triangle
    
enum Color: 
    case Red, Green, Blue
```

If we write an larger test that verifies the shape and the color is rendered correctly,
we end up having 3 x3 = 9 possible cases to test (or not).

If instead, we "divide and conquer" by testing shape rendering separately
from color rendering, we have 3 + 3 + 1 = 7 cases. (The one at the end
is an integration test that verifies shape and color rendering can be combined.)

Note how we avoid need to test the multiplicative _product_ of the cases,
and instead have to cover the _sum_ of all cases plus their composition.

### Mocking

The basic idea of mocking is that we replace the collaborators of the SUT
with mock objects ("mocks"). These mocks are special placeholder values typically
provided by a specialised mocking library. They are configured by the test author to 
resemble the real components behaviorally, as so far as the test is concerned. 

But there is one more characteristic that separates _mocks_ from _stubs_. Mocks
expect to be called, and they remember if they were actually invoked
during the test. At the end of the test, the mock collaborators verify they
were called with the expected parameters, the expected number of times,
and in the expected order.

Lets see this in action with [ScalaMock](https://scalamock.org/)  
```scala
"drawLine" should "interact with Turtle" in {
  val mockedTurtle = mock[Turtle]
 
  // Set expectations
  (mockedTurtle.setPosition _).expects(10.0, 10.0)
  (mockedTurtle.forward _).expects(5.0)
  (mockedTurtle.getPosition _).expects().returning(15.0, 10.0)
 
  // Exercise System Under Test
  drawLine(mockedTurtle, (10.0, 10.0), (15.0, 10.0))
}
```

### Why is Object-Orientation+Mocks so Common?

Objects, in the OO conception, are more than just values. They can have internal state and in the general case,
invoking their methods can cause arbitrary side-effects. Further,
their internal representation is often hidden, so putting them into a desired
state may be non-trivial.

This means we can't always setup the collaborators to our SUT in a desired state 

And we can't be confident that invoking collaborators is safe; It might change their state,
delete files, or launch missiles.

Given that its difficult and unsafe to create a suitable set of collaborators
for the SUT, mocks make an appealing alternative. They don't have side effects
and they don't internal state (relevant to the SUT at least).

I don't really know how you could test "classic" OO without mocks actually.



