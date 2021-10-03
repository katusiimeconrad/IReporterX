package org.pahappa.systems.views;

import org.pahappa.systems.enums.Type;
import org.pahappa.systems.models.Incident;

import org.pahappa.systems.services.IncidentServiceImplConsole;


import java.util.List;
import java.util.Scanner;

public class IReporter {

    /**
     * This function helps print incidents with a numbered format.
     * It also sets the counter attribute in the Incident class.
     *
     * @param incidents
     */
    public static void printIncidents(List<Incident> incidents) {
        System.out.println("======== All Incidents =======");
        int counter = 0;
        for (Incident incident : incidents) {
            System.out.println(++counter + ". " + incident.getTitle());
        }
    }

    /**
     * Function takes in a list of {@link Incident}s with a Title, and prints them as options
     *
     * @param incidents
     * @param type
     */
    public static void printIncidents(List<Incident> incidents, String type) {
        System.out.println("======== " + type + " =======");
        int counter = 0;
        for (Incident incident : incidents) {
            System.out.println(++counter + ". " + incident.getTitle());
        }

    }

    /**
     * This method helps to call the update method to update an incident.
     *
     * @param incident
     * @return
     */
    public static Incident check(Incident incident) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the new Title: ");
        incident.setTitle(sc.nextLine());
        System.out.println("Choose 1 for Corruption Incident or 2 for Intervention incident");
        int type3 = sc.nextInt();
        sc.nextLine();
        if (type3 == 1) {
            incident.setType(Type.RED_FLAG);
        } else if (type3 == 2) {
            incident.setType(Type.INTERVENTION);
        }
        System.out.println("Enter the new Comment");
        incident.setComment(sc.nextLine());
        return incident;

    }

    /**
     * Prints the details of an incident and displays the back option
     *
     * @param incident
     */
    public static void printIncident(Incident incident) {
        System.out.println("Incident Title: " + incident.getTitle());
        System.out.println("Incident Type: " + incident.getType());
        System.out.println("Incident Comment: " + incident.getComment());
        System.out.println("Incident Status: " + incident.getStatus());
        System.out.println("Incident Date Created: " + incident.getCreatedOn());

        //Option to go back
        System.out.println("Enter 0 to return to the previous menu");
    }

    /**
     * Function creates dummy incidents, for demonstration purposes.
     *
     * @throws Exception
     */
    public static void incidentFactory() throws Exception {
        IncidentServiceImplConsole incidentService = new IncidentServiceImplConsole();


        String[] incidentTitles = new String[]{"Theft of Funds", "Bribe", "Bad road", "Low labour"};
        String[] incidentComments = new String[]{"Money allocated to health workers", "Officer Bribes", "The road to Kireka", "Poor pay to traffic officers"};
        Type[] incidentTypes = new Type[]{Type.RED_FLAG, Type.RED_FLAG, Type.INTERVENTION, Type.INTERVENTION};

        for (int i = 0; i < incidentTitles.length; i++) {
            Incident incident = new Incident();
            incident.setTitle(incidentTitles[i]);
            incident.setComment(incidentComments[i]);
            incident.setType(incidentTypes[i]);
            incidentService.saveIncident(incident);
        }
    }

    public static void main(String[] args) throws Exception {
        //Setting up dummy incidents
        incidentFactory();

        IncidentServiceImplConsole serviceHelper = new IncidentServiceImplConsole();

        System.out.println("**WELCOME TO IREPORTER SYSTEM**\n" +
                "DESCRIPTION OF IREPORTER \n" +
                "Corruption is a huge bane to Africa’s development.\n" +
                "African countries must develop novel and localised solutions that will curb this menace, hence the birth of iReporter.\n" +
                "iReporter enables any/every citizen to bring any form of corruption to the notice of appropriate authorities and the general public.\n" +
                "Users can also report on things that needs government intervention...........\n" +
                "*****************************************************\n" +
                "WHAT WOULD YOU LIKE TO DO {SELECT AN OPTION}.\n");
        boolean i = true;
        while (i) {
            System.out.println("1. Get Incidents\n" +
                    "2. Create Record\n" +
                    "3. Update Incidents\n" +
                    "4. Delete Incidents\n" +
                    "5. Total number of incidents\n" +
                    "0. Exit\n" +
                    "Enter your option:\n" +
                    " ");
            Scanner sc = new Scanner(System.in);
            int option;
            option = sc.nextInt();
            switch (option) {

//				Getting all incidents
                case 1:
                    boolean x = true;
                    while (x) {
                        System.out.println(
                                "Select an option:\n" +
                                        "1. Get Red Flag Incidents\n" +
                                        "2. Get Intervention Incidents\n" +
                                        "3. View All Incidents\n" +
                                        "0. Exit\n"
                        );
                        Scanner scanner = new Scanner(System.in);
                        int incidentOption;
                        incidentOption = scanner.nextInt();
                        switch (incidentOption) {

                            //Getting RedFlag Incidents
                            case 1:
                                System.out.println("Red Flag Incidents : \n");
                                while (true) {
                                    printIncidents(serviceHelper.getRedflagIncidents(), "Red_Flag Incidents");

                                    //Option to capture Input
                                    option = scanner.nextInt();
                                    if (option > serviceHelper.getRedflagIncidents().size() || option == 0) {
                                        break;
                                    } else
                                        //Print the selected Option
                                        //Option -1 : To refer to the actual index
                                        printIncident(serviceHelper.getRedflagIncidents().get(option - 1));
                                }
                                break;
                            case 2:
                                System.out.println("Intervention Incidents : \n");
                                while (true) {
                                    printIncidents(serviceHelper.getInterventionIncidents(), "Intervention Incidents");

                                    option = scanner.nextInt();
                                    if (option > serviceHelper.getInterventionIncidents().size() || option == 0) {
                                        break;
                                    } else
                                        printIncident(serviceHelper.getInterventionIncidents().get(option - 1));
                                }
                                break;
                            case 3:
                                System.out.println("All Incidents : \n");
                                while (true) {
                                    printIncidents(serviceHelper.getAllIncidents(), "All Incidents");

                                    option = scanner.nextInt();
                                    if (option > serviceHelper.getAllIncidents().size() || option == 0) {
                                        break;
                                    } else
                                        printIncident(serviceHelper.getAllIncidents().get(option - 1));
                                }
                                break;
                            case 0:
                                x = false;
                                System.out.println("Exiting ....");
                                break;
                            default:
                                System.out.println("Invalid option");
                        }
                    }
                    break;

//				Saving incidents.
                case 2:
                    Incident incident1 = new Incident();
                    System.out.println("Please select(Number) the type of your incident \n" +
                            "1 ---> corruption Incident \n" +
                            "2 ---> Intervention Incident" + "\n");
                    int type = sc.nextInt();

                    sc.nextLine();

                    System.out.println("Please enter the title of your incident \n");
                    sc.nextLine();
                    String title = sc.nextLine();
                    System.out.println("Enter your comment \n");
                    String comment = sc.nextLine();

                    if (type == 1) {
                        incident1.setType(Type.RED_FLAG);
                    }
                    if (type == 2) {

                        incident1.setType(Type.INTERVENTION);
                    }
                    incident1.setTitle(title);
                    incident1.setComment(comment);

                    Incident savedIncident = serviceHelper.saveIncident(incident1);
                    System.out.println("Your report has been saved" + "\n");
                    System.out.println(savedIncident);

                    break;

//				Updating an incident
                case 3:
                    System.out.println("===== Please Choose the number of the incident to edit =====");
                    printIncidents(serviceHelper.getAllIncidents());
                    int chosen = sc.nextInt();
                    System.out.println(check(serviceHelper.getAllIncidents().get(chosen - 1)));

                    break;
                case 4:
                    while (true) {
                        printIncidents(serviceHelper.getAllIncidents(), "All Incidents");
                        System.out.println("Please type the number of the incident you would like to delete");
                        System.out.println("To exit, type 00");
                        Scanner y = new Scanner(System.in);
                        option = y.nextInt();

                        if (option == 00) {
                            break;
                        }
                        if (option > serviceHelper.getAllIncidents().size() || option == 0) {
                            break;
                        } else
                            System.out.println("The incident you have deleted is:" + serviceHelper.getAllIncidents().get(option - 1));
                        serviceHelper.deleteIncident(serviceHelper.getAllIncidents().get(option - 1));
                    }

                    break;
                case 5:
                    System.out.println("Total  number of incidents " + serviceHelper.countIncidents());
                    break;
                case 0:
                    i = false;
                    System.out.println("Exiting ...");
                    break;
                default:
                    System.out.println("wrong input");
            }
        }


    }
}