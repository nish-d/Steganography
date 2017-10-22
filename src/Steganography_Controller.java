import java.util.Scanner;

/*
 *Steganography_Controller Class
 */
public class Steganography_Controller {
    //Program Variables
    private Steganography model;

    //action event classes
    private EncodeButton encButton;
    private DecodeButton decButton;

    //decode variable
    private String stat_path = "";
    private String stat_name = "";
    private Scanner scanner;

    /*
     *Constructor to initialize view, model and environment variables
     *@param aView  A GUI class, to be saved as view
     *@param aModel A model class, to be saved as model
     */
    public Steganography_Controller(Steganography aModel) {
        scanner = new Scanner(System.in);
        model = aModel;
        encButton = new EncodeButton();
        decButton = new DecodeButton();
        encode_view();
    }

    /*
     *Main Method for testing
     */
    public static void main(String args[]) {
        new Steganography_Controller(
                new Steganography()
        );
    }

    /*
     *Updates the single panel to display the Encode View.
     */
    private void encode_view() {
        int choice = 0;
        System.out.println("Menu: \n1.Encode \n2.Decode \n3.Exit\n");

        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                encButton.actionPerformed();
                break;
            case 2:
                decButton.actionPerformed();
                break;
            case 3:
                new Exit().actionPerformed();
                break;

        }

    }


    /*
     *Exit Class - handles the Exit menu item
     */
    private class Exit {
        /*
         *handles the click event
         *@param e The ActionEvent Object
         */
        public void actionPerformed() {
            System.exit(0);
        }


    }

    /*
     *Encode Button Class - handles the Encode Button item
     */
    private class EncodeButton {
        /*
         *handles the click event
         *@param e The ActionEvent Object
         */
        public void actionPerformed() {
            System.out.println("Enter message to be encoded.");
            scanner.nextLine();
            String text = scanner.nextLine();
            System.out.println("Enter output filename");
            String stegan = scanner.nextLine();
            try {

                System.out.println("Enter path of image file, excluding image name:");
                String path = scanner.nextLine();
                System.out.println("Enter name of file excluding extension:");
                String name = scanner.nextLine();
                System.out.println("Enter extension of file:");
                String ext = scanner.nextLine();

                if (model.encode(path, name, ext, stegan, text)) {

                    System.out.println("The image was encoded successfully");
                } else {
                    System.out.println("The image could not be encoded successfully");
                }
            } catch (Exception except) {

                System.out.println("The File cannot be opened!");
            } finally {
                encode_view();
            }
        }


    }

    /*
     *Decode Button Class - handles the Decode Button item
     */
    private class DecodeButton {
        /*
         *handles the click event
         *@param e The ActionEvent Object
         */
        public void actionPerformed() {
            scanner.nextLine();
            System.out.println("Enter path of image to be decoded excluding filename. ");
            stat_path = scanner.nextLine();
            System.out.println("Enter name of image without extension");
            stat_name = scanner.nextLine();
            try {
                String message = model.decode(stat_path, stat_name);
                System.out.println(stat_path + ", " + stat_name);
                if (message != "") {
                    System.out.println("The image was decoded succesfully\n The message is :" + message);
                } else {
                    System.out.println("The Image could not be decoded!" + message);
                }
            } catch (Exception e) {
                System.err.println("File could not be opened");
            } finally {
                encode_view();
            }
        }
    }
}