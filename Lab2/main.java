import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
public static void clear() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}
void main() 
{
    Scanner s = new Scanner(System.in);
    Show currentShow = null;
    while(true) 
    {
        int userOption;
        System.out.println("Select one of the following");
        System.out.println("'1' for Option 1");
        System.out.println("'2' for Option 2");
        System.out.println("'3' for Option 3");
        System.out.println("'0' to exit");
        System.out.println();
        userOption = s.nextInt();
        s.nextLine();

        if (userOption == 1) {
            currentShow = makeShowFile(s);
        } 
        else if (userOption == 2) 
        {
            currentShow = loadAndSellTicket(s);
        } 
        else if (userOption == 3) 
        {
            option3(s, currentShow);
        } 
        else if (userOption == 0) 
        {
            break;
        } 
        else 
        {
            System.out.println("Invalid input, try again");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            clear();
        }
    }
    s.close();
}


Show makeShowFile(Scanner s) {
    System.out.print("Please enter the name of the show: ");
    String showName = s.nextLine();
    System.out.print("Please enter the artist's name: ");
    String artistName = s.nextLine();
    System.out.print("Please enter the venue name: ");
    String venueName = s.nextLine();
    System.out.print("Please enter the number of tickets you want (10 - 300 only): ");
    int numberOfTickets = s.nextInt();
    s.nextLine(); // Clear newline buffer

    if (numberOfTickets >= 10 && numberOfTickets <= 300) 
    {
        Show thisShow = new Show(showName, artistName, venueName, numberOfTickets);

        // Create the file name (replaces spaces with underscores)
        String fileName = showName.replaceAll("\\s+", "_") + ".txt";
        File showFile = new File(fileName);

        try {
            if (showFile.createNewFile()) 
            {
                System.out.println("File created: " + showFile.getName());
            } 
            else 
            {
                System.out.println("Overwriting existing file: " + showFile.getName());
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(showFile))) 
            {
                writer.println(thisShow.getShowName() + "," +
                        thisShow.getArtist() + "," +
                        thisShow.getVenue() + "," +
                        thisShow.getNumberOfTickets());
                for (Ticket t : thisShow.getTickets()) {
                    writer.println(t.getTicketNumber() + "," +
                            t.getSeatNumber() + "," +
                            t.getVerificationCode() + "," +
                            t.getPurchaserName());
                }
            }

            System.out.println("Show data successfully written to " + showFile.getAbsolutePath() + "\n");
            return thisShow;

        } catch (IOException e) 
        {
            System.out.println("An error occurred while creating the file: " + e.getMessage() + "\n");
            return null;
        }
    } else 
    {
        System.out.println("Invalid input, must be between 10 and 300.\n");
        return null;
    }
}


Show loadAndSellTicket(Scanner s)
{
    Show loadedShow = loadShowFile(s);
    if (loadedShow == null) {
        return null;
    }

    System.out.print("Enter the ticket number to sell: ");
    int targetTicketNum = s.nextInt();
    s.nextLine();
    for (Ticket t : loadedShow.getTickets())
    {
        if (t.getTicketNumber() == targetTicketNum) 
        {
            if (t.isSold()) 
            {
                System.out.println("Ticket #" + targetTicketNum + " is already sold to " + t.getPurchaserName() + ".\n");
                return loadedShow;
            }

            System.out.print("Enter the purchaser's name: ");
            String purchaserName = s.nextLine();

            t.sellTicket(purchaserName);
            saveShowToFile(loadedShow);

            System.out.println("Success! Ticket #" + targetTicketNum + " (Seat " + t.getSeatNumber() +
                    ") sold to " + purchaserName + ".\n");
            return loadedShow;
        }
    }

    System.out.println("Ticket #" + targetTicketNum + " was not found in this show.\n");
    return loadedShow;
}
Show loadShowFile(Scanner s) 
{
    System.out.print("Enter the exact name of the show to load: ");
    int sellable = 0;
    String showName = s.nextLine();
    String fileName = showName.replaceAll("\\s+", "_") + ".txt";
    File file = new File(fileName);


    if (!file.exists()) 
    {
        System.out.println("File '" + fileName + "' not found!\n");
        return null;
    }

    try (Scanner fileScanner = new Scanner(file)) 
    {
        String[] showInfo = fileScanner.nextLine().split(",");
        String loadedName = showInfo[0];
        String loadedArtist = showInfo[1];
        String loadedVenue = showInfo[2];
        int totalTickets = Integer.parseInt(showInfo[3]);

        Show loadedShow = new Show(loadedName, loadedArtist, loadedVenue, totalTickets);
        loadedShow.clearTickets();
        int soldCount = 0;
        while (fileScanner.hasNextLine()) 
        {
            String[] tData = fileScanner.nextLine().split(",", -1);
            int tNum = Integer.parseInt(tData[0]);
            int sNum = Integer.parseInt(tData[1]);
            String vCode = tData[2];
            String purchaser = tData[3];

            Ticket t = new Ticket(tNum, sNum, vCode, purchaser);
            if (t.isSold()) soldCount++;
            loadedShow.addLoadedTicket(t);
        }

        System.out.println("\n--- Show Loaded ---");
        System.out.println("Show: " + loadedShow.getShowName() + " | Artist: " + loadedShow.getArtist());
        System.out.println("Venue: " + loadedShow.getVenue());
        System.out.println("Tickets Sold: " + soldCount + " / " + totalTickets + "\n");

        return loadedShow;
    } catch (Exception e) {
        System.out.println("Error reading file: " + e.getMessage() + "\n");
        return null;
    }
}

void saveShowToFile(Show show) 
{
    String fileName = show.getShowName().replaceAll("\\s+", "_") + ".txt";
    File showFile = new File(fileName);

    try (PrintWriter writer = new PrintWriter(new FileWriter(showFile))) {
        writer.println(show.getShowName() + "," +
                show.getArtist() + "," +
                show.getVenue() + "," +
                show.getNumberOfTickets());

        for (Ticket t : show.getTickets()) {
            writer.println(t.getTicketNumber() + "," +
                    t.getSeatNumber() + "," +
                    t.getVerificationCode() + "," +
                    t.getPurchaserName());
        }
    } catch (IOException e) {
        System.out.println("Error saving file: " + e.getMessage() + "\n");
    }
}

Show option3(Scanner s, Show currentShow)
{
    if (currentShow == null) {
        System.out.println("No show currently loaded in memory.");
        currentShow = loadShowFile(s);
        if (currentShow == null) {
            return null;
        }
    }

    int soldCount = 0;
    for (Ticket t : currentShow.getTickets()) {
        if (t.isSold()) {
            soldCount++;
        }
    }
    System.out.println("==========================================================================");
    System.out.println("SHOW DETAILS");
    System.out.println("==========================================================================");
    System.out.printf("Show Name:      %s%n", currentShow.getShowName());
    System.out.printf("Artist:         %s%n", currentShow.getArtist());
    System.out.printf("Venue:          %s%n", currentShow.getVenue());
    System.out.printf("Tickets Sold:   %d / %d (Available: %d)%n",
            soldCount,
            currentShow.getNumberOfTickets(),
            currentShow.getNumberOfTickets() - soldCount);
    System.out.println("==========================================================================");
    
    System.out.printf("%-12s | %-8s | %-10s | %-20s | %-18s%n",
            "Ticket #", "Seat #", "Status", "Owner", "Verification Code");
    System.out.println("--------------------------------------------------------------------------");

    for (Ticket t : currentShow.getTickets()) 
    {
        String status = t.isSold() ? "SOLD" : "Available";
        String owner = t.isSold() ? t.getPurchaserName() : "---";
        String vCode = t.isSold() ? t.getVerificationCode() : "N/A";

        System.out.printf("%-12d | %-8d | %-10s | %-20s | %-18s%n",
                t.getTicketNumber(),
                t.getSeatNumber(),
                status,
                owner,
                vCode);
    }

    System.out.println("==========================================================================\n");
    return currentShow;
}
