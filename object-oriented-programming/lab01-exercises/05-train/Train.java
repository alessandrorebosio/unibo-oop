public class Train {

    private int nTotSeats;
    private int nFirstClassSeats;
    private int nSecondClassSeats;
    private int nFirstClassReservedSeats;
    private int nSecondClassReservedSeats;

    public void build(final int nFirstClassSeats, final int nSecondClassSeats) {
        this.nFirstClassSeats = nFirstClassSeats;
        this.nSecondClassSeats = nSecondClassSeats;
        this.nTotSeats = nFirstClassSeats + nSecondClassSeats;
    }

    public void reserveFirstClassSeats(final int nSeats) {
        if (this.nFirstClassReservedSeats + nSeats <= this.nFirstClassSeats) {
            this.nFirstClassReservedSeats += nSeats;
        }
    }

    public void reserveSecondClassSeats(final int nSeats) {
        if (this.nSecondClassReservedSeats + nSeats <= this.nSecondClassSeats) {
            this.nSecondClassReservedSeats += nSeats;
        }
    }

    public double getTotOccupancyRatio() {
        return (this.nFirstClassReservedSeats + this.nSecondClassReservedSeats) * 100.0 / this.nTotSeats;
    }

    public double getFirstClassOccupancyRatio() {
        return (this.nFirstClassReservedSeats) * 100.0 / this.nFirstClassSeats;
    }

    public double getSecondClassOccupancyRatio() {
        return (this.nSecondClassReservedSeats) * 100.0 / this.nSecondClassSeats;
    }

    public void deleteAllReservations() {
        this.nFirstClassReservedSeats = 0;
        this.nSecondClassReservedSeats = 0;
    }
}
