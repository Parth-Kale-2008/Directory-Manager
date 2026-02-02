import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DirectoryExplorer {

    private static void fileManagement(File file) {
        System.out.println("\nPress 1 to rename the file," +
                "\nPress 2 to delete the file," +
                "\nAny other key to exit");

        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();

        if (userChoice.equals("1")) {
            System.out.println("Enter the new name for the file " + file.getName());
            String newfileName = scanner.nextLine();
    
            boolean changed = file.renameTo(new File(file.getParent(), newfileName));
            if (changed) {
                System.out.println("Filename successfully changed");
            } else {
                System.out.println("Filename couldn't be changed!");
            }
        } else if (userChoice.equals("2")) {
            
            boolean deleted = file.delete();
            
            if (deleted) {
                System.out.println("Filename successfully deleted");
            } else {
                System.out.println("Filename couldn't be deleted!");
            }
        }
    }

    private static void directoryList(File dirObj) {

        File files[] = dirObj.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                System.out.println(files[i].getAbsolutePath());
            } else {
                directoryList(files[i]);
            }
        }
    }

    private static void directoryManagement(File dirObj) {
        System.out.println("\nPress 1 to list the directory," +
                "\nPress 2 to rename the directory," +
                "\nPress 3 to delete the directory," +
                "\nPress 4 to recursively list the directory," +
                "\nAny other key to exit");

        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();

        if (userChoice.equals("1")) {
            String fileNames[] = dirObj.list();
            if (fileNames.length == 0) {
                System.out.println("The directory is empty!");
            } else {
                for (int i = 0; i < fileNames.length; i++) {
                    System.out.println(fileNames[i]);
                }
            }
        } else if (userChoice.equals("2")) {
            System.out.println("Enter the new name for the directory " + dirObj.getName());
            String newDirName = scanner.nextLine();
            boolean changed = dirObj.renameTo(new File(dirObj.getParent(), newDirName));
            if (changed) {
                System.out.println("Directory name successfully changed");
            } else {
                System.out.println("Directory name couldn't be changed!");
            }
        } else if (userChoice.equals("3")) {
            boolean deleted = dirObj.delete();
            
            if (deleted) {
                System.out.println("Directory successfully deleted");
            } else {
                System.out.println("Directory couldn't be deleted! It might not be empty");
            }
        } else if (userChoice.equals("4")) {
                
                directoryList(dirObj);
        }
    }

    public static void main(String s[]) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            
            System.out.println("\nPress 1 for File Management," + "\nAny other key to exit");

            String userAction = scanner.nextLine();

            if (userAction.equals("1")) {
                
            System.out.println("Enter the name of the file or directory with the path");

            String fileName = scanner.nextLine();

            File file = new File(fileName);

            if (file.exists()) {
                    if (file.isFile()) {
                        System.out.println(fileName + " is a file");
                        fileManagement(file);
                    } else {
                        System.out.println(fileName + " is a directory");
                        directoryManagement(file);
                    }
                } else {
                    System.out.println(fileName + " is not a valid file or directory");
                    System.out.println("To create a file with given name press 1\n"
                            + "To create a directory with given name press 2\n"
                            + "To do nothing and continue, press any other key");

                    String createChoice = scanner.nextLine();

                    if (createChoice.equals("1")) {
                        String parentDirStr = file.getParent();
                        File parentDir = new File(parentDirStr);

                        if (!parentDir.exists()) {
                            boolean created = parentDir.mkdirs();
                            if (!created) {
                                System.out.println("The parent directory could not be created");
                                continue;
                            }
                        }

                        try {
                            file.createNewFile();
                            System.out.println("File successfully created!");
                        } catch (IOException ioe) {
                            System.out.println("Unable to create file. " + ioe.getMessage());
                        }
                    }
                    else if (createChoice.equals("2")) {
                        boolean created = file.mkdirs();
                        if (created) {
                            System.out.println("The directory has been created");
                        } else {
                            System.out.println("The directory couldn't be created");
                        }
                    }
                }
            }
            else {
                System.out.println("Bye!");
                break;
            }
        }
    }
}