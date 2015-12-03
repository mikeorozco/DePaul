/******************************************************************/

object Values {
  val b1 = true
  val b2 = false

  val b3 = !b2
  val b4 = b1 && b2 
  val b5 = b1 || b2 

  val n1 = 2
  val n2 = 3

  val n3 = n1 + n2
  val n4 = n1 - n2
  val n5 = n1 * n2
  val n6 = n1 / n2

  /* Character literals are delimited by an apostrophe. */
  val c1 = 'h'
  val c2 = 'e'
  val c3 = 'e'

  /* String literals are delimited by double quotes. */
  val s1 = "hello"
  val s2 = "world"

  /* The string concatenation function is written infix as "+". */
  val s3 = s1 + " " + s2
}

/******************************************************************/

object Tuples {
  /* Pairs are created using parentheses. */

  /* They may have the same type. */
  val pair1 = (3, 5)
  val pair2 = ("hello", "world")

  /* Or different types. */
  val pair3 = (3, "hello")
  val pair4 = ("hello", 3)

  /* More generally, we can create tuples using the same syntax. */
  val tuple1 = pair1
  val tuple2 = (3, 5, 3, "hello")

  /* Tuples can nest. */
  val nested1 = ((3, 5), ("hello", "world"))
  val nested2 = (pair1, pair2)

  /* Now test equality of nested1 and nested2. */
  val equal12 = nested1 == nested2

  /* The nesting is significant.  tuple2 has a different type to nested1
   * and nested2.  Try running "tuple2 == nested1" at the 
   * command line to see that they are distinct values.
   */
}


/******************************************************************/

object Lists {
  val emptylist = Nil
  /* The following yields the same result as emptylist. */
  val emptylist2 = List ()  
  val singletonlist1 = List (5)
  val singletonlist2 = List ('a')
  val singletonlist3 = List ("hello")

  /* Every object in a list must have the same type. */
  val list1 = List (5, 4, 3, 2, 1)
  val list2 = List (1, 2, 3, 4, 5)

  /* Functions/methods such as "reverse", concatenation/append ":::", and cons "::" can operate on
   * lists.
   */
  val list3 = list2.reverse
  val list4 = list1 ::: list2 /* ::: - joins two lists */
  val list5 = 29 :: list2     /* ::  - joins a list element and a list */
  val list6 = 1 :: 2 :: 3 :: 4 :: 5 :: Nil

  /* Lists can be compared for equality, and equality is based upon
   * having the same elements.  It is not possible to create
   * (observably) distinct lists with the same elements.
   */

  val equal12a = list1 == list2
  val equal12b = list1 == list3

  val equal = list2 == (1 to 5).toList

  /* SUMMARY:
   * * TUPLES: the type specifies the length, and individual components
   *   may have different types.
   *
   * * LISTS: the length is unbounded (and may even be infinite!), and
   *   individual components must have the same type.
   *
   * Tuples, lists, strings, etc., are all immutable.  You cannot
   * change them at all.
   */
}

/******************************************************************/

object BasicFunc {
  /* Some simple function definitions. */

  /* Define a function "add1" that has an Int parameter x and returns an
   * Int. */
  def add1 (x:Int):Int = x + 1

  /* NOTE: no "return" keyword is necessary. */

  /* Now try running the function and bind the results. */
  val result1a = add1 (5)
  val result1b = add1 (add1 (5))

  /* In fact, Scala will infer the return type for you, so you do not need to
   * add it explicitly.  Opinions differ about whether it is worthwhile making
   * explicit type definitions versus leaving inference to the compiler.
   */
  def add1b (x:Int) = x + 1

  /* Functions may take more than one argument. */

  /* For example, a function that takes two Int arguments and returns an
   * Int argument:
   */
  def add2 (x:Int, y:Int) = x + y

  val result2a = add2 (5, 7)
  val result2b = add2 (5, (add1 (7)))

  /* Now define functions for list "cons" and list "concatentation".
   * The type for a list of Int is written "List[Int]".
   */
  def cons (x:Int, xs:List[Int]) = x :: xs
  def append (xs:List[Int], ys:List[Int]) = xs ::: ys

  /* Naming convention: use "xs" for a list; "x" for a typical element
   * of "xs".
   */
  val resultlist1 = cons (5, List (6,7,8))
  val resultlist2 = append (List (1,2,3), List (4,5,6))

  /* Normally, we would not define functions called "myconcat" and
   * "mycons".  We just use the builtins ":::" and "::" directly.  However,
   * the builtins are pronounced "concat" and "cons".
   */

  /* Operations can return tuples as well as lists. */
  def makepair (x:Int, y:String) : (Int, String) = (x, y)

  val resultpair1 = makepair (5, "hello")


  /* Conditional expressions are allowed. */
  def isZero1 (n:Int) : Boolean = if (n == 0) true else false
  def isZero2 (n:Int) : Boolean = n == 0  
}

/******************************************************************/

object Immutable {
  /* Declarations with "val" are immutable.  Declarations with "var" are mutable. */
  val immutable = 10
  var mutable = 10

  /* The following line is not permitted by the compiler. */
  /* def setImmutable (n:Int) : Unit = immutable = 10 */  

  /* But this is OK. */
  def setMutable   (n:Int) : Unit = mutable = 10
}

/******************************************************************/

object CompoundExpr {
  /* A compound expression is a sequence of declarations and 
   * expressions inside curly braces.  The value of the final
   * expression is the value of the compound expression.
   *
   * Semicolons for terminating expressions on different lines are OPTIONAL.
   * Semicolons for terminating expressions on the same line are MANDATORY.
   */

  def test1 () : Int = {
    1
    2
    3
  }

  def test2 (n:Int) : Int = {
    1
    2
    n+3
  }

  def test3 (n:Int) : Int = { 1; 2; n+3 }

  def test4 (n:Int) : Int = { 
    val x = 1 
    val y = 2 
    n+x+y
  }

  def test5 (n:Int) = {
    def foo (m:Int) = m * 2
    List (foo (n), foo (n+1), foo(n+2))
  }

  def quadruple (n:Int) = {
    val double = n * n
    double * double
  }
}

/******************************************************************/

object Recursion {
  object Direct {
    /* Recursive functions can be defined.  Recursive functions must
     * have their type declared explicitly.
     */
    def factorial  (n:Int) : Int = if (n <= 0) 1 else n * factorial (n - 1)
    def triangular (n:Int) : Int = if (n <= 0) 0 else n + triangular (n - 1)
  }

  object HigherOrder {
    /* The factorial and triangular functions are very similar.
     * We can define a higher-order function "iterate" that captures the
     * similarity.  It is a higher-order function because it accepts a
     * function parameter "f:(Int,Int)=>Int".  The type "(Int,Int)=>Int"
     * is the type of functions that take two Int arguments and return an Int.
     */
    def iterate (n:Int, v:Int, f:(Int,Int)=>Int) : Int = if (n <= 0) v else f(n, iterate (n - 1, v, f))

    /* Now, factorial and triangular are defined in terms of iterate.
     * The syntax "(x,y) => x*y" is for an anonymous function that takes
     * two arguments and then multiplies them.
     */
    def factorial  (n:Int) : Int = iterate (n, 1, (x,y) => x*y)
    def triangular (n:Int) : Int = iterate (n, 0, (x,y) => x+y)
  }

  object HigherOrderNested {
    /* It is cumbersome to pass the same arguments v and f when we
     * call iterate recursively.  This passing can be eliminated by
     * defining a nested function that is in the scope of the original
     * v and f parameters.
     */
    def iterate (n:Int, v:Int, f:(Int,Int)=>Int) : Int = {
      def aux (m:Int) : Int = if (m <= 0) v else f(m, aux (m - 1))
      aux (n)
    }

    def factorial  (n:Int) = iterate (n, 1, (x,y) => x*y)
    def triangular (n:Int) = iterate (n, 0, (x,y) => x+y)
  }

  object Lists {
    /* Some simple functions to create lists. */

    def upto (start:Int, end:Int) : List[Int] = 
      if (start > end) 
        Nil
      else 
        start :: upto (start + 1, end)

    /* Has generic polymorphism (more on this later on in the course).
     * EXERCISE: write a version of "repeat" that operates on Int elements only.
     */
    def repeat [X] (element:X, repetitions:Int) : List[X] = {
      if (repetitions == 0)Nil
      else element :: repeat (element, repetitions - 1)
    }
  }
}

/******************************************************************/

object Patterns {
  /* Patterns allow:
   * 1. Binding components of a composite object.
   * 2. Branching for different cases.
   */

  object Tuples {
    /* Get the first component of a pair. */
    def fst [X,Y] (p:(X, Y)) : X = p match {
      case (x, y) => x
    }

    /* Get the second component of a pair. */
    def snd [X,Y] (p:(X, Y)) : Y = p match {
      case (x, y) => y
    }

    /* NOTE: variable binder that have no bound occurrences can be
     * replaced by an underscore "_".
     * */

    /* EXERCISE: write version of "fst" and "snd" that operate on specific types only. */

    val pair = ("hello", 50)

    val result1 = fst (pair)
    val result2 = snd (pair)

    /* There are builtin functions/methods on pairs to extract the first
     * and second elements (more generally, components of a tuple).
     */
    val result3 = pair._1
    val result4 = pair._2
  }


  object Lists {
    /* A function to test whether a list is empty. */
    def isEmpty [X] (xs:List[X]) : Boolean = xs match {
      case Nil => true
      case y :: ys => false
    }

    def head [X] (xs:List[X]) : X = xs match {
      case Nil => throw new NoSuchElementException ()
      case y :: ys => y
    }

    def tail [X] (xs:List[X]) : List[X] = xs match {
      case Nil => throw new NoSuchElementException ()
      case y :: ys => ys
    }
      
    val list = List (1, 2, 3, 4, 5)

    val result1 = isEmpty (list)
    val result2 = head (list)
    val result3 = tail (list)

    /* There are builtin functions/methods on lists.*/
    val result4 = list.head
    val result5 = list.tail
  }
}

/******************************************************************/

object ListFunc {
  /* Recursive functions can operate on lists.  Pattern matching is used
   * to consider the empty and non-empty cases (and bind the head and
   * tail of the list in the non-empty case).
   */

  def length (xs:List[Int]) : Int = xs match {
    case Nil => 0
    case y :: ys => 1 + length (ys)
  }

  /* "sum" calculates the sum of all numbers in a list. */
  def sum (xs:List[Int]) : Int = xs match {
    case Nil => 0
    case y :: ys => y + sum (ys)
  }

  /* "product" calculates the product of all numbers in a list. */
  def product (xs:List[Int]) : Int = xs match {
    case Nil => 1
    case y :: ys => y * product (ys)
  }

  /* Find the maximum number in a non-empty list. */
  def max (xs:List[Int]) : Int = {
    def aux (currentMax:Int, ys:List[Int]) : Int = ys match {
      case Nil => currentMax
      case y :: ys => {
        val newMax = if (y > currentMax) y else currentMax
        aux (newMax, ys)
      }
    }    
    aux (xs.head, xs.tail)
  }

  /* Consider list concatenation.  There are two different situations.
   *
   * A. Concatenate two lists List (1,2,3) and List (4,5,6) into the list
   *    List (1,2,3,4,5,6).  This is done by the builtin function ::: .
   *
   * For example:
   */

  val resultlist1 = List (1,2,3) ::: List (4,5,6)

  /* B. Flatten a list of lists List (List (1,2,3), List (4), List (5,6)) into
   *    the list List (1,2,3,4,5,6).  This is done by the builtin function/method
   *    "flatten".
   *
   * For example:
   */

  val list2 = List (List (1,2,3), List (4), List (5,6))
  val resultlist2 = list2.flatten[Int]
  
  /* Define our own versions. */
  def append [X] (xs:List[X], ys:List[X]) : List[X] = xs match {
    case Nil => ys
    case z :: zs => z :: append (zs, ys)
  }

  def flatten [X] (xss:List[List[X]]) : List[X] = xss match {
    case Nil   => Nil
    case ys :: yss => ys ::: flatten (yss)
  }
    
  val resultlist3 = append (List (1,2,3), List (4,5,6))
  val resultlist4 = flatten (list2)


  /* The Scala library includes many other functions on lists that are common.
   * Below we define our own versions of these functions for pedagogical purposes,
   * but normally the library versions would be used instead.
   */

  /* The "take" function takes the first n elements of a list. */
  def take [X] (n:Int, xs:List[X]) : List[X] = 
    if (n <= 0) 
      Nil
    else
      xs match {
        case Nil => Nil
        case y :: ys => y :: take (n - 1, ys)
      }

  /* The "drop" function drop the first n elements of a list. */
  def drop [X] (n:Int, xs:List[X]) : List[X] = 
    if (n <= 0) 
      xs
    else
      xs match {
        case Nil => Nil
        case y :: ys => drop (n - 1, ys)
      }
  
  val takeresult = take (3, resultlist4)
  val dropresult = drop (3, resultlist4)


  /* Reverse a list.  This version is straightforward, but inefficient.  Revisited later on. */
  def reverse [X] (xs:List[X]) : List[X] = xs match {
    case Nil => Nil
    case y :: ys => reverse (ys) ::: List (y)
  }

  /* zip takes two lists and creates a single list containing pairs from the two lists
   * that occur at the same position.  The definition shows more sophisticated pattern
   * matching: the use of wildcards; overlapping patterns; and decomposing pairs and
   * lists simultaneously.  Note that the "xs" and "ys" in the third case shadow the
   * "xs" and "ys" parameters to the function.
   */
  def zip [X,Y] (xs:List[X], ys:List[Y]) : List[(X,Y)] = (xs, ys) match {
    case (Nil, _)       => Nil
    case (_, Nil)       => Nil
    case (x :: xs, y :: ys) => (x, y) :: zip (xs, ys)
  }

  val ziplist = zip (List (1, 2, 3), List ("the", "rain", "in", "spain"))

  /* unzip decomposes a list of pairs into a pair of lists.
   * The recursive case illustrates pattern-matching the result of the
   * recursive call in order to apply an operation to the elements.
   */
  def unzip [X,Y] (ps:List[(X,Y)]) : (List[X], List[Y]) = ps match {
    case Nil      => (Nil, Nil)
    case (x, y) :: qs => {
      val (xs, ys) = unzip (qs)
      (x :: xs, y :: ys)
    }
  }

  val unziplist = unzip (ziplist)
}

/******************************************************************/

object StringLists {
  /* Some examples to illustrate working with lists of strings. */

  /* Concatenate a list of strings together, inserting spaces between
   * each word.  Here we use a pattern that picks off two elements of a list.
   */
  def concatenateStrings (xs:List[String]) : String = xs match {
    case Nil          => ""
    case List (x)     => x
    case x :: y :: xs => x + " " + concatenateStrings (y :: xs)
  }

  val wordlist = List ("The", "rain", "in", "Spain", "falls", "mainly", "on", "the", "plain")
  val results1 = concatenateStrings (wordlist)

  /* Now generalize this function to allow a different choice of
   * separator in between words.  A nested function definition is used
   * to avoid passing the separator (but this is a matter of taste).
   */
  def concatenateStrings2 (xs:List[String], separator:String) : String = {
    def aux (xs:List[String]) : String = xs match {
      case Nil          => ""
      case List (x)     => x
      case x :: y :: xs => x + separator + aux (y :: xs)
    }
    aux (xs)
  }

  val results2 = concatenateStrings2 (wordlist, " ")
  val results3 = concatenateStrings2 (wordlist, ",")

  /* Generalize once more to allow begin and end strings. */
  def concatenateStrings3 (xs:List[String], begin:String, end:String, separator:String) : String = {
    def aux (xs:List[String]) : String = xs match {
      case Nil          => ""
      case List (x)     => x
      case x :: y :: xs => x + separator + aux (y :: xs)
    }
    begin + aux (xs) + end
  }

  val results4 = concatenateStrings3 (wordlist, "{", "}", ",")
  val results5 = concatenateStrings3 (Nil, "{", "}", ",")
  val results6 = concatenateStrings3 (List ("rain"), "{", "}", ",")
  val results7 = concatenateStrings3 (wordlist, "", "", "\n")
}

/******************************************************************/

object HigherOrder {
  /* Higher-order functions accept functions as arguments.
   * Higher-order refers to the "order" of the type of the function.
   */

  /* The "filter" function is a higher-order function.  It accepts a
   * predicate "p" and a list.  The predicate "p" is a function that
   * takes an element of the list and returns a Boolean.  The filter
   * function returns a new list that contains only the elements of
   * the original list where "p" was true (recall that lists are immutable).
   * The definition below illustrates the use guards on patterns.
   */
  def filter [X] (xs:List[X], p:X=>Boolean) : List[X] = xs match {
    case Nil                  => Nil
    case (x :: xs) if (p (x)) => x :: (filter (xs, p))
    case (x :: xs)            => filter (xs, p)
  }

  /* Passing a named function to filter. */
  def isEven (n:Int) = (n % 2) == 0
  val list1:List[Int] = List (0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val results1 = filter (list1, isEven)
  
  /* Passing an anonymous function to filter. */
  val results2 = filter (list1, (n:Int) => (n % 2) == 0)

  /* Anonymous functions are often called lambda abstractions.
   *
   * Inner classes in Java are closely related to anonymous functions.
   *
   * C# 3 has anonymous functions.
   */

  /* A string can be converted to a list of characters, then filter can
   * be applied on a per-character basis.
   */
  val list2:List[Char] = "I!like!exclamation!marks!".toList
  val results3 = filter (list2, (c:Char) => c != '!')
  /* NOTE: try this in Java for comparison. */

  
  /* The "exists" function is similar to "filter", but returns true iff
   * there is an element x in the list such that p(x) is true.
   */
  def exists [X] (xs:List[X], p:X=>Boolean) : Boolean = xs match {
    case Nil       => false
    case (x :: xs) => p (x) || exists (xs, p)
  }
  /* EXERCISE: write and test a "forall" function. */


  /* The "map" function applies a function to each element of a list.
   * A new list is constructed for the result (recall that lists are immutable).
   *
   * For example, given a list -  xs = List (x1, x2, x3, x4, x5, ...)
   *
   * The result of -              map (xs, f)
   *
   * Is -                         List (f (x1), f (x2), f (x3), f (x4), f (x5), ...)
   */
  def map [X,Y] (xs:List[X], f:X=>Y) : List[Y] = xs match {
    case Nil     => Nil
    case y :: ys => f (y) :: map (ys, f)
  }

  val resultmap1 = map (list1, isEven)

  val resultmap2    = map    (list1, (n:Int) => n <= 5)
  val resultfilter2 = filter (list1, (n:Int) => n <= 5)

  val resultmap3 = map (list1, (n:Int) => n + 10)

  import Recursion.Direct.{factorial,triangular}
  val resultmap4a = map (list1, (n:Int) => factorial (n))
  val resultmap4b = map (list1, (n:Int) => (n, factorial (n)))
  val resultmap5 = map (list1, (n:Int) => triangular (n))

  /* rot13 is a substitution cipher that advances characters by 13 places
   * in the Roman alphabet, wrapping around from Z to A.
   * 
   * See http://en.wikipedia.org/wiki/Rot13.
   *
   * First, define rot13Char for a single character.  Then, map rot13Char onto each
   * character in a list (converting from and to a string).
   */
  def rot13Char (c:Char) : Char = 
    if ('a' <= c && c <= 'z')
      ((c - 'a' + 13) % 26 + 'a').toChar
    else if ('A' <= c && c <= 'Z')
      ((c - 'A' + 13) % 26 + 'A').toChar
    else 
      c
  def stringToCharList (s:String) : List[Char] = s.toList
  def charListToString (cs:List[Char]) : String = new String (cs.toArray)
  def rot13 (s:String) = charListToString (map (stringToCharList (s), rot13Char))

  val resultmap6:String = rot13 ("The rain in Spain falls mainly on the plain.")

  /* Apply rot13 to each String in a list of Strings. */
  val list3:List[String] = List ("The", "rain", "in", "Spain", "falls", "mainly", "on", "the", "plain")
  val resultmap7 = map (list3, rot13)

  /* Find the length of each String in a list of Strings. */
  val resultmap8 = map (list3, (s:String) => s.length)

  /* The List class in Scala has "filter", "map", etc. as builtin methods.  */


   def time [X] (op:()=>X) : X = {
    val start = System.currentTimeMillis ()
    val result = op ()
    val end = System.currentTimeMillis ()
    //format ("Operation took: %d milliseconds%n", end - start)
    result
  }
}

/******************************************************************/

object Folds {
  /* Fold operators are higher-order functions that iterate over the
   * elements of a list, calculating a result using a function argument.
   */

  /* There are two fold operators.
   *
   * "foldl" groups operations to the left.
   *
   * "foldr" groups operations to the right.
   */

  def foldl [X,Y] (xs:List[Y], f:(X,Y)=>X, initial:X) : X = xs match {
    case Nil     => initial
    case y :: ys => foldl (ys, f, f (initial, y))
  }

  def foldr [X,Y] (xs:List[X], f:(X,Y)=>Y, initial:Y) : Y = xs match {
    case Nil     => initial
    case y :: ys => f (y, foldr (ys, f, initial))
  }

  /* INTUITION
   * 
   * foldl (List (x1,x2,...,x{n-1}, x{n}), +, initial)   =   (((initial + x1) + x2 ) ... + x{n-1}) + x{n}
   *
   * foldr (List (x1,x2,...,x{n-1}, x{n}), +, initial)   =   x1 + (x2 + ... + (x{n-1} + (x{n} + initial)))
   */

  /* With these definitions, we can redefine prior operations that were
   * defined recursively (so the fold operators are "combinators" for
   * defining recursive functions).
   */

  def suml (xs:List[Int]) = foldl (xs, (x:Int,y:Int) => x+y, 0)
  def sumr (xs:List[Int]) = foldr (xs, (x:Int,y:Int) => x+y, 0)

  def productl (xs:List[Int]) = foldl (xs, (x:Int,y:Int) => x*y, 1)
  def productr (xs:List[Int]) = foldr (xs, (x:Int,y:Int) => x*y, 1)

  def length [X] (xs:List[X]) : Int = foldr (xs, (_:X,n:Int) => 1+n, 0)

  def append [X] (xs:List[X], ys:List[X]) : List[X] = foldr (xs, (x:X,zs:List[X]) => x::zs, ys)

  def flatten [X] (xss:List[List[X]]) : List[X] = foldr (xss, (xs:List[X],ys:List[X]) => xs:::ys, Nil)

  /* Lists are lightweight objects, so one might even use them to
   * define a factorial function quickly.
   */
  import Recursion.Lists.upto
  def factorial (n:Int) = productr (upto (1, n))
}

/******************************************************************/

object Sorting {
  /* A merge sort and a quick sort. */

  def mergesort [X] (xs:List[X], lt:(X,X)=>Boolean) : List[X] = {
    def insert (x:X, ys:List[X]) : List[X] = ys match {
      case Nil => List (x)
      case z :: zs =>
        if (lt (z, x)) 
          z :: (insert (x, zs)) 
        else
          x :: z :: zs
    }
    def aux (ys:List[X]) : List[X] = ys match {
      case Nil => Nil
      case z :: zs => insert (z, aux (ys))
    }
    aux (xs)
  }

  def qsort [X] (xs:List[X], lt:(X,X)=>Boolean) : List[X] = {
    def aux (ys:List[X]) : List[X] = ys match {
      case Nil => Nil
      case z :: zs => aux (zs.filter (lt (_, z))) ::: List (z) ::: aux (zs.filter (!lt (_, z)))
    }
    aux (xs)
  }

  val listtosort = List (4,8,2,3,10,5,6,1,9,7)
  def mergesortresult = mergesort (listtosort, (x:Int, y:Int) => x < y)
  def qsortresult =     qsort     (listtosort, (x:Int, y:Int) => x < y)
}

/******************************************************************/

object TailRecursion {
  /* Tail recursion is fast in most functional programming languages,
   * but not supported well in many other languages.  .NET is an
   * exception, because functional programmers from Microsoft Research
   * influenced the design of the .NET instruction language.
   */

  /* The following two functions for reversing
   * a list differ in their speed not because one has a tail call and
   * the other doesn't, but because one has quadratic time (using
   * "++[x]" is linear in the size of the list) and the other doesn't.
   */

  /* Not tail recursive. */
  def reverseSlow [X] (xs:List[X]) : List[X] = xs match {
    case Nil => Nil
    case y :: ys => reverseSlow (ys) ::: List (y)
  }

  /* Tail recursive. */
  def reverseFast [X] (xs:List[X]) : List[X] = {
    def aux (xs:List[X], result:List[X]) : List[X] = xs match {
      case Nil => result
      case y :: ys => aux (ys, y :: result)

    }
    aux (xs, Nil)
  }

  /* The non tail-recursive definition of "upto" from Recursion.Lists.upto
   * has a stack overflow on the JVM for large lists, so use Scala's "to"
   * to create a large list.
   */
  val list1 = (1 to 10000).toList
  
  def slow () = reverseSlow (list1)
  def fast () = reverseFast (list1)

  import HigherOrder.time
  def timeReverseFast (size:Int) {
    val list = (1 to size).toList
    time (() => reverseFast (list))
  }

  /* Tail recursion is also used when simulating while loops using
   * accumulating parameters.
   */

  /* Non-tail recursive version of length.
   * Experiment to see when it fails.
   */
  def length [X] (xs:List[X]) : Int = xs match {
    case Nil => 0
    case y :: ys => 1 + length (ys)
  }

  def lengthTail [X] (xs:List[X]) : Int = {
    def aux (result:Int, xs:List[X]) : Int = xs match {
      case Nil => result
      case y :: ys => aux (result + 1, ys)
    }
    aux (0, xs)
  }

  def lengthImperative [X] (xs:List[X]) : Int = {
    var ys = xs
    var result = 0
    var done = false
    while (!done) {
      ys match {
        case Nil => done = true
        case _ :: zs => result += 1; ys = zs
      }
    }
    result
  }

  /* Create large lists and calculate their length.  23 makes the JVM
   * run out of heap space with default settings.
   */
  def test (exponent:Int) {
    for (i <- 2 to exponent) {
      val limit = 1 << i
      println ("[" + i + "] Calculating a list of length: " + limit)
      println (lengthTail ((1 to limit).toList))
    }
  }
}

/******************************************************************/

object Fib {
  /* The Fibonacci sequence starts with 0, 1.  Each subsequent number is
   * obtained by adding the preceding two numbers.
   */

  def fibSlow (n:Int) : Int = 
    if (n <= 0)
      0 
    else if (n == 1) 
      1
    else 
      fibSlow (n - 1) + fibSlow (n - 2)

  import HigherOrder.time
  def result1 () = time (() => fibSlow (5))   // 0 milliseconds
  def result2 () = time (() => fibSlow (10))  // 0 milliseconds
  def result3 () = time (() => fibSlow (20))  // 1 milliseconds
  def result4 () = time (() => fibSlow (25))  // 1 milliseconds
  def result5 () = time (() => fibSlow (30))  // 10 milliseconds
  def result6 () = time (() => fibSlow (35))  // 119 milliseconds
  def result7 () = time (() => fibSlow (40))  // 1269 milliseconds
  def result8 () = time (() => fibSlow (45))  // 14212 milliseconds
  def result9 () = time (() => fibSlow (50))  // 157385 milliseconds
  def result10 () = time (() => fibSlow (50)) // did not attempt

  /* Consider a function that calculates the pair "(fib (n), fib (n - 1))".
   * It can be calculated efficiently (see the auxiliary function "aux" below).
   * The value that we want can be then extracted from the pair.
   */
  def fibFast (n:Int) : Int = {
    def aux (n:Int) : (Int, Int) = {
      if (n <= 0)
        (0, 0)
      else if (n == 1) 
        (1, 0)
      else {
        val (r1, r2) = aux (n - 1)
        (r1 + r2, r1)
      }
    }
    aux (n)._1
  }
    
  def result11 () = time (() => fibFast (5))   // 0 milliseconds
  def result12 () = time (() => fibFast (10))  // 0 milliseconds
  def result13 () = time (() => fibFast (20))  // 0 milliseconds
  def result14 () = time (() => fibFast (25))  // 0 milliseconds
  def result15 () = time (() => fibFast (30))  // 0 milliseconds
  def result16 () = time (() => fibFast (35))  // 0 milliseconds
  def result17 () = time (() => fibFast (40))  // 0 milliseconds
  def result18 () = time (() => fibFast (45))  // 0 milliseconds
  def result19 () = time (() => fibFast (50))  // 0 milliseconds
  def result20 () = time (() => fibFast (50))  // 0 milliseconds
}

/******************************************************************/

object Datatypes {
  object Simple {
    trait GroceryItem
    case object Apple     extends GroceryItem
    case object Banana    extends GroceryItem
    case object Carrot    extends GroceryItem
    case object Orange    extends GroceryItem
    case object Persimmon extends GroceryItem

    val list1 = List (Apple, Banana, Carrot, Orange, Persimmon)

    def isVegetable (g:GroceryItem) : Boolean = g match {
      case Apple     => false
      case Banana    => false
      case Carrot    => true
      case Orange    => false
      case Persimmon => false
    }
    
    /* Use Scala's builtin filter method, as opposed to the filter function
     * defined earlier.
     */
    val fruits = list1.filter (isVegetable)
  }

  object Values {
    trait Color
    case class RGB  (red:Int, green:Int, blue:Int) extends Color
    case class CMYK (cyan:Int, magenta:Int, yellow:Int, key:Int) extends Color

    val color1 = RGB (1, 5, 9)
    val color2 = CMYK (54, 23, 7, 99)

    val list2 = List (color1, color2)

    def yellowInk (c:Color) : Int = c match {
      case RGB  (r, g, b)    => 255 - b
      case CMYK (c, m, y, k) => y
    }
  }

  object Optional {
    trait Option[+X]
    case object None           extends Option[Nothing]
    case class  Some[+X] (x:X) extends Option[X]

    def safeDivide (x:Int, y:Int) : Option[Int] = 
      if (y == 0) 
        None
      else
        Some (x / y)

    /* There is a builtin Option type in most functional programming languages,
     * including Scala.
     */

    /* Discussion: how does the Option type compare with error handling in C or Java? */
  }

  object Lists {
    /* Rename Scala's builtin List class as ScalaList for the current scope. */
    import scala.{List=>ScalaList}

    trait List[+X]
    case object Nil                             extends List[Nothing]
    case class  Cons[+X] (head:X, tail:List[X]) extends List[X]

    val list1 = Nil

    val list2 = Cons (5, list1)

    /* Nil can be used at different types. */
    val list3 = Cons ("hello", list1)

    def toScalaList [X] (xs:List[X]) : ScalaList[X] = xs match {
      case Nil         => ScalaList ()
      case Cons (h, t) => h :: toScalaList (t)
    }

    def fromScalaList [X] (xs:ScalaList[X]) : List[X] = xs match {
      case ScalaList () => Nil
      case h :: t       => Cons (h, fromScalaList (t))
    }

    val list4 = "Hello World!".toList

    val list5 = fromScalaList (list4)

    val list6 = toScalaList (list5)
  }

  object Trees {
    /* There are many different notions of tree:
     *
     * - binary or multiway branching
     * - values on leaves and/or nodes
     */

    trait Tree[+X]
    case object Leaf extends Tree[Nothing]
    case class  Node[+X] (left:Tree[X], contents:X, right:Tree[X]) extends Tree[X]

    val tree1 = Leaf
    val tree2 = Node (Leaf, 5, Leaf)
    val tree3 = Node (Node (Leaf, 2, Leaf), 3, Node (Leaf, 4, Leaf))

    def size [X] (t:Tree[X]) : Int = t match {
      case Leaf => 0
      case Node (t1, _, t2) => size (t1) + 1 + size (t2)
    }

    def map [X,Y] (t:Tree[X], f:X=>Y) : Tree[Y] = t match {
      case Leaf => Leaf
      case Node (t1, c, t2) => Node (map (t1, f), f (c), map (t2, f))
    }

    def flatten [X] (t:Tree[X]) : List[X] = t match {
      case Leaf => Nil
      case Node (t1, c, t2) => flatten (t1) ::: List (c) ::: flatten (t2)
    }

    def makeSampleTree (n:Int) : Tree[Int] = 
      if (n == 0)
        Node (Leaf, 0, Leaf)
      else {
        val t1 = makeSampleTree (n - 1)
        val size1 = size (t1)
        val t2 = map (t1, (x:Int) => x + size1 + 1)
        Node (t1, size1, t2)
      }

    /* What size are the sample trees? */
    def getSampleTreeSizes (n:Int) = (1 to n).toList.map ((n:Int) => (n, size (makeSampleTree (n))))

    /* Binary search through an ordered tree: */
    def binarySearch [X] (t:Tree[X], lt:(X,X)=>Boolean, x:X) : Boolean = t match {
      case Leaf => false
      case Node (t1, c, t2) if (lt (x, c)) => binarySearch (t1, lt, x)
      case Node (t1, c, t2) if (lt (c, x)) => binarySearch (t2, lt, x)
      case Node (t1, c, t2) if (x == c)    => true
    }

    val testSearch = {
      val tree = map (makeSampleTree (5), (x:Int) => 2 * x)
      (-5 to 150).toList.map ((x:Int) => binarySearch (tree, (m:Int, n:Int) => m < n, x))
    }

    /* Insertion into an ordered binary tree.  Produces an ordered tree,
     * but not necessarily balanced.
     */
    def insert [X] (x:X, t:Tree[X], lt:(X,X)=>Boolean) : Tree[X] = t  match {
      case Leaf => Node (Leaf, x, Leaf)
      case Node (t1, c, t2) if (lt (x, c)) => Node (insert (x, t1, lt), c, t2)
      case Node (t1, c, t2) if (lt (c, x)) => Node (t1, c, insert (x, t2, lt))
      case Node (t1, c, t2) if (x == c)    => Node (t1, c, t2)
    }
  }
}



/******************************************************************/

object FirstClassFunc {

  /* Functions are first-class citizens.  They can be:
   *
   * 1. Passed as arguments to a function.
   * 2. The result of evaluating an expression:
   *    a. Returned from a function.
   *    b. Bound using "val".
   */

  def foo1 (x:Int) : Int = x + 1

  val foo2 : Int => Int = (x:Int) => x + 1

  val test1 = foo1 (5)
  val test2 = foo2 (5)

  /* What about functions with two parameters */
  
  def foo3 (x:Int, y:Int) : Int = x + y

  val foo4 : (Int, Int) => Int = (x:Int, y:Int) => x + y

  val test3 = foo3 (5, 7)
  val test4 = foo4 (5, 7)

  /* There is another option. */

  def foo5 (x:Int) : Int => Int = (y:Int) => x + y

  val foo6 : Int => Int => Int = (x:Int) => (y:Int) => x + y
    
  val test5 = foo5 (5) (7)
  val test6 = foo6 (5) (7)

  /* ASSOCIATIVITY
   *
   * Int => Int => Int           == Int => (Int => Int)
   * (x:Int) => (y:Int) => x + y == (x:Int) => ( (y:Int) => x + y )
   * foo5 (5) (7)                == (foo5 (5)) (7)
   */

  /* foo4 : (Int, Int) => Int   -- a function that takes a pair of Ints to an Int
   * foo6 : Int => (Int => Int) -- a function that takes an Int and returns something of type Int => Int
   */

  /* Functions that return functions can be partially applied. */

  val foo7 : Int => Int = foo5 (5)
  val foo8 : Int => Int = foo6 (5)

  val test7 = foo7 (7)
  val test8 = foo8 (7)

  /* Partial application is very closely related with methods that
   * return object references in object-oriented programming.
   */

  /* Functions can operate on other functions.
   * Function composition is a common example.
   */

  def compose [X,Y,Z] (f:Y=>Z) (g:X=>Y) (x:X) : Z = f (g (x))

  val not : Boolean => Boolean = (b:Boolean) => !b
  def isEven (n:Int) = (n % 2) == 0
  val list1:List[Int] = List (0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  val results1 = list1.filter (isEven)
  val results2 = list1.filter ((n:Int) => isEven (n))
  val results3 = list1.filter ((n:Int) => !isEven (n))
  val results4 = list1.filter (compose (not) (isEven))

  // The builtin "compose" function/method is used as an infix operator:
  val results5 = list1.filter (not compose isEven)

  def curry [X,Y,Z] (f:(X,Y)=>Z) (x:X) (y:Y) = f (x, y)

  def uncurry [X,Y,Z] (f:X=>Y=>Z) (p:(X,Y)) = p match {
    case (x, y) => f (x) (y)
  }
}


/******************************************************************/

object ListComprehensions {

  val list1 = List (0, 3, 5, 43, 0, 4343, 2, 0)

  /* List comprehensions are inspired by set comprehensions.  The order
   * of the original list determines the order in the result list.
   */
  val list2 = for (x <- list1) yield x

  /* Expressions involving x (and other variables) can be used.
   * They may return the same type...
   */
  val list3 = for (x <- list1) yield x * 2

  /* ...Or a different type. */
  val list4 = for (x <- list1) yield (x % 2) == 0

  /* Expressions of Boolean type are called predicates and can be used
   * in list comprehensions as with the "filter" function.
   */
  val list5 = for (x <- list1; if ((x % 2) == 1)) yield x * 2


  /* Just as tuples can contain other tuples, lists can contain other lists. */
  val nestedlist1 : List[List[Int]] = List (List (1, 2, 3), List (4, 5, 6), List (7, 8, 9))

  /* List comprehensions can be used on lists of lists (and beyond). */
  val list6 = for (xs <- nestedlist1; x <- xs) yield x

  val list7 = for (xs <- nestedlist1; x <- xs.reverse) yield x

  val list8 = for (xs <- nestedlist1.reverse; x <- xs) yield x

  val list9 = for (xs <- nestedlist1.reverse; x <- xs.reverse) yield x

  val list10 = (for (xs <- nestedlist1; x <- xs) yield x).reverse

  /* The nested iteration can be packaged as a function.  We typically use
   * "xss" and "yss" as variables ranging over lists of lists of
   * elements.  Contrast this definition with the recursive definition given earlier. 
   */
  def concat [X] (xss:List[List[X]]) : List[X] = for (xs <- xss; x <- xs) yield x

  def concatresult = concat (nestedlist1)

  /* List comprehensions can be turned back into combinations of map,
   * filter, and concat.
   */
  val list5b = list1.filter ((x:Int) => (x % 2) == 1).map ((x:Int) => x * 2)

  def isOdd (x:Int) = (x % 2) == 1
  val list5c = list1.filter (isOdd).map (_ * 2)
  def double (x:Int) = x * 2
  val list5d = list1.filter (isOdd).map (double)

  /* Create permutations of a list. */
  def permutation1 [X] (xs:List[X]) : List[List[X]] = xs match {
    case Nil     => List (Nil)
    case _       => 
      for (x <- xs; ys <- permutation1 (xs.filterNot (_ == x))) 
      yield x :: ys
  }

  /* It is not necessarily more readable with map, filter, and concat. */
  def permutation2 [X] (xs:List[X]) : List[List[X]] = xs match {
    case Nil     => List (Nil)
    case _       => {
      def aux (x:X) = permutation2 (xs.filterNot (_ == x)).map (x :: _)
      xs.map (aux).flatten 
    }
  }
}
