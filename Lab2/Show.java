import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Show {
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private String showName, artistName, venue;
    private int numberOfTickets;


    Show(String showname, String artist, String Venue, int tickets)
    {
        this.showName = showname;
        this.artistName = artist;
        this.venue = Venue;
        this.numberOfTickets = tickets;

        for(int i = 1000; i < 1000 + numberOfTickets; i++)
        {
            Ticket temp = new Ticket(i, i-500);
            this.tickets.add(temp);
        }
    }

    public String getShowName()
    {
        return showName;
    }
    public String getVenue()
    {
        return venue;
    }
    public String getArtist()
    {
        return artistName;
    }
    public int getNumberOfTickets()
    {
        return numberOfTickets;
    }
    public ArrayList<Ticket> getTickets()
    {
        return new ArrayList<>(tickets);
    }
    public void clearTickets() {
        this.tickets.clear();
    }

    public void addLoadedTicket(Ticket t) {
        this.tickets.add(t);
    }
}
