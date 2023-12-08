import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Main {
    /**
     * Function that validates a CPF
     *
     * @param cpf the CPF number (xxx.xxx.xxx-xx or xxxxxxxxxxx)
     * @return True if the CPF is valid, and false if is not valid
     **/
    public static boolean ValidateCPF(String cpf) throws Exception {
        cpf = cpf.replace(".", "").replace("-", "");

        if(!cpf.matches("[0-9]+")){
            throw new Exception("Invalid cpf");
        }

        if(cpf.length() != 11){
            throw new Exception("Invalid cpf");
        }

        String[] result_str = cpf.split("");
        int[] cpf_num = new int[11];

        for (int i = 0; i < cpf_num.length; i++) {
            cpf_num[i] = Integer.parseInt(result_str[i]);
        }

        int cont = 10;
        int tot = 0;
        for(int i: Arrays.copyOfRange(cpf_num, 0, 9)){
            tot += i * cont;
            cont--;
        }

        int dig1 = 11 -tot %11 > 9 ? 0 : 11 -tot %11;

        if (dig1 != cpf_num[9]){
            return false;
        }
        else{
            cont = 11;
            tot = 0;
            for(int i: Arrays.copyOfRange(cpf_num, 0, 10)){
                tot += i * cont;
                cont--;
            }
            int dig2 = 11 -tot %11 > 9 ? 0 : 11 -tot %11;
            return dig2 == cpf_num[10];
        }

    }

    /**
     * Function that generates and return a random valid CPF
     *
     * @return random valid cpf
     **/
    public static String GenerateCPF(){
        int[] cpf_num = new int[11];
        Random rand = new Random();

        for (int i = 0; i < 9; i++) {
            cpf_num[i] = rand.nextInt(10);
        }

        int cont = 10;
        int tot = 0;
        for(int i: Arrays.copyOfRange(cpf_num, 0, 9)){
            tot += i * cont;
            cont--;
        }

        cpf_num[9] = 11 -tot %11 > 9 ? 0 : 11 -tot %11;

        cont = 11;
        tot = 0;
        for(int i: Arrays.copyOfRange(cpf_num, 0, 10)){
            tot += i * cont;
            cont--;
        }
        cpf_num[10] = 11 -tot %11 > 9 ? 0 : 11 -tot %11;

        String cpf_num_format = cpf_num[0]+""+cpf_num[1]+""+cpf_num[2]+"."+cpf_num[3]+""+cpf_num[4]+""+cpf_num[5]+"."+cpf_num[6]+""+cpf_num[7]+""+cpf_num[8]+"-"+cpf_num[9]+""+cpf_num[10];

        return cpf_num_format;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true){
            System.out.println("\nWelcome to the CPF validator");
            System.out.println("(1) - Validate CPF");
            System.out.println("(2) - Generate CPF");
            System.out.println("(3) - Exit");
            System.out.print("Select an option above:");
            try {
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        boolean validation = false;
                        String cpf;
                        while (true){
                            try {
                                System.out.print("Type the cpf: ");
                                cpf = sc.next();
                                validation = ValidateCPF(cpf);
                                break;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        if (validation) {
                            System.out.println("\nThe cpf: " + cpf + " is valid");
                        } else {
                            System.out.println("\nThe cpf: " + cpf + " is invalid");
                        }
                        break;
                    case 2:
                        System.out.println("\nCPF generated: "+GenerateCPF());
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nInvalid option");
                }
            }
            catch (Exception e) {
                System.out.println("\nInvalid option");
                sc.nextLine();
            }
        }

    }
}
