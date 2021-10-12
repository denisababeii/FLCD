public class Test {
    public static void main(String[] args) {
        SymbolTable<String, Integer> table = new SymbolTable<>();
        table.add("a",1);
        table.add("b",2);
        table.add("c",3);
        table.add("c",4);

        System.out.println(table.get("a"));
        System.out.println(table.get("b"));
        System.out.println(table.get("c"));
    }
}
