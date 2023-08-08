import java.util.Stack;

public class ExpressionTree implements ExpressionTreeInterface {

        public static class ExpressionNode{
            private ExpressionNode left;
            private ExpressionNode right;
            private String value;
        

            private ExpressionNode (String value){
                this.value = value;
                left = right = null;

            }

            private ExpressionNode ( String value, ExpressionNode left, ExpressionNode right){
                this.value = value;
                this.left = left;
                this.right = right;
            }

        }


        public static boolean Operator (String operator){
            if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")){
                return true;
                }
            return false;
        }
        
        private ExpressionNode root;

        public ExpressionTree(String expression){
            Stack<ExpressionNode> eNode = new Stack<>();
            String [] Array = expression.split(" ");
            for (String c: Array){
                if (Operator(c)){
                    ExpressionNode a = eNode.pop();
                    ExpressionNode b = eNode.pop();

                    ExpressionNode node = new ExpressionNode (c, b, a);
                    eNode.add (node);
                }

                else{
                    eNode.add (new ExpressionNode(c));

                }     
            }

            root = eNode.peek();

        }

        public int eval(){
            return eval(root);
        }

        private int eval (ExpressionNode r){
            if (r == null){
                return 0;
            }

            int evaluateLeft = eval(r.left);
            int evaluateRight= eval(r.right);

            if (r.value.equals("+")){
                return evaluateLeft + evaluateRight;
            }

            if (r.value.equals("-")){
                return evaluateLeft - evaluateRight;
            }

            if (r.value.equals("*")){
                return evaluateLeft * evaluateRight;
            }

            if (r.value.equals ("/")){
                return evaluateLeft / evaluateRight;
            
            }else{
                return Integer.valueOf(r.value);
            }
            
        } 
                
            
        public String postfix(){
            return postfix(root).trim();
        }

        private String postfix (ExpressionNode s){
            if (s.left == null && s.right == null ){
                return Integer.toString(Integer.valueOf(s.value));
            
            }else {
            return postfix(s.left) + " " + postfix(s.right)+ " " + (s.value);
            }
        }

        public String prefix(){
            return prefix(root).trim();
        }

        private String prefix (ExpressionNode s){
            if (s.left == null && s.right == null ){
                return Integer.toString(Integer.valueOf(s.value));
            
            }else{
                return (s.value) + " " + prefix(s.left) + " " + prefix (s.right);
            }

        }

        public String infix(){
            return infix(root).trim();
        }

        public String infix (ExpressionNode s){
           if (s.left == null && s.right == null ){
                return Integer.toString(Integer.valueOf(s.value));
            
            }else{ 
              return "(" + infix (s.left) + (s.value) + infix (s.right) + ")";
            }
        }
        
}

