# Functional Programming

*"Programming with Functions"*

Key idea for today's talk is the idea that we separate *Computation* from *Action*.

Computation: Given the program's input and current state, compute it's next state and
what actions it should take. The computation will be pure (no side effects, deterministic)

Action: *Do* the actions that have been computed. Cause external effects.
Don't perform computation however, just faithfuly follow the previous computed
actions.

Sometimes the piece that performs the actions is called the *interpreter*.