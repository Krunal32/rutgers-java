public class HotelRoom
{
	final String singleRoom    = "single";
	final String doubleRoom    = "double";
	final String businessSuite = "business";

	final double singleRoomRate = 80;
	final double doubleRoomRate = 120;
	final double businessSuiteRate = 200;

	// define your fields here
	private String guestName;
	private String roomType;
	private int days;

	public HotelRoom(String guestname, String roomtype, int days)
	{
		// complete this method
		this.guestName = guestname;
		this.setRoomType(roomtype);
		this.setReservationLength(days);
	}

	public String getGuestName()
	{
		return this.guestName; // replace this line with your code
	}

	public String getRoomType()
	{
		return this.roomType; // replace this line with your code
	}

	public int getReservationLength()
	{
		return this.days; // replace this line with your code
	}
    
	public double getEstimatedPrice()
	{
		double rate = -1;
		
		if (this.roomType.equalsIgnoreCase(singleRoom))
		{
			rate = singleRoomRate;
		}
		else if (this.roomType.equalsIgnoreCase(doubleRoom))
		{
			rate = doubleRoomRate;
		}
		else if (this.roomType.equalsIgnoreCase(businessSuite))
		{
			rate = businessSuiteRate;
		}
		
		return rate*this.days; // replace this line with your code
	}

	public void setRoomType(String roomtype)
	{
		if (roomtype.equalsIgnoreCase(singleRoom))
		{
			this.roomType = singleRoom;
		}
		else if (roomtype.equalsIgnoreCase(doubleRoom))
		{
			this.roomType = doubleRoom;
		}
		else if (roomtype.equalsIgnoreCase(businessSuite))
		{
			this.roomType = businessSuite;
		}
	}

	public void setReservationLength(int days)
	{
		if (days > 0)
		{
			this.days = days;
		}
	}

	public double getActualPrice(int daysStayed)
	{
		double rate = -1;
		
		if (this.roomType.equalsIgnoreCase(singleRoom))
		{
			rate = singleRoomRate;
		}
		else if (this.roomType.equalsIgnoreCase(doubleRoom))
		{
			rate = doubleRoomRate;
		}
		else if (this.roomType.equalsIgnoreCase(businessSuite))
		{
			rate = businessSuiteRate;
		}
		
		return rate*daysStayed; // replace this line with your code
	}

}