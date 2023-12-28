Welcome to the sample project of JAVA concepts

1) Functional Interfaces
2) Lambdas
3) Streams

Difference bw Map & FlatMap
Map does a one-to-one transformation
FlatMap does a one-to-many transformation

To check if element exists in a stream, use -
anyMatch()
allMatch()
noneMatch()

Stream does not have an order
It also depends on the source you create it from
When streams are created from HashSet, we get an unordered stream.
Hence, findFirst() does not hold meaning and will be same as findAny()
when created from ArrayList ==> we get Ordered Stream

Collection as a Reduction
Collection = Mutable Reduction
A collector is an object that provides a set of functions necessary for a specific collection operation
Collection operation can reduce a stream into a mutable result container
In Streams API, there is an interface called Collector which defines below 
Ex: using collect() function
collect() contains 3 arguments=> supplier(creates),accumulator and a combiner(helps in parallel streaming)
Collector also has a 4th function called finisher which is called as the last step of the collection operation 
after combiner has been called to combine all the intermediate results

Examples of Collectors: toList(), toSet(), toMap()

Using collect helps refactor and reduce code in scenarios where there is a lot of repetitive code

