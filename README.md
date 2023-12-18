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
Hence findFirst() does not hold meaning and will be same as findAny()
when created from ArrayList ==> we get Ordered Stream


