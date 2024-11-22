import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Linked general tree is a class implementing the tree ADT
 *
 * Based on Goodrich, Tamassia, Goldwasser and the course IFT2015 code
 *
 * @authors
 * @version     1.0
 * @since       1.0
 */

public class LinkedGeneralTree<E> implements Tree<E> {

    // Inner class TreeNode implementing Position interface
    public static class TreeNode<E> implements Position<E> {
        private E element;  //reference to the element stored in this node
        private TreeNode<E> parent; //reference to the parent of the node
        private List<TreeNode<E>> children; //reference to a list of children of the node

        public TreeNode(E element, TreeNode<E> parent, List<TreeNode<E>> children) {
            this.element = element;
            this.parent = parent;
            this.children = new ArrayList<>();
            if(children != null)this.children.addAll(children);
        }
        //Inner class methods
        // getters
        @Override
        public E getElement() { return this.element; }
        public TreeNode<E> getParent() { return this.parent; }
        public List<TreeNode<E>> getChildren() { return this.children; }
        public TreeNode<E> getChildK(int k){
            if(children.isEmpty()) return null;
            else {return this.children.get(k);}
        }
        // setters
        public void setElement( E e ) { this.element = e; }
        public void setParent( TreeNode<E> parent ) { this.parent = parent; }
        public void setChildren( List<TreeNode<E>> children ) { this.children = new ArrayList<>();
            if(children != null)this.children.addAll(children); }

        public void addChild(TreeNode<E> child) {
            children.add(child);
        }
    }//----- end of inner class TreeNode -----

    //instance attributes
    private TreeNode<E> root; // Root node of the tree
    private int size;         // Size of the tree (total number of nodes)

    // Constructor to initialize the tree with a root
    public LinkedGeneralTree(E rootElement) {
        root = new TreeNode<>(rootElement, null, null);
        size = 1;
    }

    // Method to get the node of the root of the tree
    //public TreeNode<E> getRoot() {
      // return this.root;
    //}

    // Returns the Position of the root of the tree
    @Override
    public Position<E> root() {
       return this.root;
    }
    //Returns the Position of the parent of the node at position p or null if p is the root
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        TreeNode<E> node = validate(p);
        return node.getParent();
    }
    //Returns an iterable collection of children
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        TreeNode<E> node = validate(p);
        return new ArrayList<>(node.getChildren());

    }
    //Returns the position of the child at index k in the collection of children of node at position p
    public Position<E> childK(Position<E> p, int k) throws IllegalArgumentException {
        TreeNode<E> node = validate(p);
        return node.getChildK(k);
    }
    //Returns the number of children of Position p
    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        TreeNode<E> node = validate(p);
        return node.getChildren().size();
    }
    //Returns true if Position p has at least one child
    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }
    //Returns true if Position p has no child
    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }
    //Returns true if Position p is the root of the tree
    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root;
    }
    //Returns the number of elements in the tree
    @Override
    public int size() {
        return size;
    }
    //Returns true if the tree does not contain any position
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    //Returns an iterator for all the elements of the tree
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Iterator<Position<E>> positionIterator = positions().iterator();

            public boolean hasNext() {
                return positionIterator.hasNext();
            }

            public E next() {
                return positionIterator.next().getElement();
            }
        };
    }
    //Returns an iterable collection of all positions of the tree
    @Override
    public Iterable<Position<E>> positions() {
        List<Position<E>> positions = new ArrayList<>();
        preorder(root, positions);
        return positions;
    }

    // Preorder traversal helper for positions
    private void preorder(Position<E> p, List<Position<E>> positions) {
        positions.add(p);
        for (Position<E> c : children(p)) {
            preorder(c, positions);
        }
    }

    // Adds a new child to a given parent node and returns the position of the new child node
    public Position<E> addNode(E element, Position<E> parent) throws IllegalArgumentException {
        TreeNode<E> parentNode = validate(parent);
        List<TreeNode<E>> children = new ArrayList<>();
        TreeNode<E> childNode = new TreeNode<>(element, parentNode, children);
        parentNode.addChild(childNode);
        size++;
        return childNode;
    }

    // Validates the position and casts it to TreeNode if valid
    private TreeNode<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof TreeNode)) throw new IllegalArgumentException("Invalid position type");
        TreeNode<E> node = (TreeNode<E>) p;
        if (node.getParent() == node) throw new IllegalArgumentException("Position is no longer in the tree");
        return node;
    }
    //Removes a node and replace it with its children if any
    public E remove(Position<E> p) throws IllegalArgumentException {
        TreeNode<E> node = this.validate( p );
        if( numChildren( p ) >= 2 )
            throw new IllegalArgumentException( "p has at leas two children" );
        TreeNode<E> child = node.getChildK(0);
        if( child != null )
            child.setParent( node.getParent() ); // child's grandparent becomes parent
        if( node == root )
            this.root = child; // child becomes root
        else {
            TreeNode<E> parent = node.getParent();
            parent.addChild( child );
        }
        this.size--;
        E tmp = node.getElement();
        node.setElement( null ); // garbage collection
        node.setChildren( null );
        node.setParent( node ); // convention for defunct node
        return tmp;
    }
    //Removes all descendants (if any) of the node at position p
    public void removeBranch(Position<E> p)  {
        TreeNode<E> node = this.validate( p );
        for( TreeNode<E> c : node.getChildren() ){
            if(c!= null) {
                removeBranch(c);
                remove(c);
            }
        }
    }


}
