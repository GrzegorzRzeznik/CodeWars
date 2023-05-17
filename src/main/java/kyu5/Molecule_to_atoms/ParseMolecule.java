package kyu5.Molecule_to_atoms;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
For a given chemical formula represented by a string, count the number of atoms of each element contained in the molecule and return
an object (associative array in PHP, Dictionary<string, int> in C#, Map<String,Integer> in Java).

For example:

String water = "H2O";
parseMolecule.getAtoms(water); // return [H: 2, O: 1]

String magnesiumHydroxide = "Mg(OH)2";
parseMolecule.getAtoms(magnesiumHydroxide); // return ["Mg": 1, "O": 2, "H": 2]

String fremySalt = "K4[ON(SO3)2]2";
parseMolecule.getAtoms(fremySalt); // return ["K": 4, "O": 14, "N": 2, "S": 4]

parseMolecule.getAtoms("pie"); // throw an IllegalArgumentException
As you can see, some formulas have brackets in them. The index outside the brackets tells you that you have to multiply
count of each atom inside the bracket on this index. For example, in Fe(NO3)2 you have one iron atom, two nitrogen atoms and six oxygen atoms.

Note that brackets may be round, square or curly and can also be nested. Index after the braces is optional.
 */
public class ParseMolecule {
    private static LinkedHashMap<String, Integer> RESULT = new LinkedHashMap<>();
    private final static String atoms = "(Ac|Ag|Al|Am|Ar|As|At|Au|Br|Ba|Be|Bh|Bi|Bk|B|Cu|" +
            "Ca|Cd|Ce|Cf|Cl|Cm|Co|Cr|Cs|C|Ds|Db|Dy|Er|Es|Eu|Fr|Fe|Fm|" +
            "F|Ga|Gd|Ge|H|He|Hf|Hg|Ho|Hs|Ir|In|I|Kr|K|La|Li|Lr|Lu|Md|" +
            "Mg|Mn|Mo|Mt|Np|Na|Nb|Nd|Ne|Ni|No|N|Os|O|Pu|Pa|Pb|Pd|Pm|Po|" +
            "Pr|Pt|P|Ra|Rb|Re|Rf|Rg|Rh|Rn|Ru|Sr|Sb|Sc|Se|Sg|Si|Sm|Sn|S|" +
            "Ta|Tb|Tc|Te|Th|Ti|Tl|Tm|U|V|W|Xe|Yb|Y|Zn|Zr)";

    public static Map<String, Integer> getAtoms(String formula) {
        System.out.println(formula);
        RESULT = new LinkedHashMap<>();
        if (!validateBrackets(formula)) {
            throw new IllegalArgumentException();
        }
        prepareMapOrder(formula);
        if (RESULT.size() == 0) {
            throw new IllegalArgumentException();
        }
        String noExtraBrackets = removeExtraBrackets(formula);
        String simple = simplify(noExtraBrackets);
        solveSimplifiedFormula(simple);
        return RESULT;
    }
    public static void prepareMapOrder(String formula) {
        Pattern atomPattern = Pattern.compile(atoms);
        Matcher matcher = atomPattern.matcher(formula);
        while (matcher.find()) {
            if (!RESULT.containsKey(matcher.group())) {
                RESULT.put(matcher.group(), 0);
            }
        }
    }
    public static String simplify(String formula) {

        while (formula.contains("{")) {
            StringBuilder sb = new StringBuilder();
            String regex = "\\{([^}]+)}\\d*";
            String temp = Pattern.compile(regex)
                    .matcher(formula)
                    .results().findAny()
                    .map(MatchResult::group).get();
            sb.append(formula.replaceFirst( regex, ""));
            int num = getMultiplier(temp, "(}\\d+)");
            String toAppend = formula.substring(formula.indexOf("{") + 1, formula.indexOf("}"));
            sb.append(toAppend.repeat(num));
            formula = sb.toString();
        }
        while (formula.contains("[")) {
            StringBuilder sb = new StringBuilder();
            String regex = "\\[([^]]+)]\\d*";
            String temp = Pattern.compile(regex)
                    .matcher(formula)
                    .results().findAny()
                    .map(MatchResult::group).get();
            sb.append(formula.replaceFirst( regex, ""));
            int num = getMultiplier(temp, "(]\\d+)");
            String toAppend = formula.substring(formula.indexOf("[") + 1, formula.indexOf("]"));
            sb.append(toAppend.repeat(num));
            formula = sb.toString();
        }
        while (formula.contains("(")) {
            StringBuilder sb = new StringBuilder();
            String regex = "\\((\\w*)\\)\\d*";
            String temp = Pattern.compile(regex)
                    .matcher(formula)
                    .results().findAny()
                    .map(MatchResult::group).get();
            sb.append(formula.replaceFirst( regex, ""));
            int num = getMultiplier(temp, "(\\)\\d+)");
            String toAppend = formula.substring(formula.indexOf("(") + 1, formula.indexOf(")"));
            sb.append(toAppend.repeat(num));
            formula = sb.toString();
        }

        return formula;
    }
    private static String removeExtraBrackets(String formula) {
        while (formula.matches(".*}\\D*")) {
            formula = formula.replace("{", "");
            formula = formula.replace("}", "");
        }
        while (formula.matches(".*]\\D*")) {
            formula = formula.replace("[", "");
            formula = formula.replace("]", "");
        }
        while (formula.matches(".*\\)\\D*")) {
            formula = formula.replace("(", "");
            formula = formula.replace(")", "");
        }
        return formula;
    }
    private static int getMultiplier(String formula, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(formula);
        int num = 1;
        if (m.find()) {
            num = Integer.parseInt(m.group().substring(1));
        }
        return num;
    }
    private static boolean validateBrackets(String formula) {
        Stack<Character> stack = new Stack<>();
        for (char c : formula.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            }
            if (c == '}') {
                if (stack.isEmpty() || stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
            }
            if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            }
            if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }
    private static void solveSimplifiedFormula(String formula) {
        List<String> list = new ArrayList<>();
        String regex = "(" + atoms + "\\d*)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(formula);
        while (m.find()) {
            list.add(m.group());
        }
        for (String s : list) {
            String atom = s.replaceAll("\\d*", "");
            int number = 1;
            if (s.replaceAll("\\D*", "").length() > 0) {
                number = Integer.parseInt(s.replaceAll("\\D*", ""));
            }
            insertIntoMap(atom, number);
        }
    }
    private static void insertIntoMap(String element, int num) {
        if (RESULT.containsKey(element)) {
            RESULT.replace(element, RESULT.get(element) + num);
        } else {
            RESULT.put(element, num);
        }
    }
}