import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Dex {

    /* ERRORS FOUND
    - After creating a pokemon, the ERROR message appears (1.0)
    - Saying no to creating another route leads to an error (1.1)
    - Typing a letter into a place that wishes for an integer crashes the program (2.0)
     */

    public static Scanner scnr = new Scanner(System.in);

    public static int skillMod(int skill) {
        int mod = 0;
        if (skill == 1) {
            mod = -5;
        }
        if ((skill==2)||(skill==3)) {
            mod = -4;
        }
        if ((skill==4)||(skill==5)) {
            mod = -3;
        }
        if ((skill==6)||(skill==7)) {
            mod = -2;
        }
        if ((skill==8)||(skill==9)) {
            mod = -1;
        }
        if ((skill==10)||(skill==11)) {
            mod = 0;
        }
        if ((skill==12)||(skill==13)) {
            mod = 1;
        }
        if ((skill==14)||(skill==15)) {
            mod = 2;
        }
        if ((skill==16)||(skill==17)) {
            mod = 3;
        }
        if ((skill==18)||(skill==19)) {
            mod = 4;
        }
        if (skill==20) {
            mod = 5;
        }
        return mod;
    }

    public static double statMod(double stat) {
        double pls;

        pls = (double) (stat/100)+1;

        return pls;
    }

    public static void createPKMN() {
        File moveList = new File("Move List.txt");
        String pkmn;
        String abilities;
        String flush;


        int STR;
        int DEX;
        int CON;
        int WIS;
        int INT;
        int CHA;
        //These are the base tabletop stats. From here, they will be added into calculations to determine a pokemon's stats
        
        
        double baseHP;
        double baseATK;
        double baseSPA;
        double baseDEF;
        double baseSPD;
        double baseSPE;
        //These are the base stats for the pokemon. These can be found on pokemondb.net and are used for the second half of the result calculation.//

        double calcHP;
        double calcATK;
        double calcSPA;
        double calcDEF;
        double calcSPD;
        double calcSPE;
        int calcACR;
        double calcAC;
        //These are the variables that give the double result of the equations. They will be converted to an int for final results.//

        int finalHP;
        int finalATK;
        int finalSPA;
        int finalDEF;
        int finalSPD;
        int finalSPE;
        int finalACR;
        int finalAC;

        //These variables are for miscellaneous features, such as hit die//
        int EXPDie;

        System.out.println("PKMN CREATOR TEST\n");
        System.out.print("Please input the name of the pokemon: ");
        pkmn = scnr.nextLine();
        
        System.out.println("What abilities can this pokemon have? (Separate by '/') ");
        abilities = scnr.nextLine();
        do {
            System.out.print("\n"+"Please enter the pokemon's six core stats (Must be between 0 and 20) (STR DEX CON WIS INT CHA) ");
            STR = scnr.nextInt();
            DEX = scnr.nextInt();
            CON = scnr.nextInt();
            WIS = scnr.nextInt();
            INT = scnr.nextInt();
            CHA = scnr.nextInt();
            if (((0 > STR)||(STR > 20))||((0 > DEX)||(DEX > 20))||((0 > CON)||(CON > 20))||((0 > WIS)||(WIS > 20))||((0 > INT)||(INT > 20))||((0 > CHA)||(CHA > 20))) {
                System.out.println("ERROR, one or more stats did not meet the requirement. Please try again.");
            }
        } while (((0 > STR)||(STR > 20))||((0 > DEX)||(DEX > 20))||((0 > CON)||(CON > 20))||((0 > WIS)||(WIS > 20))||((0 > INT)||(INT > 20))||((0 > CHA)||(CHA > 20)));
        System.out.println("\n\n");

        System.out.println("Please enter the pokemon's base stats (HP ATK SPA DEF SPD SPE) ");
        baseHP = scnr.nextInt();
        baseATK = scnr.nextInt();
        baseDEF = scnr.nextInt();
        baseSPA = scnr.nextInt();
        baseSPD = scnr.nextInt();
        baseSPE = scnr.nextInt();
        flush = scnr.nextLine();

        System.out.print("What is the pokemon's EXP die? ");
        EXPDie = scnr.nextInt();
        flush = scnr.nextLine();


        //CALCULATIONS FOR FINAL STATS//
        calcHP = statMod(baseHP)*10;
        calcATK = 10 * (skillMod(STR)+skillMod(DEX)) * statMod(baseATK);
        calcSPA = statMod(baseSPA)*(10 * (skillMod(WIS)+skillMod(INT)));
        calcDEF = statMod(baseDEF)*(10 * (skillMod(STR)+skillMod(CON)));
        calcSPD = statMod(baseSPD)*(10 * (skillMod(WIS)+skillMod(CON)));
        calcSPE = statMod(baseSPE)*(10 * (skillMod(DEX) * 2));
        calcACR = (skillMod(DEX) + skillMod(WIS)) * 10;
        calcAC = (int)(calcSPE/10)+(int)(calcDEF/20)+(int)(calcSPD/20);

        //FINAL STATS//
        finalHP = (int) calcHP;
        finalATK =  (int) calcATK;
        finalSPA = (int) calcSPA;
        finalDEF = (int) calcDEF;
        finalSPD = (int) calcSPD;
        finalSPE = (int) calcSPE;
        finalACR = (int) calcACR;
        finalAC = (int) calcAC;

        //Moves//
        int i;
        int j;
        int k = 0;
        int count = 0;
        int count2 = 0;
        String line;
        String[] moves;
        String[] names = new String[4];
        String[] desc = new String[4];
        String search = null;
        String confirm = null;
        int buff = 0;
        int maxMoves = 4;

        try {
            Scanner reader = new Scanner(moveList);
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                count++;
            }

            moves = new String[count];
            reader = new Scanner(moveList);

            while (reader.hasNextLine()) {
                line = reader.nextLine();
                line.replaceAll(" ", "");
                moves[count2] = line;
                count2++;
            }
            
            for (i = 1; i <= 4; i++) {
                if (i!=1) {
                    System.out.print("Add another move?");
                    confirm = scnr.nextLine();
                    if (!confirm.equals("Y")||(confirm.equals("y"))) {
                        maxMoves = i;
                        break;
                    }
                }
                do {
                        System.out.print("Move "+i+"? ");
                    search = scnr.nextLine();
                    search.replaceAll(" ", "");
                    for (j = 0; j < moves.length; j++) {
                        if (moves[j].equals(search)) {
                            names[k] = moves[j];
                            desc[k] = moves[j+1];
                            k++;
                            System.out.println("Move added!");
                            buff=1;
                            break;
                        }
                        else {
                            buff = 0;
                            if (j==moves.length-1) {
                                System.out.println("ERROR: Move not found!");
                            }
                        }
                    }
                } while (buff==0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //START: The result of the method.//
        System.out.println("\n\n"+pkmn);
        System.out.println(abilities);
        System.out.println("HP - "+finalHP+" AC - "+finalAC);
        System.out.println("EXP Die: d"+EXPDie+"\n");
        System.out.println("STR: "+STR+" DEX: "+DEX+" CON: "+CON);
        System.out.println("WIS: "+WIS+" INT: "+INT+" CHA: "+CHA+"\n");

        System.out.println("ATK: "+finalATK+"\nSPA: "+finalSPA+"\nDEF: "+finalDEF+"\nSPD: "+finalSPD+"\nSPE: "+finalSPE);

        System.out.println("\nMOVES");
            for (i = 0; i < maxMoves-1; i++) {
                System.out.println(names[i]+" "+desc[i]);
            }
        System.out.println("\n~~TEST PASS~~"+"\n");
    }

    public static void createField() {
        String fieldName;
        System.out.print("What is the name of this Route/City? ");
        fieldName = scnr.nextLine();
        System.out.println("");

        try {
            File myFile = new File("Routes\\"+fieldName+".txt");

            if (myFile.createNewFile()) {
            System.out.println("File created: "+myFile.getName());
            }
            else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main (String[] args) {
        int opt;
        String flush;
        String confirm = null;

        System.out.println("PKMN D&D Data System");
        do {
            System.out.println("MAIN MENU");
            System.out.println("0 - List Route/City  1 - Create Route/city  2 - Update Route/City 3 - Create PKM  4 - Generate PKMN  5 - Update PKMN  6 - Exit");
            System.out.print("Select an option: ");
            opt = scnr.nextInt();
            flush = scnr.nextLine();

            

            if (opt == 0) {

            }

            if (opt == 1) {
                do {
                    createField();
                    System.out.print("Would you like to create another file? (Y/N): ");
                    confirm = scnr.nextLine();
                    if ((!confirm.equals("Y")) && (!confirm.equals("N"))) {
                        System.out.println("ERROR. Not a valid option. Exiting program.\n");
                    }
                    if (confirm.equals("Y")) {
                        continue;
                    }
                    if (confirm.equals("N")) {
                        break;
                    }
                } while (confirm.equals("Y"));
            }
               
            if (opt == 0) {

            } 

            if (opt == 3) {
                createPKMN();
            }

            if (opt == 4) {

            }

            if (opt == 5) {

            }

            if (opt == 6) {
                break;
            }
            else {
                System.out.println("ERROR: Not a valid option. Please retry.\n");
            }

        } while (opt != 6);
    }
}
