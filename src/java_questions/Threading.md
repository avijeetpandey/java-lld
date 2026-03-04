# Concurrency

Concurreny is all about managing multiple tasks at once, for example a chef can cook , cut vegetables and fry other things very quicky but at any exact given moment, he is doing one task at a time very quickly.

## Parallelism

Parallelism means executing multiple things at once, at the same time.

The way to achieve concurrency and parallelism in Java and they are
- Basic threads
- Via Executor framework
- CompletableFuture
- Via virtual threads ( Java21 )


## Basic threading 
In the older versions of java if some background task needs to be done and executed it was a manual process and we have to create threads and manage it manually, every JAVA thread is mapped directly to the OS level thread , which made them heavy and took lot of memory and time to startup.