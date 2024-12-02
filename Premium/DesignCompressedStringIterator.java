// 604. Design Compressed String Iterator
// Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

// The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

// next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
// hasNext() - Judge whether there is any letter needs to be uncompressed.

// Note:
// Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

// Example:

// StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

// iterator.next(); // return 'L'
// iterator.next(); // return 'e'
// iterator.next(); // return 'e'
// iterator.next(); // return 't'
// iterator.next(); // return 'C'
// iterator.next(); // return 'o'
// iterator.next(); // return 'd'
// iterator.hasNext(); // return true
// iterator.next(); // return 'e'
// iterator.hasNext(); // return false
// iterator.next(); // return ' '



class StringIterator {
  
  List<Node> list;
  int index = 0;
  
  public StringIterator(String compressedString) {
    list = new ArrayList<>();
    int n = compressedString.length();
    int i = 0;
    while(i < n) {
      char ch = compressedString.charAt(i);
      int charCount = 0;
      while(++i < n && Character.isDigit(compressedString.charAt(i))) {
        charCount = charCount*10 + (compressedString.charAt(i) - '0');
      }
      list.add(new Node(ch, charCount));
    }
  }
  
  public char next() {
    if(!hasNext())
      return ' ';
    char ch = list.get(index).ch;
    if(--list.get(index).count == 1)
      index++;
    return ch;
  }
  
  public boolean hasNext() {
    return index < list.size() && list.get(index).count > 0;
  }
}

class Node {
  char ch;
  int count;
  public Node(char ch, int count) {
    this.ch = ch;
    this.count = count;
  }
}
