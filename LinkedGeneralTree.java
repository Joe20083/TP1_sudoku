import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedGeneralTree<E> implements Tree<E> {
    private TreeNode<E> root; // Root node of the tree
    private int size;         // Size of the tree (total number of nodes)

    // Inner class TreeNode implementing Position interface
    public static class TreeNode<E> implements Position<E> {
        private E element;
        private TreeNode<E> parent;
        private List<TreeNode<E>> children;

        public TreeNode(E element, TreeNode<E> parent) {
            this.element = element;
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        @Override
        public E getElement() {
            return element;
        }

        public TreeNode<E> getParent() {
            return parent;
        }

        public List<TreeNode<E>> getChildren() {
            return children;
        }

        public void addChild(TreeNode<E> child) {
            children.add(child);
        }
    }

    // Constructor to initialize the tree with a root node
    public LinkedGeneralTree(E rootElement) {
        root = new TreeNode<>(rootElement, null);
        size = 1;
    }

    // Method to get the root of the tree
    public TreeNode<E> getRoot() {
        return root;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        TreeNode<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        TreeNode<E> node = validate(p);
        return new ArrayList<>(node.getChildren());
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        TreeNode<E> node = validate(p);
        return node.getChildren().size();
    }

    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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

    // Adds a new child to a given parent node and returns the new child node
    public Position<E> addNode(E element, Position<E> parent) throws IllegalArgumentException {
        TreeNode<E> parentNode = validate(parent);
        TreeNode<E> childNode = new TreeNode<>(element, parentNode);
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
}
