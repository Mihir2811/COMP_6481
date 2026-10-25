import java.util.*; // Your imports

// basically **BURN** this template in your brain

public class BasicConstruct {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Taking Input
        takingInput(sc);

        // 2. Basic Output
        basicOutput();

        // 3. String Operations
        stringOperations();

        // 4. Basic Type Conversions
        typeConversions();

        sc.close();
    }

    // How to take input in Java
    public static void takingInput(Scanner sc) {

        // Stuff you are NOT supposed to do in domjudge
        // System.out.print("Enter the input: ");

        // For single line inputs
        // String[] input = sc.nextLine().split(" ");
        // System.out.println(Arrays.toString(input));

        // For multiple line inputs
        // int n = sc.nextInt(); // number of lines
        // sc.nextLine(); // consume the leftover newline
        // for (int i = 0; i < n; i++) {
        //     String[] lineInput = sc.nextLine().split(" ");
        //     System.out.println(Arrays.toString(lineInput));
        // }
    }

    // How to show output in Java
    public static void basicOutput() {
        System.out.println("Why so serious?");

        System.out.println(String.format("Why \n so \t serious?"));
        System.out.println(
            String.format("Why so serious? %d", 123)
        );

        System.out.printf("Why so %s %s", "Serious", "?");
        System.out.println();

        // DO NOT DO THIS IN DOMJUDGE
        // return "Hello World";
    }

    // Some basic String operations
    public static void stringOperations() {
        String str = "Bcoz I am Batman! ";

        // Atleast Know these methods.
        System.out.println("\nString operations\n");
        str = str.trim(); // removes leading and trailing spaces
        System.out.println(str);
        System.out.println(str.length());
        System.out.println(str.charAt(0));
        System.out.println(str.substring(0, 5));
        System.out.println(str.indexOf("World"));
        System.out.println(str.indexOf("Batman"));

        // Optionally also know these methods.
        System.out.println(str.toUpperCase());
        System.out.println(str.toLowerCase());
        System.out.println(str.replace("Bcoz", "Because"));
        System.out.println(str.contains("Batman"));
        System.out.println(str.startsWith("Bcoz"));
        System.out.println(str.endsWith("!"));
    }

    // Some basic type conversions
    public static void typeConversions() {

        // 1. Implicit (Widening) Type Conversion
        int smallNum = 10;
        double largeNum = smallNum; // int to double

        // 2. Explicit (Narrowing) Type Conversion: Larger to Smaller primitive (Requires Cast)
        double doubleNum = 9.78;
        int intNum = (int) doubleNum; // double to int (truncates decimal -> 9)

        // 3. String and Char Array Conversions
        String str = "How you doin?";
        char[] charArr = str.toCharArray(); // String to char array
        String str2 = String.valueOf(charArr); // char array to String

        // 4. Char to Int / Int to Char (ASCII/Unicode)
        char c = 'A';
        int int_c = c; // char to int implicitly (ASCII value -> 65)
        int int_c_2 = (int) c; // char to int explicitly (ASCII value -> 65)

        // Fixed line 18: Use Integer.parseInt to parse a numeric character string (e.g. '5' -> 5)
        char numChar = '5';
        int numericValue = Integer.parseInt(String.valueOf(numChar)); // Converts '5' -> "5" -> 5
        int numericValueDirect = Character.getNumericValue(numChar); // Direct char digit to int

        int asciiValue = 66;
        char charFromAscii = (char) asciiValue; // int to char ('B')

        // 5. String to Primitive / Primitive to String
        String strNum = "123";
        int parsedInt = Integer.parseInt(strNum); // String to int
        double parsedDouble = Double.parseDouble("45.67"); // String to double

        int num = 456;
        String stringFromInt = String.valueOf(num); // int to String
        String stringFromInt2 = Integer.toString(num); // Alternative int to String

        // 6. Primitive to Wrapper Object (Autoboxing / Unboxing)
        int primitiveInt = 50;
        Integer boxedInt = primitiveInt; // Autoboxing (int -> Integer)
        int unboxedInt = boxedInt; // Unboxing (Integer -> int)
    }
}
