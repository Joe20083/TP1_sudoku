import java.util.List;

public class GeneralTree<E> extends AbstractTree<E> {

    //----- inner Node class
    protected static class Node<E> implements Position<E> {
        private E element; // element stored in this node
        private Node<E> parent; // reference to parent node, if any
        private List<Node<E>> children; // reference to the children
        private Object container; // reference to the node's container
        // construct a node with element and neighbors
        public Node( E e, Node<E> parent, List<Node<E>> children) {
            this.element = e;
            this.parent = parent;
            this.children = children;
            this.container = GeneralTree.this;
        }
        // getters
        public E getElement() { return this.element; }
        public Node<E> getParent() { return this.parent; }
        public List<Node<E>> getChildren() { return this.children; }
        public Node<E> getChildK(int k) { return this.children.get(k-1); }
        public Object getContainer() { return this.container; }
        // setters
        public void setElement( E e ) { this.element = e; }
        public void setParent( Node<E> parent ) { this.parent = parent; }
        public void setChild( Node<E> child ) { this.children.add(child); }
        public void setContainer( Object container ) { this.container = container; }
        public String toString() { return this.element.toString(); }

    } //----- end of inner class Node
    // Developer's method to create a new node with element e
    protected Node<E> createNode( E e, Node<E> parent, List<Node<E>> children ) {
        return new Node<E>( e, parent, children);
    }

    // GeneralTree attributes
    protected Node<E> root = null; // root of the tree, initially null
    private int size = 0; // number of nodes in the tree, initially 0

    // default constructor for an empty tree
    public GeneralTree() {}

    // developer's utilities
    // validate a position and returns its node
    protected Node<E> validate( Position<E> p ) throws IllegalArgumentException {
        if( !( p instanceof Node ) ) throw new IllegalArgumentException( "Invalid position type" );
        Node<E> node = (Node<E>)p; // safe cast
        if( node.getContainer() != (Object)this ) throw new IllegalArgumentException( "Invalid position container" );
        if( node.getParent() == node ) // convention for defunct node
            throw new IllegalArgumentException( "Position has been deleted" );
        return node;
    }
    // public methods not already implemented in AbstractBinaryTree
    @Override
    public int size() { return this.size; }
    @Override
    public Position<E> root() { return this.root; }
    @Override
    public Position<E> parent( Position<E> p ) throws IllegalArgumentException {
        Node<E> node = this.validate( p );
        return node.getParent();
    }
    @Override
    public Position<E> childK( Position<E> p, int k ) throws IllegalArgumentException {
        Node<E> node = this.validate( p );
        return node.getChildK(k);
    }
    @Override
    public int numChildren(Position<E> p){
        Node<E> node = this.validate( p );
        return node.children.size();
    }



}
