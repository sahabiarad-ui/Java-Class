import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;import java.security.SecureRandom;

public class Ticket {

    private int ticketNumber, seatNumber;
    private String verificationCode;
    private boolean sold;
    private String purchaserName;


    Ticket(int TicketNumber, int SeatNumber)
    {
        ticketNumber = TicketNumber;
        seatNumber = SeatNumber;
        sold = false;
        purchaserName = "";
        verificationCode = "0";
    }
    Ticket(int ticketNumber, int seatnumber, String verificationCode, String purchaserName)
    {
        this.ticketNumber = ticketNumber;
        this.seatNumber = seatnumber;
        this.purchaserName = purchaserName;
        this.verificationCode = verificationCode;
        this.sold = !verificationCode.equals("0");
    }

    public int getTicketNumber()
    {
        return ticketNumber;
    }
    public int getSeatNumber()
    {
        return seatNumber;
    }
    public String getVerificationCode()
    {
        return verificationCode;
    }

    public boolean isSold()
    {
        return sold;
    }

    public String getPurchaserName()
    {
        return purchaserName;
    }

    void sellTicket(String purchaserName)
    {
        sold = true;
        verificationCode = genVerification();
        this.purchaserName = purchaserName;
    }

    private String genVerification()
    {
        SecureRandom random = new SecureRandom();
        StringBuilder string= new StringBuilder(16);
        for(int i = 0; i < 16; i++)
        {
            string.append(random.nextInt(10));
        }
        return string.toString();
    }


}
