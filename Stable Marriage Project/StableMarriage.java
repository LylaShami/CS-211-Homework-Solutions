// This program reads an input file of preferences and find a stable marriage
// scenario.  The algorithm gives preference to either men or women depending
// upon whether this call is made from main:
//      makeMatches(men, women);
// or whether this call is made:
//      makeMatches(women, men);

import java.io.*;
import java.util.*;

public class StableMarriage {
    public static final String LIST_END = "END";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.print("What is the input file? ");
        String fileName = console.nextLine();
        Scanner input = new Scanner(new File(fileName));
        System.out.println();

        List<Person> men = readHalf(input);
        List<Person> women = readHalf(input);
        makeMatches(men, women);
        writeList(men, women, "Matches for men");
        writeList(women, men, "Matches for women");
    }

    public static Person readPerson(String line) {
        int index = line.indexOf(":");
        Person result = new Person(line.substring(0, index));
        Scanner data = new Scanner(line.substring(index + 1));
        while (data.hasNextInt()) {
            result.addChoice(data.nextInt());
        }
        return result;
    }

    public static List<Person> readHalf(Scanner input) {
        List<Person> result = new ArrayList<Person>();
        String line = input.nextLine();
        while (!line.equals(LIST_END)) {
            result.add(readPerson(line));
            line = input.nextLine();
        }
        return result;
    }

    public static void makeMatches(List<Person> mensList, List<Person> womensList) {
        freeEachPerson(mensList, womensList); //freeing the men and woman

        int currentManIndex = 0;
        for (currentManIndex = 0; currentManIndex < mensList.size(); currentManIndex++) {

            Person currentMan = mensList.get(currentManIndex);
            if (!currentMan.hasPartner() && currentMan.hasChoices()) {
                Person choiceWoman = womensList.get(currentMan.getFirstChoice()); //currentMan's first choice

                //  if the woman is engaged, then clear the engagement
                if (choiceWoman.hasPartner()) {
                    freeMan(mensList, choiceWoman);
                    freeWoman(choiceWoman);
                }

                //assign choice woman to currentMan and vice versa
                currentMan.setPartner(womensList.indexOf(choiceWoman));
                choiceWoman.setPartner(currentManIndex);

                int choiceWomansPartner = choiceWoman.getChoices().indexOf(currentManIndex);
                for (int i = choiceWomansPartner + 1; i < choiceWoman.getChoices().size(); i++) {

                    int removableMan = choiceWoman.getChoices().get(i);// storing the men so that we can delete later
                    choiceWoman.getChoices().remove(i);// removing the man

                    int womanToRemove = mensList.get(removableMan).getChoices().indexOf(currentMan.getFirstChoice()); //grab the int for the woman
                    mensList.get(removableMan).getChoices().remove(womanToRemove);//erasing woman from the removable man's list
                }
                currentManIndex = 0;
            }
        }
    }

    private static void freeWoman(Person choiceWoman) {
        choiceWoman.erasePartner();
    }

    private static void freeMan(List<Person> menList, Person choiceWoman) {
        int currentManFianceInt = choiceWoman.getPartner();
        Person currentManFiance = menList.get(currentManFianceInt);
        freeWoman(currentManFiance);
    }

    private static void freeEachPerson(List<Person> men, List<Person> women) {
        for (Person manPerson : men) {
            freeWoman(manPerson);
        }
        for (Person womanPerson : women) {
            freeWoman(womanPerson);
        }
    }

    public static void writeList(List<Person> list1, List<Person> list2, String title) {
        System.out.println(title);
        System.out.println("Name           Choice  Partner");
        System.out.println("--------------------------------------");
        int sum = 0;
        int count = 0;
        for (Person p : list1) {
            System.out.printf("%-15s", p.getName());
            if (!p.hasPartner()) {
                System.out.println("  --    nobody");
            } else {
                int rank = p.getPartnerRank();
                sum += rank;
                count++;
                System.out.printf("%4d    %s\n", rank, list2.get(p.getPartner()).getName());
            }
        }
        System.out.println("Mean choice = " + (double) sum / count);
        System.out.println();
    }
}
