package parser;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class Translator {

    private ASTNode<String> ast;
    private Map<String, String> registers;


    public Translator(ASTNode<String> ast) {
        this.ast = ast;
        this.registers = new HashMap<>();
    }


    public List<String> translate() {
        List<String> instructions = new ArrayList<>();
        
        return instructions;
    }

}
