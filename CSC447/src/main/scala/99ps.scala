object probs {
 
  
  def last [X] (xs:List[X]): X = {
    xs match {
      //case Nil => List()
      case y::Nil => y
      case _::ys => last(ys)
      case _ => throw new NoSuchElementException
    }
  }
  
  def penultimate [X] (xs:List[X]): X = {
    xs match {
      case y::Nil => y
      case y::z::ys => if (ys == Nil) y
                        else penultimate(z::ys)
      case _ => throw new NoSuchElementException
    }
  }
  
  def nth[X] (x:Int, xs:List[X]):X = {
    (x, xs) match {
      case (0, y:: _) => y
      case (n, _::ys) => nth(n-1,ys)
      case (_, Nil) => throw new NoSuchElementException
    }
  }
  
  def length[X] (xs:List[X]) : Int = {
    xs match {
      case Nil => 0
      case y::Nil => 1
      case y::ys => 1 + length(ys)
    }
  }
  
  def reverse[X] (xs: List[X]) : List[X] = {
    xs match {
      case Nil => Nil
      case y::Nil => y::Nil
      case y::ys => reverse(ys):::List(y)
    }
  }
  
  def isPal[X] (xs: List[X]): Boolean = {
    xs match {
      case Nil => false
      case y::Nil => true
      case y::ys => xs == xs.reverse
    }
  }
  
  def flatten [X] (xs:List[X]) : List[X] = {
    xs match {
      case Nil => Nil
      case y::ys => flatten(List(y)) ::: flatten(ys)
    }
  }
  
  def compress [X] (xs: List[X]) : List[X] = {
    xs match {
      case Nil => Nil
      case y::ys => y :: compress(ys.dropWhile(_ == y))
      
    }
  }
  
  def duplicate [X] (xs:List[X]) : List[X] = {
    xs match {
      case Nil => Nil
      case y::ys => y::y::duplicate(ys)
    }
  }
  
  /*def duplicateN [X] (x:Int, xs:List[X]) : List[X] = {
    (x,xs) match {
      case (n,Nil) => Nil
      case (0, _) => xs
      case (n,y:ys) => y::y::duplicate(ys)
    }
  }*/
  
  
}