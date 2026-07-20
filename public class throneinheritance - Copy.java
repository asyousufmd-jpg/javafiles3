public class ThroneInheritance {
    // Map to represent the family tree: parentName -> list of childNames
    private final Map<String, List<String>> familyTree;
    // Set to keep track of dead family members
    private final Set<String> deadSet;
    // The starting root of the inheritance
    private final String king;

    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.familyTree = new HashMap<>();
        this.deadSet = new HashSet<>();
        // Initialize the king's child list
        familyTree.put(kingName, new ArrayList<>());
    }
    
    public void birth(String parentName, String childName) {
        // Add the child to the parent's list of children
        familyTree.computeIfAbsent(parentName, k -> new ArrayList<>()).add(childName);
    }
    
    public void death(String name) {
        // Mark the person as dead
        deadSet.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        // Perform pre-order traversal (DFS) starting from the king
        dfs(king, order);
        return order;
    }

    private void dfs(String current, List<String> order) {
        // If the current person is not dead, add them to the inheritance list
        if (!deadSet.contains(current)) {
            order.add(current);
        }
        
        // Traverse all children in the order of their birth
        List<String> children = familyTree.get(current);
        if (children != null) {
            for (String child : children) {
                dfs(child, order);
            }
        }
    }
}