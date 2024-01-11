public class One {
    public static void main(String[] args) {

        /* for(declaration; condition; iteration or breaker)
            body */
        
            
        /* public = change values inside public function {}
           static = programmer writes the code(dynamic = computer; AI)
           void = null, no return values, open to interpretation(any data type)
           main = everything inside is part of the code
                = creating function should be outside the main
                        public static void function_name() {}                    
         */
        notmain(); //call the function inside main
        System.out.print(add(5));
    }

     public static void notmain() {
        System.out.println("halo"); //write the function inside class
    }

    public static int add(int a) {
        return a + a;
    }




}

   